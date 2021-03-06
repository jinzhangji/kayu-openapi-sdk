package com.kayu.core;

import com.alibaba.fastjson.JSON;
import com.kayu.annotation.ExcludeParam;
import com.kayu.annotation.ParamProperty;
import com.kayu.constant.RequestMethod;
import com.kayu.exception.OpenApiException;
import com.kayu.param.IBaseParam;
import com.kayu.result.OpenApiBaseResult;
import com.kayu.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;

/**
 * Created by Jin.Z.J  2021/3/24
 */
public class DefaultOpenApiClient implements OpenApiClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultOpenApiClient.class);

    private static final String UTF_8 = "UTF-8";

    private static ExecutorService threadPool;

    private String host;
    private String md5Key;
    private String merchantNo;
    private int timeout;


    protected DefaultOpenApiClient(String host, String merchantNo, String md5Key, int timeout) {
        this.host = host;
        this.md5Key = md5Key;
        this.merchantNo = merchantNo;
        this.timeout = timeout;
    }

    @Override
    public <T extends OpenApiBaseResult> T execute(IBaseParam<T> param) throws OpenApiException {
        boolean verifyFlag = param.verifyParam();
        if (!verifyFlag) {
            throw new OpenApiException("参数有误,请检查参数!");
        }
        //url
        String url = this.host + param.requestURI();
        if (logger.isDebugEnabled()) {
            logger.debug("ky-sdk-begin: -> requestURI: {}", param.requestURI());
        }
        //解析参数
        Map<String, Object> map = MapUtils.newHashMap();
        BeanUtils.foreachDesc(param.getClass(), p -> {
            Field field = p.getField();
            ExcludeParam excludeParam = field.getDeclaredAnnotation(ExcludeParam.class);
            if (excludeParam != null) {
                return;
            }
            ParamProperty property = field.getDeclaredAnnotation(ParamProperty.class);
            String key = property == null ? p.getName() : property.value();
            Object val = BeanUtils.invoke(param, p.getter());
            map.put(key, val);
        });
        map.put("merchantNo", this.merchantNo);
        map.put("sign", SignUtils.sign(map, this.md5Key));
        //统一设置请求头
        Map<String, String> header = MapUtils.newHashMap();
        header.put("User-Agent", String.format("open-sdk-java(version=%s)", param.version()));
        String requestId = param.requestId();
        header.put("request-id", requestId);
        logger.debug("ky-sdk-debug: request-id:{}, sdk-version{}", requestId, param.version());
        Response res;
        if (param.reqMethod().equals(RequestMethod.POST)) {
            res = doPost(url, map, header);
        } else {
            res = doGet(url, map, header);
        }
        int statusCode = res.getStatusCode();
        if (res.getStatusCode() == 200) {
            return JSON.parseObject(res.getResult(), param.resClass());
        } else {
            logger.error("ky-sdk-error: http statusCode:{},响应错误信息:{}", statusCode, res.getResult());
            throw new OpenApiException(String.format("ky-sdk-error: http statusCode:%s,响应错误信息:%s", statusCode, res.getResult()));
        }
    }


    @Override
    public <T extends OpenApiBaseResult> Future<T> asyncExecute(IBaseParam<T> param) {
        return getThreadPool().submit(() -> this.execute(param));
    }


    /**
     * 获取线程池
     *
     * @return
     */
    private ExecutorService getThreadPool() {
        if (threadPool == null) {
            //lock monitor by Class
            synchronized (this.getClass()) {
                if (threadPool == null) {
                    threadPool = Executors.newCachedThreadPool();
                }
            }
        }
        return threadPool;
    }


    public static void shutdown() {
        if (threadPool != null && !threadPool.isShutdown()) {
            threadPool.shutdown();
        }
    }


    /**
     * post 请求
     *
     * @param url
     * @param params
     * @param header
     * @return
     * @throws OpenApiException
     */
    private Response doPost(String url, Map<String, Object> params, Map<String, String> header) throws OpenApiException {
        try {
            HttpURLConnection connection = getConnection(new URL(url), "POST");
            connection.setRequestProperty("Content-Type", "application/json");
            if (header != null && !header.isEmpty()) {
                header.forEach(connection::addRequestProperty);
            }
            try (OutputStream out = connection.getOutputStream()) {
                String jsonParam = JSON.toJSONString(params);
                if (logger.isDebugEnabled()) {
                    logger.debug("KY_SDK_BEGIN -> params:{}", jsonParam);
                }
                out.write(jsonParam.getBytes(UTF_8));
                out.flush();
            }
            return toResponse(connection);
        } catch (Exception e) {
            throw new OpenApiException(e);
        }
    }


    /**
     * get 请求
     *
     * @param url
     * @param params
     * @param header
     * @return
     * @throws OpenApiException
     */
    private Response doGet(String url, Map<String, Object> params, Map<String, String> header) throws OpenApiException {
        try {
            URI uri = new URIBuilder(url).addParam(params).build();
            if (logger.isDebugEnabled()) {
                logger.debug("KY_SDK_BEGIN -> params:{}", uri.getQuery());
            }
            HttpURLConnection connection = getConnection(new URL(uri.toString()), "GET");
            if (!MapUtils.isEmpty(header)) {
                header.forEach(connection::setRequestProperty);
            }
            return toResponse(connection);
        } catch (Exception e) {
            throw new OpenApiException(e);
        }
    }

    private HttpURLConnection getConnection(URL url, String method) throws Exception {
        HttpURLConnection conn;
        if ("https".equals(url.getProtocol())) {
            conn = HttpsUtils.getHttpsConnection(url);
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        conn.setRequestMethod(method);
        conn.setConnectTimeout(this.timeout);
        conn.setReadTimeout(this.timeout);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(true);
        return conn;
    }


    /**
     * 返回结果
     *
     * @param connection
     * @return
     * @throws OpenApiException
     */
    private Response toResponse(HttpURLConnection connection) throws OpenApiException {
        try {
            InputStream stream;
            int code = connection.getResponseCode();
            String charset = getResponseCharset(connection.getContentType());
            if (code > 300 && code < 400) {
                String location = connection.getHeaderField("Location");
                if (logger.isDebugEnabled()) {
                    logger.debug("KY_SDK_BEGIN --> redirect:{}", location);
                }
                return this.doGet(location, null, null);
            } else if (code >= 200 && code < 300) {
                String contentEncoding = connection.getHeaderField("Content-Encoding");
                if ("gzip".equalsIgnoreCase(contentEncoding)) {
                    stream = new GZIPInputStream(connection.getInputStream());
                } else {
                    stream = connection.getInputStream();
                }
                return new Response(code, streamToString(stream, charset));
            } else {
                stream = connection.getErrorStream();
                String msg = streamToString(stream, charset);
                if (StringUtils.isEmpty(msg)) {
                    msg = connection.getResponseMessage();
                }
                return new Response(code, msg);
            }
        } catch (Exception e) {
            throw new OpenApiException(e);
        }
    }


    /**
     * 字节流转字符
     *
     * @param stream
     * @param charset
     * @return
     */
    private String streamToString(InputStream stream, String charset) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 解析响应字符
     *
     * @param contentType
     * @return
     */
    private String getResponseCharset(String contentType) {
        String charset = UTF_8;
        if (StringUtils.isEmpty(contentType)) {
            return charset;
        }
        String[] params = contentType.split(";");
        for (int i = 0; i < params.length; ++i) {
            String param = params[i].trim();
            if (param.startsWith("charset")) {
                String[] pair = param.split("=", 2);
                if (pair.length == 2 && !StringUtils.isEmpty(pair[1])) {
                    charset = pair[1].trim();
                }
                break;
            }
        }
        return charset;
    }


    private static class Response {

        private int statusCode;
        private String result;

        public Response(int statusCode, String result) {
            this.statusCode = statusCode;
            this.result = result;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getResult() {
            return result;
        }
    }


    public static class Builder {

        private String merchantNo;
        private String host;
        private String md5key;
        private int timeout = 30000;

        public Builder merchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
            return this;
        }


        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder md5key(String md5key) {
            this.md5key = md5key;
            return this;
        }

        public Builder useSandBox() {
            this.host = "";
            return this;
        }


        public Builder timeout(int second) {
            this.timeout = second * 1000;
            return this;
        }

        public Builder threadPool(ExecutorService pool) {
            DefaultOpenApiClient.threadPool = pool;
            return this;
        }


        public OpenApiClient build() {
            return new DefaultOpenApiClient(host, merchantNo, md5key, this.timeout);
        }

    }


}
