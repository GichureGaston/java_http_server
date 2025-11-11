package com.gaston.httpserver.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.gaston.httpserver.config.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
 private static ConfigurationManager myConfigurationManager;
 private static Configuration myCurrentConfiguration;
 private ConfigurationManager (){};
public static ConfigurationManager getInstance(){
    if(myConfigurationManager == null)
        myConfigurationManager = new ConfigurationManager();
    return myConfigurationManager;
}
public void loadConfigurationFile(String filePath) throws IOException {
    FileReader fileReader = new FileReader(filePath);
    StringBuffer stringBuffer = new StringBuffer();
    int i ;
    while  (( i = fileReader.read()) != -1){
 stringBuffer.append((char)i);
    }
    JsonNode conf = Json.parse(stringBuffer.toString());
    myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
}
    public Configuration getCurrentConfiguration(){
    if(myCurrentConfiguration == null){

    }
    return myCurrentConfiguration;
    }
}

