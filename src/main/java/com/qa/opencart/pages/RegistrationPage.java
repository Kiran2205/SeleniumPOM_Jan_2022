package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By firstNameLoc = By.xpath("//div[@class='form-group required']//input[@id='input-firstname']");
    private By lastNameLoc = By.xpath("//div[@class='form-group required']//input[@id='input-lastname']");
    private By emailIdLoc = By.xpath("//div[@class='form-group required']//input[@id='input-email']");
    private By telephoneLoc = By.xpath("//div[@class='form-group required']//input[@id='input-telephone']");
    private By passwordLoc = By.xpath("//div[@class='form-group required']//input[@id='input-password']");
    private By confirmPwdLoc = By.xpath("//div[@class='form-group required']//input[@id='input-confirm']");
    private By subscribeYesLoc = By.xpath("//div[@class='form-group']//label[text()[normalize-space()='Yes']]/input");
    private By subscribeNoLoc = By.xpath("//div[@class='form-group']//label[text()[normalize-space()='No']]/input");
    private By agreeChkBoxLoc = By.xpath("//input[@name='agree']");
    private By continueBtnLoc = By.xpath("//input[@value='Continue']");
    private By successMsgLoc = By.cssSelector("div#content h1");
    private By logoutLinKLoc = By.linkText("Logout");
    private By registerLinkLoc = By.linkText("Register");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public boolean doAccountRegistration(String firstName, String lastName, String email, String telephone, String password,
                                         String subscribe) {
        eleUtil.doSendKeys(firstNameLoc, firstName);
        eleUtil.doSendKeys(lastNameLoc, lastName);
        eleUtil.doSendKeys(emailIdLoc, email);
        eleUtil.doSendKeys(telephoneLoc, telephone);
        eleUtil.doSendKeys(passwordLoc, password);
        eleUtil.doSendKeys(confirmPwdLoc, password);
        if (subscribe.equalsIgnoreCase("yes")) {
            eleUtil.doClick(subscribeYesLoc);
        } else {
            eleUtil.doClick(subscribeNoLoc);
        }
        eleUtil.doClick(agreeChkBoxLoc);
        eleUtil.doClick(continueBtnLoc);
        WebElement successEle = eleUtil.waitForElementVisible(successMsgLoc, 10);
        if (successEle.getText().contains(Constants.REGISTRATION_SUCCESS_MSG)) {
            eleUtil.doClick(logoutLinKLoc);
            eleUtil.doClick(registerLinkLoc);
            return true;
        } else {
            return false;
        }
    }


}
