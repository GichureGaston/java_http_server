package com.gaston.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends  Thread {
    public Socket socket;
    public InputStream inputStream;
    public OutputStream outputStream;
    public final static Logger LOGGER =  LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    public HttpConnectionWorkerThread(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try{
            LOGGER.info("Connection Accepted:{}", socket.getInetAddress());
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();

    //Write
    String html = "<html><head></head><body><h1>Hello Gustav</h1></body></html>";
    final  String CRLF = "\n\r";
    String response = "HTTP/1.1  200 OK"+CRLF+"Content Length:"+html.getBytes().length +CRLF+
            CRLF+
            html+
            CRLF+ CRLF;
            outputStream.write(response.getBytes());




    } catch (IOException e) {
    LOGGER.error("Problem With Communication:");
        }finally {
            if(inputStream!= null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if(outputStream!= null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }if(socket!= null){
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        LOGGER.info("Connection Processing Finished:{}", socket.getInetAddress());


}
    }
