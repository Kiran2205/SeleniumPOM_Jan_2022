package com.qa.opencart.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

/**
 * @author Kiran
 */
public class DriverFactory {
    private WebDriver driver;
    private Properties prop;
    private OptionsManager oManager;

    public static String highlight;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * This method is to instantiate WebDriver
     *
     * @Param prop
     * @return this method will return {@link WebDriver}
     */
    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        highlight = prop.getProperty("highlight").trim();
        System.out.println("Browser name is " + browserName);
        oManager = new OptionsManager(prop);

        switch (browserName.toLowerCase()) {
            case "chrome":
                System.out.println("Launching chrome browser");
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver(oManager.getChromeOptions()));
//                driver = new ChromeDriver(oManager.getChromeOptions());
                break;
            case "firefox":
                System.out.println("Launching firefox browser");
                WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver(oManager.getFireFoxOptions()));
//                driver = new FirefoxDriver(oManager.getFireFoxOptions());
                break;
            case "safari":
                System.out.println("Launching safari browser");
                tlDriver.set(new SafariDriver());
//                driver = new SafariDriver();
                break;
            default:
                System.out.println("Browser " + browserName + " is invalid.! Please provide correct browser");
        }
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().get(prop.getProperty("url"));

       return DriverFactory.getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * This method is used to initialise properties file
     *
     * @return this will return instance of {@link Properties}
     */
    public Properties init_properties() {
        FileInputStream fis = null;
        prop = new Properties();
        //defining system variable for getting env for the test to run, at run time from cmd
        String env = System.getProperty("env");
        if (env == null) {
            System.out.println("Running on environment PROD");
            try {
                fis = new FileInputStream("./src/test/resources/config/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Running on environment "+env);
            try {
                switch (env) {
                    case "qa":
                        fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "uat":
                        fis = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;
                    default:
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public String getScreenshot() {
        File source = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public String getScreenshotAsBase64() {
        String base64ImagePath = null;
        File source = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(source, destination);
            byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(destination));
            base64ImagePath = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64ImagePath;
    }

    public String getBase64() {
        String base64ImagePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        return base64ImagePath;
    }

}
