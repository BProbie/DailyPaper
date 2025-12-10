package com.probie.dailypaper.System.Interface;

import com.alibaba.fastjson2.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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