package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {
    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            co.addArguments(prop.getProperty("headless"));
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments(prop.getProperty("incognito"));
        }
        return co;
    }

    public FirefoxOptions getFireFoxOptions() {
        fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            fo.addArguments(prop.getProperty("headless"));
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments(prop.getProperty("incognito"));
        }
        return fo;
    }
}
