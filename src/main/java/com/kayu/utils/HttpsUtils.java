package com.kayu.utils;

import javax.net.ssl.*;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Jin.Z.J  2021/3/26
 */
public class HttpsUtils {

    private static HostnameVerifier verifier = (hostname, session) -> true;
    private static SSLSocketFactory socketFactory;

    static {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            ctx.getClientSessionContext().setSessionTimeout(15);
            ctx.getClientSessionContext().setSessionCacheSize(1000);
            socketFactory = ctx.getSocketFactory();
        } catch (Exception var1) {
        }
    }


    public static HostnameVerifier getVerifier() {
        return verifier;
    }

    public static SSLSocketFactory getSocketFactory() {
        return socketFactory;
    }

    /**
     * 获取https连接
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpsURLConnection getHttpsConnection(URL url) throws Exception {
        HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
        connHttps.setHostnameVerifier(verifier);
        connHttps.setSSLSocketFactory(socketFactory);
        return connHttps;
    }

    private static class DefaultTrustManager implements X509TrustManager {
        private DefaultTrustManager() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }

    public static void defaultSSLSocketFactory() {
        HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
    }

}
