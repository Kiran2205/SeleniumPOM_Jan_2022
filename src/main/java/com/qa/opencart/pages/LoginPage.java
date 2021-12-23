package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    //1. Private By locators
    private By inputUserName = By.id("input-email");
    private By inputPassword = By.xpath("//input[@id='input-password']");
    private By linkForgotPwd = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
    private By btnLogin = By.xpath("//input[@type='submit']");
    private By registerLink = By.xpath("//aside[@id='column-right']//a[text()='Register']");
    private By registerPageHeading = By.cssSelector("div#content>h1");
    private By loginErrorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

    //2. Constructors
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //3. Public Page actions (methods)
    @Step("This step will get the login page title")
    public String getLoginPageTitle() {
        return eleUtil.waitForTitle(5, Constants.LOGIN_PAGE_TITLE);
    }

    @Step("This step will get login page url")
    public String getLoginPageUrl() {
        return eleUtil.getPageUrl();
//        return driver.getCurrentUrl();
    }

    @Step("This step will check whether forgot password link exists")
    public boolean isForgotPwdLinkExist() {
        return eleUtil.doIsDisplayed(linkForgotPwd);
//        return driver.findElement(linkForgotPwd).isDisplayed();
    }

    @Step("This step is used to do login for Open Cart application with username : {0} and password : {1}")
    public AccountsPage doLogin(String userName, String password) {
        eleUtil.doSendKeys(inputUserName, userName);
        eleUtil.doSendKeys(inputPassword, password);
        eleUtil.doClick(btnLogin);
        return new AccountsPage(driver);
        /*driver.findElement(inputUserName).sendKeys(userName);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(btnLogin).click();*/
    }

    @Step("This step is used to verify opencart login with invalid credentials")
    public boolean doLoginWithWrongData(String userName, String password) {
        eleUtil.doSendKeys(inputUserName, userName);
        eleUtil.doSendKeys(inputPassword, password);
        eleUtil.doClick(btnLogin);
        return eleUtil.doIsDisplayed(loginErrorMsg);
    }

    @Step("This step is used to click on register link")
    public RegistrationPage clickRegisterLink() {
        eleUtil.doClick(registerLink);
        eleUtil.waitForElementVisible(registerPageHeading, 10);
        return new RegistrationPage(driver);
    }
}
