package com.gaston.httpserver;

import com.gaston.httpserver.config.Configuration;
import com.gaston.httpserver.config.ConfigurationManager;

import java.util.Arrays;

// Driver class for my http server
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server Start!");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Using Port : "+conf.getPort());
        System.out.println("Using WebRoot : "+conf.getWebroot());


    }
}
