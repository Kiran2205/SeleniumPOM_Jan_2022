package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By searchResItems = By.xpath("//div[@class='product-thumb']//div[@class='caption']//a");


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.eleUtil = new ElementUtil(driver);
    }

    public int getCountOfSearchItem() {
        List<WebElement> resultList = eleUtil.waitForVisibilityOfElements(searchResItems, 10);
        return resultList.size();
    }

    public ProductInfoPage selectProduct(String searchItem) {
        List<WebElement> searchResList = eleUtil.waitForVisibilityOfElements(searchResItems, 10);
        for (WebElement e :
                searchResList) {
            if (e.getText().trim().equals(searchItem)) {
                e.click();
                break;
            }
        }
        return new ProductInfoPage(driver);
    }

}
