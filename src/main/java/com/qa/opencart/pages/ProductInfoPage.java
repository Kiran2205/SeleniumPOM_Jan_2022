package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By prodHeader = By.xpath("//div[@class='col-sm-4']/h1");
    private By metaDataLoc = By.xpath("//div[@class='col-sm-4']/child::ul[position()=1]/li");
    private By priceData = By.xpath("//div[@class='col-sm-4']/child::ul[position()=2]/li");
    private By inputQuantity = By.cssSelector("input#input-quantity");
    private By btnAddToCart = By.cssSelector("button#button-cart");

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getProductHeader() {
        WebElement selItem = eleUtil.waitForElementVisibleWithElement(prodHeader, 10);
        return selItem.getText();
    }

    public Map<String, String> getProductMetaData() {
        Map<String, String> metaData = new HashMap<>();
        metaData.put(Constants.KEY_PRODUCT_NAME, getProductHeader());

        List<WebElement> metaDataInfoList = eleUtil.waitForVisibilityOfElements(metaDataLoc, 10);
        System.out.println("Size of the meta data information is "+ metaDataInfoList.size());
        for (int i = 0; i < metaDataInfoList.size(); i++) {
            String[] prodDetails = metaDataInfoList.get(i).getText().split(":");
            String metaKey = prodDetails[0].trim();
            String metaValue = prodDetails[1].trim();
            metaData.put(metaKey, metaValue);
        }

        List<WebElement> priceDataList = eleUtil.waitForVisibilityOfElements(priceData, 10);
        for (WebElement e :
                priceDataList) {
            System.out.println(e.getText());
            if (e.getText().contains(":")) {
                String[] priceDataArray = e.getText().split(":");
                metaData.put(priceDataArray[0].trim(), priceDataArray[1].trim());
            } else {
                metaData.put(Constants.KEY_PRODUCT_PRICE, e.getText().trim());
            }
        }
        return metaData;
    }


}
