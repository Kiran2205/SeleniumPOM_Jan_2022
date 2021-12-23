package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void accPageTitleTest() {
        String accountPageTitle = accPage.getAccountPageTitle();
        Assert.assertTrue(accountPageTitle.contains(Constants.ACC_PAGE_TITLE), Error.ERROR_ACCOUNT_PAGE_TITLE);
    }

    @Test
    public void validateAccPageHeadersTest() {
        List<String> accPageSections = accPage.getAccPageSections();
        accPageSections.stream().forEach(e -> System.out.println(e));
        Collections.sort(Constants.EXP_ACC_SEC_HEADERS);
        Assert.assertEquals(accPageSections, Constants.EXP_ACC_SEC_HEADERS);
    }
}