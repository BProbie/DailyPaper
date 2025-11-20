package com.probie.dailypaper.System;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.X509Certificate;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import com.probie.dailypaper.System.Interface.ITrustSystem;

public class TrustSystem implements ITrustSystem, X509TrustManager {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static TrustSystem INSTANCE;

    @Override
    public void trustConnect() {
        trustSSL();
        HttpsURLConnection.setDefaultHostnameVerifier((urlHostName, session) -> true);
    }

    @Override
    public void trustSSL() {
        try {
            TrustSystem[] trustSystem = new TrustSystem[] {new TrustSystem()};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null,trustSystem,null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (NoSuchAlgorithmException | KeyManagementException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static TrustSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrustSystem();
        }
        return INSTANCE;
    }

}