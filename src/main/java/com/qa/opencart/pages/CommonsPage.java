package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonsPage {
    //Search
    //Add to Cart
    private WebDriver driver;

    //Adding By locators(Dummy one's)
    private By loginLink = By.id("Login");

    public CommonsPage(WebDriver driver) {
        this.driver = driver;
    }

    //Adding public page actions
    public void goToLogin() {
        System.out.println("This is dummy page action for Commons Page");
    }
}
