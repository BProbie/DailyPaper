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
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static FileSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FileSystem();
        }
        return INSTANCE;
    }

    /**
     * 重写读取远程文件内容的方法使适配加速器等网络代理模式
     * @param fullRemoteUriPath 完整远程文件网址
     * @return 文件内容
     * */
    @Override
    public String readRemoteFile(String fullRemoteUriPath) {
        try {
            return IFileSystem.super.readRemoteFile(fullRemoteUriPath);
        } catch (URISyntaxException | IOException ignored) {
            trustConnect();
            try {
                return IFileSystem.super.readRemoteFile(fullRemoteUriPath);
            } catch (URISyntaxException | IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * 重写下载方法使适配加速器等网络代理模式
     * @param fullRemoteUriPath 完整远程文件网址
     * @param fullFilePath 完整本地文件路径
     * @return 是否下载成功
     * */
    @Override
    public boolean download(String fullRemoteUriPath, String fullFilePath) {
        try {
            return IFileSystem.super.download(fullRemoteUriPath, fullFilePath);
        } catch (URISyntaxException | IOException ignored) {
            trustConnect();
            try {
                return IFileSystem.super.download(fullRemoteUriPath, fullFilePath);
            } catch (URISyntaxException | IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

}