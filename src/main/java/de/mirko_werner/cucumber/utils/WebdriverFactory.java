package de.mirko_werner.cucumber.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebdriverFactory {

    private static final ThreadLocal<WebDriver> THREAD_LOCAL_DRIVER = new ThreadLocal<>();

    private static void initializeDriver() {
        WebDriver driver;
        String browser = System.getProperty("browser", "chrome");
        switch (browser) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", ConfigLoader.getInstance().getDriverPath(browser));
                ChromeOptions options = new ChromeOptions();
                options.setCapability("webSocketUrl", true);
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                System.setProperty("webdriver.firefox.driver", ConfigLoader.getInstance().getDriverPath(browser));
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("webSocketUrl", true);
                options.addArguments("--headless");
                driver = new FirefoxDriver(options);
            }
            default -> throw new IllegalStateException("Unknown browser: " + browser);
        }
        driver.manage().window().maximize();
        WebdriverFactory.THREAD_LOCAL_DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        if (THREAD_LOCAL_DRIVER.get() == null || THREAD_LOCAL_DRIVER.get().toString().contains("(null)")) {
            initializeDriver();
        }
        return THREAD_LOCAL_DRIVER.get();
    }
}
