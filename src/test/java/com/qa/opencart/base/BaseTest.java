package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Properties;

//If there is some issue w.r.t Allure report generation even after adding listener in testNG.xml
//then in that case we can make a listener entry as below in BaseTest.

@Listeners(TestAllureListener.class)
public class BaseTest {
    private DriverFactory df;
    private WebDriver driver;
    protected Properties prop;

    protected LoginPage loginPage;
    protected AccountsPage accPage;
    protected SearchResultsPage searchPage;
    protected ProductInfoPage prodInfoPage;
    protected RegistrationPage registrationPage;

    @Parameters({"browser"})
    @BeforeTest
    public void setUp(@Optional("chrome") String browserName) {
        df = new DriverFactory();
        prop = df.init_properties();
        prop.setProperty("browser", browserName);
        driver = df.init_driver(prop);
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
