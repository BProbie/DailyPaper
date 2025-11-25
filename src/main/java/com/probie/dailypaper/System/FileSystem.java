package com.probie.dailypaper.System;

import java.io.IOException;
import java.net.URISyntaxException;
import com.probie.dailypaper.System.Interface.IFileSystem;

public class FileSystem extends ComputerSystem implements IFileSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static FileSystem INSTANCE;

    /**
     * 重写读取远程文件内容的方法使适配加速器等网络代理模式
     * */
    @Override
    public String readRemoteFile(String path) {
        try {
            return IFileSystem.super.readRemoteFile(path);
        } catch (URISyntaxException | IOException ignored) {
            trustConnect();
            try {
                return IFileSystem.super.readRemoteFile(path);
            } catch (URISyntaxException | IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * 重写下载方法使适配加速器等网络代理模式
     * */
    @Override
    public boolean download(String urlPath, String fullFilePath) {
        try {
            return IFileSystem.super.download(urlPath, fullFilePath);
        } catch (URISyntaxException | IOException ignored) {
            trustConnect();
            try {
                return IFileSystem.super.download(urlPath, fullFilePath);
            } catch (URISyntaxException | IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static FileSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FileSystem();
        }
        return INSTANCE;
    }

}