package com.gaston.httpserver;

import com.gaston.httpserver.config.Configuration;
import com.gaston.httpserver.config.ConfigurationManager;
import com.gaston.httpserver.config.HttpConfigurationException;
import com.gaston.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

// Driver class for my http server
public class HttpServer {
    public final static Logger LOGGER =  LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {

        LOGGER.info("Server Start!");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("Using Port : {}", conf.getPort());
        LOGGER.info("Using WebRoot : {}", conf.getWebroot());
        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(),conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }


    }
}
