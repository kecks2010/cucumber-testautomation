package de.mirko_werner.cucumber.utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        BufferedReader  bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            try {
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties from " + filePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Properties file not found " + filePath);
        }

        return properties;
    }
}
