package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends Page {

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[3]/div[1]/div/div[2]/div/div/input")
    public WebElement nameInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[3]/div[1]/div/div[3]/small")
    public WebElement errorNameInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[3]/div[2]/div/div[2]/div/div/input")
    public WebElement surnameInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[3]/div[2]/div/div[3]/small")
    public WebElement errorSurnameInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[6]/div[2]/div/div/div/div[1]/div/div/div/div/div[1]/input")
    public WebElement dateOfBirthDaySelector;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[6]/div[2]/div/div/div/div[3]/div/div/div/div[1]/input")
    public WebElement dateOfBirthMonthSelector;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[6]/div[2]/div/div/div/div[5]/div/div/div/div/div[1]/input")
    public WebElement dateOfBirthYearSelector;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[6]/div[3]/small")
    public WebElement errorDateOfBirth;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[9]/div[2]/div/label[1]/div[1]/input")
    public WebElement maleGenderInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[9]/div[2]/div/label[2]/div[1]/input")
    public WebElement femaleGenderInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[9]/div[3]/small")
    public WebElement errorGenderInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[12]/div/div[2]/div[1]/div/div/div[1]/div/input")
    public WebElement mailboxNameInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[12]/div/div[3]/div/div[3]/small")
    public WebElement errorEmptyMailboxName;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[12]/div/div[3]/div/div[3]/small")
    public WebElement errorLengthMailboxName;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[12]/div/div[3]/div/div[3]/small")
    public WebElement errorExistsMailboxName;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[12]/div/div[3]/div/div[3]/small")
    public WebElement errorCyrillicLettersMailboxName;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[12]/div/div[3]/div/div[3]/small")
    public WebElement errorInvalidEnteredMailboxName;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[15]/div[1]/div[2]/div[1]/div/div/div/input")
    public WebElement passwordInput;

    //
    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[15]/div[1]/div[3]/small")
    public WebElement errorEmptyPassword;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[15]/div[1]/div[3]/small")
    public WebElement errorLengthPassword;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[15]/div[1]/div[3]/small")
    public WebElement errorInvalidPassword;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[15]/div[6]/div[2]/div/div/input")
    public WebElement repeatPasswordInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[15]/div[6]/div[3]/small")
    public WebElement errorEmptyRepeatPassword;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[15]/div[6]/div[3]/small")
    public WebElement errorPasswordMatch;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[18]/div/div/div[2]/div[1]/div/div/div[3]/div/input")
    public WebElement phoneNumberInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[18]/div/div/div[3]/small")
    public WebElement errorIncorrectPhoneNumberInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[18]/span/a")
    public WebElement redirectBackupMailbox;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[18]/span/a")
    public WebElement redirectPhoneNumber;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[18]/div/div[2]/div[1]/div/div/div/input")
    public WebElement backupMailboxInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/div[18]/div/div[3]/small")
    public WebElement errorIncorrectBackupMailboxInput;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[4]/div[4]/div/div/div/div/form/button")
    public WebElement createButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        init();
    }

    public void init() {
        PageFactory.initElements(driver, this);
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.click();
        nameInput.sendKeys(name);
    }

    public void enterSurname(String surname) {
        wait.until(ExpectedConditions.visibilityOf(surnameInput));
        surnameInput.click();
        surnameInput.sendKeys(surname);
    }
    public void enterMailboxName(String mailboxName) {
        wait.until(ExpectedConditions.visibilityOf(mailboxNameInput));
        mailboxNameInput.click();
        mailboxNameInput.sendKeys(mailboxName);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.click();
        passwordInput.sendKeys(password);
    }

    public void enterRepeatPassword(String repeatPassword) {
        wait.until(ExpectedConditions.visibilityOf(repeatPasswordInput));
        repeatPasswordInput.click();
        repeatPasswordInput.sendKeys(repeatPassword);
    }

    public void enterPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOf(phoneNumberInput));
        phoneNumberInput.click();
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void enterBackupMailbox(String backupMailbox) {
        wait.until(ExpectedConditions.visibilityOf(backupMailboxInput));
        backupMailboxInput.click();
        backupMailboxInput.sendKeys(backupMailbox);
    }

    public void goToRedirectBackupMailbox() {
        wait.until(ExpectedConditions.visibilityOf(redirectBackupMailbox));
        redirectBackupMailbox.click();
    }

    public void goToRedirectPhoneNumber() {
        wait.until(ExpectedConditions.visibilityOf(redirectPhoneNumber));
        redirectPhoneNumber.click();
    }
    public void clickCreateButton() {
        wait.until(ExpectedConditions.visibilityOf(createButton));
        createButton.click();
    }

    public String getErrorNameInputText() {
        wait.until(ExpectedConditions.visibilityOf(errorNameInput));
        return errorNameInput.getText();
    }

    public String getErrorSurnameInputText() {
        wait.until(ExpectedConditions.visibilityOf(errorSurnameInput));
        return errorSurnameInput.getText();
    }

    public String getErrorDateOfBirthText() {
        wait.until(ExpectedConditions.visibilityOf(errorDateOfBirth));
        return errorDateOfBirth.getText();
    }

    public String getErrorGenderInputText() {
        wait.until(ExpectedConditions.visibilityOf(errorGenderInput));
        return errorGenderInput.getText();
    }

    public String getErrorEmptyMailboxNameText() {
        wait.until(ExpectedConditions.visibilityOf(errorEmptyMailboxName));
        return errorEmptyMailboxName.getText();
    }

    public String getErrorLengthMailboxNameText() {
        wait.until(ExpectedConditions.visibilityOf(errorLengthMailboxName));
        return errorLengthMailboxName.getText();
    }

    public String getErrorExistsMailboxNameText() {
        wait.until(ExpectedConditions.visibilityOf(errorExistsMailboxName));
        return errorExistsMailboxName.getText();
    }

    public String getErrorCyrillicLettersMailboxNameText() {
        wait.until(ExpectedConditions.visibilityOf(errorCyrillicLettersMailboxName));
        return errorCyrillicLettersMailboxName.getText();
    }

    public String getErrorInvalidEnteredMailboxNameText() {
        wait.until(ExpectedConditions.visibilityOf(errorInvalidEnteredMailboxName));
        return errorInvalidEnteredMailboxName.getText();
    }

    public String getErrorEmptyPasswordText() {
        wait.until(ExpectedConditions.visibilityOf(errorEmptyPassword));
        return errorEmptyPassword.getText();
    }

    public String getErrorLengthPasswordText() {
        wait.until(ExpectedConditions.visibilityOf(errorLengthPassword));
        return errorLengthPassword.getText();
    }

    public String getErrorInvalidPasswordText() {
        wait.until(ExpectedConditions.visibilityOf(errorInvalidPassword));
        return errorInvalidPassword.getText();
    }

    public String getErrorEmptyRepeatPasswordText() {
        wait.until(ExpectedConditions.visibilityOf(errorEmptyRepeatPassword));
        return errorEmptyRepeatPassword.getText();
    }

    public String getErrorPasswordMatchText() {
        wait.until(ExpectedConditions.visibilityOf(errorPasswordMatch));
        return errorPasswordMatch.getText();
    }

    public String getErrorIncorrectPhoneNumberInputText() {
        wait.until(ExpectedConditions.visibilityOf(errorIncorrectPhoneNumberInput));
        return errorIncorrectPhoneNumberInput.getText();
    }

    public String getErrorIncorrectBackupMailboxInputText() {
        wait.until(ExpectedConditions.visibilityOf(errorIncorrectBackupMailboxInput));
        return errorIncorrectBackupMailboxInput.getText();
    }

}