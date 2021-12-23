package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationPageTest extends BaseTest {

    @BeforeClass
    public void registrationPageSetup() {
        registrationPage = loginPage.clickRegisterLink();
    }

    public String generateRandomEmail() {
        Random random = new Random();
        String email = "testAutUser"+random.nextInt(10000)+"@gmail.com";
        return email;
    }

    @DataProvider
    public Object[][] getRegistrationPageTestData() {
        Object regData[][] = ExcelUtil.getTestData(Constants.REGISTERPAGE_TESTDATASHEET_NAME);
        return regData;
    }

    @Test(dataProvider = "getRegistrationPageTestData")
    public void accountRegistrationTest(String fName, String lName, String telephone, String password, String subscribe) {
        boolean isRegistered = registrationPage.doAccountRegistration(fName, lName, generateRandomEmail(),
                telephone, password, subscribe);
        Assert.assertTrue(isRegistered);
    }

}
