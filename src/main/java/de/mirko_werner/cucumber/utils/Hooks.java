package de.mirko_werner.cucumber.utils;

import io.cucumber.java.After;

public class Hooks {

    @After
    public void after() {
        WebdriverFactory.getDriver().quit();
    }
}
