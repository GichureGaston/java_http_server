package com.gaston.httpserver.config;

import com.gaston.httpserver.config.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }

        StringBuffer stringBuffer = new StringBuffer();
        int i;

        try {
            while ((i = fileReader.read()) != -1) {
                stringBuffer.append((char) i);
            }
            fileReader.close();
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(stringBuffer.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error Parsing Configuration File");
        }

        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing Internal Configuration File");
        }
    }

    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfigurationException("NO Current Configuration Set.");
        }
        return myCurrentConfiguration;
    }
}