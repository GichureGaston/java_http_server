package com.gaston.httpserver;

import com.gaston.httpserver.config.Configuration;
import com.gaston.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

// Driver class for my http server
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server Start!");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Using Port : "+conf.getPort());
        System.out.println("Using WebRoot : "+conf.getWebroot());
        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();



            //Write
            String html = "<html><head></head><body><h1>Hello World</h1></body></html>";
             String response = "";



            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
