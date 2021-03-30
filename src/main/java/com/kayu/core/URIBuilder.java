package com.kayu.core;

import com.kayu.param.IObject;
import com.kayu.utils.BeanUtils;
import com.kayu.utils.MapUtils;
import com.kayu.utils.StringUtils;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Jin.Z.J  2021/3/24
 */
public class URIBuilder {

    private static final String UTF_8 = "UTF-8";

    private String uri;
    private Map<String, Object> map = MapUtils.newHashMap();

    public URIBuilder(String uri) {
        this.uri = uri;
    }

    public URIBuilder addParam(String key, Object value) {
        map.put(key, value);
        return this;
    }


    public <T extends IObject> URIBuilder addParam(T param) {
        if (param != null) {
            BeanUtils.beanToMap(param, true).forEach(map::put);
        }
        return this;
    }


    public URIBuilder addParam(Map<String, Object> map) {
        if (!MapUtils.isEmpty(map)) {
            map.forEach(this.map::put);
        }
        return this;
    }


    public URI build() {
        String finalUri;
        if (this.map.isEmpty()) {
            finalUri = this.uri;
        } else {
            StringBuilder builder = new StringBuilder();
            this.map.forEach((k, v) -> {
                if (StringUtils.isNotEmpty(k) && v != null) {
                    String attr = strEncode(k);
                    String val = strEncode(v.toString());
                    builder.append("&").append(attr).append("=").append(val);
                }
            });
            if (this.uri.lastIndexOf("?") != -1) {
                StringBuilder urlBuilder = new StringBuilder(this.uri);
                if (this.uri.endsWith("?")) {
                    urlBuilder.append(builder.substring(1, builder.length()));
                } else {
                    urlBuilder.append(builder);
                }
                finalUri = urlBuilder.toString();
            } else {
                finalUri = new StringBuilder(this.uri).append("?").append(builder.substring(1, builder.length())).toString();
            }
        }
        return URI.create(finalUri);
    }


    private String strEncode(String val) {
        try {
            return URLEncoder.encode(val, UTF_8);
        } catch (Exception e) {
            return val;
        }
    }

}
