package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountsPage {
    private ElementUtil eleUtil;
    private WebDriver driver;

    private By accPageSecHeaders = By.cssSelector("div#content h2");
    private By yourStoreLoc = By.cssSelector("div#logo a");
    private By linkLogout = By.linkText("Logout");
    private By inputSearchBox = By.xpath("//div[@id='search']/input");
    private By btnSearchProduct = By.xpath("//div[@id='search']//button");

    public AccountsPage(WebDriver driver) {
        this.eleUtil = new ElementUtil(driver);
        this.driver = driver;
    }

    public String getAccountPageTitle() {
        return eleUtil.waitForTitle(10, Constants.ACC_PAGE_TITLE);
    }

    public List<String> getAccPageSections() {
        List<String> accPageText= new ArrayList<String>();
        List<WebElement> accPageHeaders = eleUtil.waitForVisibilityOfElements(accPageSecHeaders, 10);

        for (WebElement element :
                accPageHeaders) {
            accPageText.add(element.getText());
        }
        Collections.sort(accPageText);
        return accPageText;
    }

    public SearchResultsPage searchProduct(String prodToSearch) {
        eleUtil.doSendKeys(inputSearchBox, prodToSearch);
        eleUtil.doClick(btnSearchProduct);
        return new SearchResultsPage(driver);
    }
}
