package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CatalogPage extends AdsPage{

    @FindBy(xpath = "//div[@class=\"input-group__item\"][last()]")
    public WebElement findCarsButton;

    @FindBy(xpath = "//div[@class=\"input-group__item\"][1]")
    public WebElement kppSelector;

    @FindBy(xpath = "//div[@class=\"input-group__item\"][2]")
    public WebElement kuzovSelector;

    @FindBy(xpath = "//div[@class=\"input-group__item\"][3]")
    public WebElement engineSelector;

    @FindBy(xpath = "//div[@class=\"input-group__item\"][1]//div[contains(@class, \"suggest__item\")][1]")
    public WebElement autoKppButton;

    @FindBy(xpath = "//div[@class=\"input-group__item\"][2]//div[contains(@class, \"suggest__item\")][2]")
    public WebElement crossoverKuzovButton;

    @FindBy(xpath = "//div[@class=\"input-group__item\"][3]//div[contains(@class, \"suggest__item\")][1]")
    public WebElement petrolEngineButton;

    @FindBy(xpath = "//form[@class=\"js-module\"]//a")
    public WebElement extendedSearchLink;


    public CatalogPage(WebDriver driver) {
        super(driver);
        init();
    }

    public void init() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnFindCarsButton(){
        wait.until(visibilityOf(findCarsButton));
        findCarsButton.click();
    }

    public void clickOnKppSelector(){
        wait.until(visibilityOf(kppSelector));
        kppSelector.click();
    }

    public void clickOnAutoKppButton(){
        wait.until(visibilityOf(autoKppButton));
        autoKppButton.click();
    }

    public void clickOnKuzovSelector(){
        wait.until(visibilityOf(kuzovSelector));
        kuzovSelector.click();
    }

    public void clickOnCrossoverKuzovButton(){
        wait.until(visibilityOf(crossoverKuzovButton));
        crossoverKuzovButton.click();
    }

    public void clickOnEngineSelector(){
        wait.until(visibilityOf(engineSelector));
        engineSelector.click();
    }

    public void clickOnPetrolEngineButton(){
        wait.until(visibilityOf(petrolEngineButton));
        petrolEngineButton.click();
    }

    public void clickOnExtendedSearchLink(){
        wait.until(visibilityOf(extendedSearchLink));
        extendedSearchLink.click();
    }


}
