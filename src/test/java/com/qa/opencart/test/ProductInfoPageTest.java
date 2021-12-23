package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void productInfoPageSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] searchProductTestData() {
        return new Object[][] {{"Macbook"},{"iMac"},{"iPhone"}};
    }


    @Test(dataProvider = "searchProductTestData")
    public void getCountOfProdSearchTest(String productName) {
        searchPage = accPage.searchProduct(productName);
        int countOfSearchItem = searchPage.getCountOfSearchItem();
        Assert.assertTrue(countOfSearchItem > 0);
    }

    @Test
    public void getProductInfoTest() {
        searchPage = accPage.searchProduct("Macbook");
        prodInfoPage = searchPage.selectProduct("MacBook");
        Map<String, String> actualProdDetails = prodInfoPage.getProductMetaData();

        actualProdDetails.forEach((k,v)-> System.out.println(k+" : "+v));

        softAssert.assertEquals(actualProdDetails.get(Constants.KEY_PRODUCT_NAME), "MacBook");
        softAssert.assertEquals(actualProdDetails.get("Brand"), "Apple");
        softAssert.assertEquals(actualProdDetails.get("Product Code"), "Product 16");
        softAssert.assertEquals(actualProdDetails.get("Reward Points"), "600");
        softAssert.assertEquals(actualProdDetails.get("Availability"), "Out Of Stock");
        softAssert.assertEquals(actualProdDetails.get(Constants.KEY_PRODUCT_PRICE), "$500.00");
        softAssert.assertEquals(actualProdDetails.get("Ex Tax"), "$500.00");
        
        softAssert.assertAll(Error.ERROR_PROD_INFO_ASSERTION);

    }

}