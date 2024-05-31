package org.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CarPage extends AdsPage{

    @FindBy(xpath = "//a[@class=\"filter__item color_white\"]/span[contains(text(), \"Характеристики\")]")
    public WebElement characteristics;

    @FindBy(xpath = "//div[contains(@class, \"js-specs-content_active\")]//span[contains(text(), \"Тип коробки\")]/following::span[1]")
    public WebElement kppType;

    @FindBy(xpath = "//div[contains(@class, \"js-specs-content_active\")]//span[contains(text(), \"Топливо\")]/following::span[1]")
    public WebElement fuelType;

    @FindBy(xpath = "//div[contains(@class, \"js-specs-content_active\")]//span[text()=\"Тип\"]/following::span[1]")
    public WebElement kuzovType;

    @FindBy(xpath = "//span[contains(@class, \"hdr__ending color_gray\")]")
    public WebElement carProducingDate;

    @FindBy(xpath = "//div[contains(@class, \"js-specs-content_active\")]//span[contains(text(), \"Тип привода\")]/following::span[1]")
    public WebElement privodType;

    @FindBy(xpath = "//div[contains(@class, \"js-specs-content_active\")]//span[contains(text(), \"Рабочий объем, см\")]/following::span[1]")
    public WebElement engineValue;

    @FindBy(xpath = "//body")
    public WebElement body;


    public CarPage(WebDriver driver) {
        super(driver);
        init();
    }

    public void init() {
        PageFactory.initElements(driver, this);
    }


    public void closeWindow(){
        ((JavascriptExecutor)driver).executeScript("window.close();");
    }

    public void scrollDown(){
        wait.until(visibilityOf(body));
        body.sendKeys(Keys.PAGE_DOWN);
        body.sendKeys(Keys.PAGE_UP);
    }

    public int getCarProducingDate(){
        return Integer.parseInt(carProducingDate.getText().trim().split(" ")[0]);

    }

    public void clickOnCharacteristics(){
        wait.until(visibilityOf(characteristics));
        characteristics.click();
    }

    public String getKppType(){
        wait.until(visibilityOf(kppType));
        return kppType.getText();
    }

    public String getKuzovType(){
        wait.until(visibilityOf(kuzovType));
        return kuzovType.getText();
    }

    public String getFuelType(){
        wait.until(visibilityOf(fuelType));
        return fuelType.getText();
    }

    public String getPrivodType(){
        wait.until(visibilityOf(privodType));
        return privodType.getText();
    }

    public int getEngineValue(){
        wait.until(visibilityOf(engineValue));
        return Integer.parseInt(engineValue.getText());
    }


}
