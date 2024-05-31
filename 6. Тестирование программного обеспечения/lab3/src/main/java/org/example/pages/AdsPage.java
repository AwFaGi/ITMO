package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public abstract class AdsPage extends Page{

    @FindBy(xpath = "//iframe[@class=\"rb-fs__frame\"]")
    public WebElement ads;

    public AdsPage(WebDriver driver) {
        super(driver);
    }

    public void waitAds(){
        try {
            wait.wait(2000);
        } catch (Exception ignored){

        }
        wait.until(invisibilityOf(ads));
    }

}
