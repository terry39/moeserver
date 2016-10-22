package com.github.timeloveboy.moeserver.ServerDriver.sun;

import com.github.timeloveboy.moeserver.IHttpServer;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

/**
 * Created by timeloveboy on 2016/10/22.
 */
public class sunServer implements IHttpServer {
    private HttpServer server;
    private String modulePath;

    @Override
    public void create(InetSocketAddress addr) {
        try {
            server = HttpServer.create(addr, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createContext(String ModulePath) {
        this.modulePath = ModulePath;
    }

    @Override
    public void start() {
        server.createContext("/", new sunRouterDispatcher(modulePath));//所有的路由,都交给Mainhandler处理
        server.setExecutor(null);
        server.start();
    }
}
