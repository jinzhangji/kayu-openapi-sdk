package com.kayu.core;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.kayu.annotation.ExcludeParam;
import com.kayu.annotation.ParamProperty;
import com.kayu.constant.RequestMethod;
import com.kayu.exception.OpenApiException;
import com.kayu.param.IBaseParam;
import com.kayu.result.OpenApiBaseResult;
import com.kayu.result.OpenApiResult;
import com.kayu.utils.HttpsUtils;
import com.kayu.utils.SignUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by Jin.Z.J  2021/3/24
 */
public class DefaultOpenApiClient implements OpenApiClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultOpenApiClient.class);

    private static final Object[] INVOKE_PARAM = {};

    private static final String UTF_8 = "UTF-8";

    private String host;
    private String md5Key;
    private String merchantNo;

    private DefaultOpenApiClient(String host, String merchantNo, String md5Key) {
        this.host = host;
        this.md5Key = md5Key;
        this.merchantNo = merchantNo;
    }

    @Override
    public <T extends OpenApiBaseResult> T execute(IBaseParam<T> param) throws OpenApiException {
        //url
        String url = this.host + param.requestURI();
        if(logger.isDebugEnabled()){
            logger.debug("KY_SDK_BEGIN -> requestURI: {}",param.requestURI());
        }
        //解析参数
        Map<String,Object> map = Maps.newHashMap();
        BeanUtil.descForEach(param.getClass(),p -> {
            Field field = p.getField();
            ExcludeParam excludeParam = field.getDeclaredAnnotation(ExcludeParam.class);
            if(excludeParam != null){
                return;
            }
            ParamProperty property = field.getDeclaredAnnotation(ParamProperty.class);
            String key = property == null ? p.getFieldName() : property.value();
            Object val = ReflectUtil.invoke(param,p.getGetter(), INVOKE_PARAM);
            map.put(key,val);
        });

        map.put("merchantNo",this.merchantNo);
        map.put("sign", SignUtils.sign(map,this.md5Key));
        //统一设置请求头
        Map<String,String> header = new HashMap<>();
        header.put("User-Agent",String.format("open-sdk-java(version=%s)",param.version()));
        header.put("request-id",param.requestId());
        Response res;
        if (param.reqMethod().equals(RequestMethod.POST)) {
            res = doPost(url,map,header);
        }else{
            res = doGet(url,map,header);
        }
        int statusCode = res.getStatusCode();
        if(res.getStatusCode() == 200){
            OpenApiResult apiResult = JSON.parseObject(res.getResult(), OpenApiResult.class);
            return apiResult.getData(param.resClass());
        }else{
            logger.error("KY_SDK_ERROR -> http状态码:{},响应错误信息:{}", statusCode,res.getResult());
            throw new OpenApiException(String.format("KY_SDK_ERROR -> http状态码:%s,响应错误信息:%s", statusCode,res.getResult()));
        }
    }


    /**
     * post 请求
     * @param url
     * @param params
     * @param header
     * @return
     * @throws OpenApiException
     */
    private Response doPost(String url, Map<String, Object> params, Map<String, String> header) throws OpenApiException {
        try {
            HttpURLConnection connection = getConnection(new URL(url), "POST");
            connection.setRequestProperty("Content-Type","application/json");
            if (header != null && !header.isEmpty()) {
                header.forEach(connection::addRequestProperty);
            }
            try (OutputStream out = connection.getOutputStream()) {
                String jsonParam = JSON.toJSONString(params);
                if(logger.isDebugEnabled()){
                    logger.debug("KY_SDK_BEGIN -> params:{}",jsonParam);
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
     * @param url
     * @param params
     * @param header
     * @return
     * @throws OpenApiException
     */
    private Response doGet(String url, Map<String, Object> params, Map<String, String> header) throws OpenApiException {
        try {
            URI uri = new URIBuilder(url).addParam(params).build();
            if(logger.isDebugEnabled()){
                logger.debug("KY_SDK_BEGIN -> params:{}",uri.getQuery());
            }
            HttpURLConnection connection = getConnection(new URL(uri.toString()), "GET");
            if(!MapUtil.isEmpty(header)){
                header.forEach(connection::setRequestProperty);
            }
            return toResponse(connection);
        } catch (Exception e) {
            throw new OpenApiException(e);
        }
    }


    /**
     * 返回结果
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
                return this.doGet(location, null,null);
            } else if (code >= 200 && code < 300) {
                String contentEncoding = connection.getHeaderField("Content-Encoding");
                if ("gzip".equalsIgnoreCase(contentEncoding)) {
                    stream = new GZIPInputStream(connection.getInputStream());
                } else {
                    stream = connection.getInputStream();
                }
                return new Response(code,streamToString(stream,charset));
            } else {
                stream = connection.getErrorStream();
                String msg = streamToString(stream, charset);
                if (StringUtils.isEmpty(msg)) {
                    msg = connection.getResponseMessage();
                }
                return new Response(code,msg);
            }
        } catch (Exception e) {
            throw new OpenApiException(e);
        }
    }


    /**
     * 字节流转字符
     * @param stream
     * @param charset
     * @return
     */
    private String streamToString(InputStream stream,String charset){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream,charset))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }catch (Exception e){
            return null;
        }
    }


    /**
     * 解析响应字符
     * @param contentType
     * @return
     */
    private String getResponseCharset(String contentType) {
        String charset = UTF_8;
        if (StringUtils.isEmpty(contentType)) {
            return charset;
        }
        String[] params = contentType.split(";");
        for(int i = 0; i < params.length; ++i) {
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



    private static class Response{

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



    private HttpURLConnection getConnection(URL url, String method) throws Exception {
        HttpURLConnection conn;
        if ("https".equals(url.getProtocol())) {
            conn = HttpsUtils.getHttpsConnection(url);
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        conn.setRequestMethod(method);
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(true);
        return conn;
    }


    public static class Builder{

        private String merchantNo;
        private String host;
        private String md5key;


        public Builder merchantNo(String merchantNo){
            this.merchantNo = merchantNo;
            return this;
        }


        public Builder host(String host){
            this.host = host;
            return this;
        }

        public Builder md5key(String md5key){
            this.md5key = md5key;
            return this;
        }

        public Builder useSandBox(){
            this.host = "";
            return this;
        }

        public OpenApiClient build(){
            return new DefaultOpenApiClient(host,merchantNo,md5key);
        }

    }


}
