package de.mirko_werner.cucumber.utils;

import java.util.Properties;

public class ConfigLoader {
     private final Properties properties;
     private static ConfigLoader instance;

     private ConfigLoader() {
         properties = PropertyUtils.loadProperties("src/test/resources/config.properties");
     }

     public static ConfigLoader getInstance() {
         if (instance == null) {
             instance = new ConfigLoader();
         }
         return instance;
     }

     public String getBaseUrl(String environment) {
         return switch (environment) {
             case "dev" -> properties.getProperty("environment.dev.baseUrl");
             case "int" -> properties.getProperty("environment.int.baseUrl");
             case "prod" -> properties.getProperty("environment.prod.baseUrl");
             default -> properties.getProperty("environment.default.baseUrl");
         };
     }

     public String getDriverPath(String browser) {
         return switch (browser) {
             case "chrome" -> properties.getProperty("driver.windows.chromePath");
             case "firefox" -> properties.getProperty("driver.windows.firefoxPath");
             default -> throw new IllegalStateException("Unknown browser: " + browser);
         };
     }
}
