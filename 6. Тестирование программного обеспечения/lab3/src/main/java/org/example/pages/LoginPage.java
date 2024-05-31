package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page{
    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div/div/a")
    public WebElement redirectToRegisterLink;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/div/form/div[2]/div[2]/div[1]/div/div/div/div/div/div[1]/div/input")
    public WebElement emailInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/div/form/div[2]/div[2]/div[3]/div/div/div[1]/button")
    public WebElement enterPasswordBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/div/form/div[2]/div[2]/div[1]/div/div/div/div[2]/small")
    public WebElement noAccountError;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/div/form/div[3]/div[2]/div/a")
    public WebElement redirectToRecoveryLink;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/div/form/div[2]/div/div[2]/div/div/div/div/div/input")
    public WebElement passwordInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/div/form/div[2]/div/div[3]/div/div/div[1]/div/button")
    public WebElement loginButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/div/form/div[2]/div/div[2]/div/div/div[2]/small/div")
    public WebElement noPasswordError;

    @FindBy(xpath = "//div[contains(@class, \"ph-container\")]//button[contains(@class, \"ph-project\")]")
    public WebElement allProjectsButton;

    @FindBy(xpath = "//div[contains(@class, \"ph-projects-group\")]//li[contains(@class, \"ph-project\")]/a[1]")
    public WebElement autoLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        init();
    }

    public void init() {
        PageFactory.initElements(driver, this);
    }

    public void goToRegister(){
        wait.until(ExpectedConditions.visibilityOf(redirectToRegisterLink));
        redirectToRegisterLink.click();
    }

    public void goToRecovery(){
        wait.until(ExpectedConditions.visibilityOf(redirectToRecoveryLink));
        redirectToRecoveryLink.click();
    }

    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.click();
        emailInput.sendKeys(email);
    }

    public void clickOnEnterPasswordBtn(){
        wait.until(ExpectedConditions.visibilityOf(enterPasswordBtn));
        enterPasswordBtn.click();
    }

    public String getErrorText(){
        wait.until(ExpectedConditions.visibilityOf(noAccountError));
        return noAccountError.getText();
    }

    public void enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
    }

    public void clickOnLoginButton(){
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    }

    public String getPasswordErrorText(){
        wait.until(ExpectedConditions.visibilityOf(noPasswordError));
        return noPasswordError.getText();
    }

}
