package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ExpandedCatalogPage extends AdsPage{
    @FindBy(xpath = "//div[@class=\"p-search\"]/div[contains(@class,\"p-search__item\")][1]//a[@class=\"hdr__text\"]")
    public WebElement firstCar;

    @FindBy(xpath = "//body")
    public WebElement body;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//button")
    public WebElement applyButton;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//div[contains(@class, \"dropdown dropdown_scrollable\")][2]")
    public WebElement allMarksSelector;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//div[contains(@class, \"dropdown dropdown_scrollable\")][2]//div[@class=\"js-multiselect__items\"]/div[1]")
    public WebElement firstMark;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//div[contains(@class, \"dropdown dropdown_scrollable\")][2]//div[@class=\"js-multiselect__items\"]/div[2]")
    public WebElement secondMark;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//span[contains(@class, \"cell cell_half\")][1]/div[contains(@class, \"dropdown dropdown_scrollable\")]")
    public WebElement yearFrom;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//span[contains(@class, \"cell cell_half\")][1]/div[contains(@class, \"dropdown dropdown_scrollable\")]//div[contains(@class, \"suggest__block\")]/div[text()=\"2005\"]")
    public WebElement yearFrom2005;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//span[contains(@class, \"cell cell_half\")][2]/div[contains(@class, \"dropdown dropdown_scrollable\")]")
    public WebElement yearTo;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//span[contains(@class, \"cell cell_half\")][2]/div[contains(@class, \"dropdown dropdown_scrollable\")]//div[contains(@class, \"suggest__block\")]/div[text()=\"2010\"]")
    public WebElement yearTo2010;

//    @FindBy(xpath = "//div[contains(@class, \"p-field-animated\")]//div[contains(@class, \"p-field-animated__title\") and contains(text(), \"Привод\")]")
//    public WebElement privod;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//div[contains(@class, \"p-field-animated\") and text()=\"Привод\"]/following::div[contains(@class, \"dropdown dropdown_scrollable\")][1]")
    public WebElement privodSelect;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//div[contains(@class, \"p-field-animated\") and text()=\"Привод\"]/following::div[contains(@class, \"dropdown dropdown_scrollable\")][1]//div[contains(@class, \"suggest__item\") and .//span[text()=\"Полный\"]]")
    public WebElement fullPrivod;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//span[@class=\"cell cell_half\"][1]//input[@class=\"input__field\"]")
    public WebElement valueFrom;

    @FindBy(xpath = "//div[contains(@class, \"box_project\")]//span[@class=\"cell cell_half\"][2]//input[@class=\"input__field\"]")
    public WebElement valueTo;

    @FindBy(xpath = "//span[@class=\"js-search-page__total\"]")
    public WebElement totalCars;

    public ExpandedCatalogPage(WebDriver driver) {
        super(driver);
        init();
    }

    public void init() {
        PageFactory.initElements(driver, this);
    }

    public void scrollDown(){
        wait.until(visibilityOf(body));
        body.sendKeys(Keys.PAGE_DOWN);
    }

    public void scrollUp(){
        wait.until(visibilityOf(body));
        body.sendKeys(Keys.PAGE_UP);
    }

    public void clickOnFirstCar(){
        wait.until(visibilityOf(firstCar));
        firstCar.click();
    }

    public String getFirstCarText(){
        wait.until(visibilityOf(firstCar));
        return firstCar.getText();
    }

    public void applyFilters(){
        wait.until(visibilityOf(applyButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", applyButton);
    }

    public void clickOnAllMarksSelector(){
        wait.until(visibilityOf(allMarksSelector));
        allMarksSelector.click();
    }

    public void clickOnFirstMark(){
        wait.until(visibilityOf(firstMark));
        firstMark.click();
    }

    public String getFirstMarkText(){
        wait.until(visibilityOf(firstMark));
        return firstMark.getText();
    }

    public void clickOnSecondMark(){
        wait.until(visibilityOf(secondMark));
        secondMark.click();
    }

    public String getSecondMarkText(){
        wait.until(visibilityOf(secondMark));
        return secondMark.getText();
    }

    public void selectYearFrom(){
        wait.until(visibilityOf(yearFrom));
        yearFrom.click();
//        wait.until(visibilityOf(yearFrom2005));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", yearFrom2005);
        yearFrom.click();
    }

    public void selectYearTo(){
        wait.until(visibilityOf(yearTo));
        yearTo.click();
//        wait.until(visibilityOf(yearTo2010));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", yearTo2010);
        yearTo.click();
    }

    public void selectFullPrivod(){
//        wait.until(visibilityOf(privod));
//        privod.click();

        wait.until(visibilityOf(privodSelect));
//        scrollDown();
//        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", privodSelect);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ block: \"center\", behavior: \"instant\" });", privodSelect);
        privodSelect.click();

        wait.until(visibilityOf(fullPrivod));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({ block: \"center\" });", fullPrivod);
        fullPrivod.click();

        privodSelect.click();

    }

    public void insertIntoValueFrom(int value){
        wait.until(visibilityOf(valueFrom));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ block: \"center\", behavior: \"instant\" });", valueFrom);
        valueFrom.sendKeys(String.valueOf(value));
        scrollUp();
        valueFrom.sendKeys(Keys.ENTER);
    }

    public void insertIntoValueTo(int value){
        wait.until(visibilityOf(valueTo));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ block: \"center\", behavior: \"instant\" });", valueFrom);
        valueTo.sendKeys(String.valueOf(value));
        scrollUp();
        valueFrom.sendKeys(Keys.ENTER);
    }

    public void waitForPageToLoad() {
        ExpectedCondition < Boolean > pageLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            wait.until(pageLoad);
        } catch (Throwable ignored) {

        }
    }

    public int getTotalCars(){
        wait.until(visibilityOf(totalCars));
        return Integer.parseInt(totalCars.getText().trim().split(" ")[1]);

    }

}
