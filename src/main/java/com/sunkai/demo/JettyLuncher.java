package com.sunkai.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

/**
 * jetty 启动器
 */
public class JettyLuncher {
    public static void main(String[] args) throws Exception {
        Integer port = 8090;
        Server server = new Server(port);
        ProtectionDomain domain = JettyLuncher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());
        server.setHandler(webapp);
        server.start();
        server.join();
    }

}