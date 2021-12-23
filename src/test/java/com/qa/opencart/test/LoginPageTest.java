package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Description("This is Login Page Title Test")
    @Severity(SeverityLevel.TRIVIAL)
    @Test(priority = 1)
    public void loginTitleTest() {
        String loginPageTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(loginPageTitle , Constants.LOGIN_PAGE_TITLE);
    }

    @Description("This is Login Page URL Test")
    @Severity(SeverityLevel.TRIVIAL)
    @Test(priority = 2, enabled = false)
    public void loginPageUrlTest() {
        String loginPageUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(loginPageUrl.contains(Constants.LOGIN_PAGE_URL_VALUE));
    }

    @Description("This is Forgot Password link Check Test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void chkForgotPwdLinkTest() {
        boolean forgotPwdLinkExist = loginPage.isForgotPwdLinkExist();
        Assert.assertTrue(forgotPwdLinkExist);
    }

    @Description("This is Login Test")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 4)
    public void doLoginTest() {
        loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] loginNegativeTestData() {
        return new Object[][]{{"teast@gmail.com", "Pass123"}, {" ", " "}, {" ", " Test"}};
    }

    @Description("This test is ti check login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "loginNegativeTestData")
    public void loginNegativeTest(String userName, String password) {
        boolean isErrorDisplayed = loginPage.doLoginWithWrongData(userName, password);
        Assert.assertTrue(isErrorDisplayed);
    }
}