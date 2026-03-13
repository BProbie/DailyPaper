package com.probie.dailypaper.System.Interface;

import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import okhttp3.OkHttpClient;

public interface INetworkSystem {

    /**
     * 信任连接
     * */
    void trustConnect();

    /**
     * 信任 SSL 连接
     * */
    void trustSSL();

    /**
     * 检测电脑是否已经连接到网络
     * @return 是否有网络
     * */
    default boolean getHasNetwork() {
        try (Response response = new OkHttpClient.Builder().build().newCall(new Request.Builder().url("https://www.baidu.com/").build()).execute()) {
            return response.isSuccessful();
        } catch (IOException ignored) {}
        return false;
    }

}