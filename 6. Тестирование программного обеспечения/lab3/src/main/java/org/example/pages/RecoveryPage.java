package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RecoveryPage extends Page{
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[3]/div/div/div/form/div/div[3]/div[2]/div/div[1]/div/input")
    public WebElement emailInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div[3]/div/div/div/form/div/button[1]/span")
    public WebElement continueButton;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div[3]/div/div/div/form/div/div[3]/div[3]/small")
    public WebElement errorText;


    public RecoveryPage(WebDriver driver) {
        super(driver);
        init();
    }

    public void init() {
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.click();
        emailInput.sendKeys(email);
    }

    public void clickOnContinueButton(){
        wait.until(ExpectedConditions.visibilityOf(continueButton));
        continueButton.click();
    }

    public String getErrorText(){
        wait.until(ExpectedConditions.visibilityOf(errorText));
        return errorText.getText();
    }

}
