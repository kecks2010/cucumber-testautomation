package de.mirko_werner.cucumber.models;

import de.mirko_werner.cucumber.utils.WebdriverFactory;
import de.mirko_werner.cucumber.utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class PageObject {

    protected WebDriver webDriver;
    protected WebDriverWait wait;

    protected PageObject() {
        this.getWebDriver();
    }

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            this.webDriver = WebdriverFactory.getDriver();
            this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            PageFactory.initElements(webDriver, this);
        }
        return webDriver;
    }

    public void openUrl() {
        this.getWebDriver().get(this.getUrl());
    }

    protected String getUrl() {
        String endpoint = "";

        if (this.getClass().getAnnotation(EndPoint.class) != null) {
            endpoint = this.getClass().getAnnotation(EndPoint.class).value();
        }
        return ConfigLoader.getInstance().getBaseUrl(System.getProperty("environment", "default")) + endpoint;
    }
}
