import org.example.pages.CarPage;
import org.example.pages.CatalogPage;
import org.example.pages.ExpandedCatalogPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AutoTest extends BaseTest{
    List<CatalogPage> pages;
    @Override
    public void init(){
        pages = new ArrayList<>();
        for (var driver :
                driverList) {
            driver.get(prop.getProperty("auto"));
            pages.add(new CatalogPage(driver));
        }
    }

    @Test
    public void checkKpp(){
        for (var page :
                pages) {

            page.waitAds();
            page.clickOnKppSelector();
            page.clickOnAutoKppButton();
            page.clickOnFindCarsButton();

            ExpandedCatalogPage expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();
            expandedCatalogPage.clickOnFirstCar();

            expandedCatalogPage.wait.until(numberOfWindowsToBe(2));
            assertEquals(2, page.driver.getWindowHandles().size());

            String originalWindow = page.driver.getWindowHandle();
            for (String windowHandle : page.driver.getWindowHandles()) {
                if(!originalWindow.contentEquals(windowHandle)) {
                    page.driver.switchTo().window(windowHandle);
                    break;
                }
            }

            CarPage carPage = new CarPage(page.driver);
            carPage.waitAds();
            carPage.clickOnCharacteristics();

            assertEquals("Автомат", carPage.getKppType());

        }
    }

    @Test
    public void checkKuzov(){
        for (var page :
                pages) {

            page.waitAds();
            page.clickOnKuzovSelector();
            page.clickOnCrossoverKuzovButton();
            page.clickOnFindCarsButton();

            ExpandedCatalogPage expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();
            expandedCatalogPage.clickOnFirstCar();

            expandedCatalogPage.wait.until(numberOfWindowsToBe(2));
            assertEquals(2, page.driver.getWindowHandles().size());

            String originalWindow = page.driver.getWindowHandle();
            for (String windowHandle : page.driver.getWindowHandles()) {
                if(!originalWindow.contentEquals(windowHandle)) {
                    page.driver.switchTo().window(windowHandle);
                    break;
                }
            }

            CarPage carPage = new CarPage(page.driver);
            carPage.waitAds();
            carPage.clickOnCharacteristics();

            assertTrue(carPage.getKuzovType().contains("Кроссовер"));

        }
    }

    @Test
    public void checkEngine(){
        for (var page :
                pages) {

            page.waitAds();
            page.clickOnEngineSelector();
            page.clickOnPetrolEngineButton();
            page.clickOnFindCarsButton();

            ExpandedCatalogPage expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();
            expandedCatalogPage.clickOnFirstCar();

            expandedCatalogPage.wait.until(numberOfWindowsToBe(2));
            assertEquals(2, page.driver.getWindowHandles().size());

            String originalWindow = page.driver.getWindowHandle();
            for (String windowHandle : page.driver.getWindowHandles()) {
                if(!originalWindow.contentEquals(windowHandle)) {
                    page.driver.switchTo().window(windowHandle);
                    break;
                }
            }

            CarPage carPage = new CarPage(page.driver);
            carPage.waitAds();
            carPage.clickOnCharacteristics();

            assertTrue(carPage.getFuelType().contains("Бензин"));

        }
    }

    @Test
    public void checkMarks(){
        for (var page :
                pages) {

            page.waitAds();
            page.clickOnExtendedSearchLink();

            ExpandedCatalogPage expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.clickOnAllMarksSelector();

            String mark = expandedCatalogPage.getSecondMarkText();

            expandedCatalogPage.clickOnSecondMark();
            expandedCatalogPage.clickOnAllMarksSelector();

            expandedCatalogPage.scrollDown();

            expandedCatalogPage.applyFilters();

            expandedCatalogPage.waitForPageToLoad();

            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            String carText = expandedCatalogPage.getFirstCarText();

            assertTrue(carText.contains(mark));

        }
    }

    @Test
    public void checkAge(){
        for (var page :
                pages) {

            page.waitAds();
            page.clickOnExtendedSearchLink();

            ExpandedCatalogPage expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.selectYearFrom();
            expandedCatalogPage.applyFilters();

            expandedCatalogPage.waitForPageToLoad();

            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.selectYearTo();
            expandedCatalogPage.applyFilters();

            expandedCatalogPage.waitForPageToLoad();

            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.clickOnFirstCar();

            expandedCatalogPage.wait.until(numberOfWindowsToBe(2));
            assertEquals(2, page.driver.getWindowHandles().size());

            String originalWindow = page.driver.getWindowHandle();
            for (String windowHandle : page.driver.getWindowHandles()) {
                if(!originalWindow.contentEquals(windowHandle)) {
                    page.driver.switchTo().window(windowHandle);
                    break;
                }
            }

            CarPage carPage = new CarPage(page.driver);
            carPage.waitAds();
            int producingDate = carPage.getCarProducingDate();

            assertTrue(producingDate >= 2005);
            assertTrue(producingDate <= 2010);

        }
    }

    @Test
    public void checkPrivod(){
        for (var page :
                pages) {

            page.waitAds();
            page.clickOnExtendedSearchLink();

            ExpandedCatalogPage expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.selectFullPrivod();

            expandedCatalogPage.applyFilters();

            expandedCatalogPage.waitForPageToLoad();

            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.clickOnFirstCar();

            expandedCatalogPage.wait.until(numberOfWindowsToBe(2));
            assertEquals(2, page.driver.getWindowHandles().size());

            String originalWindow = page.driver.getWindowHandle();
            for (String windowHandle : page.driver.getWindowHandles()) {
                if(!originalWindow.contentEquals(windowHandle)) {
                    page.driver.switchTo().window(windowHandle);
                    break;
                }
            }

            CarPage carPage = new CarPage(page.driver);
            carPage.waitAds();

            carPage.clickOnCharacteristics();
            String privodText = carPage.getPrivodType();

            assertTrue(privodText.contains("Полный"));

        }
    }

    @Test
    public void checkEngineValue(){
        for (var page :
                pages) {

            page.waitAds();
            page.clickOnExtendedSearchLink();

            ExpandedCatalogPage expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.insertIntoValueFrom(1);

            expandedCatalogPage.waitForPageToLoad();
            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.insertIntoValueTo(2);

            expandedCatalogPage.waitForPageToLoad();

            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.clickOnFirstCar();

            expandedCatalogPage.wait.until(numberOfWindowsToBe(2));
            assertEquals(2, page.driver.getWindowHandles().size());

            String originalWindow = page.driver.getWindowHandle();
            for (String windowHandle : page.driver.getWindowHandles()) {
                if(!originalWindow.contentEquals(windowHandle)) {
                    page.driver.switchTo().window(windowHandle);
                    break;
                }
            }

            CarPage carPage = new CarPage(page.driver);
            carPage.waitAds();

            carPage.clickOnCharacteristics();
            int engineValue = carPage.getEngineValue();

            assertTrue(engineValue >= 1000);
            assertTrue(engineValue <= 2000);

        }
    }

    @Test
    public void checkCarDifference(){
        for (var page :
                pages) {

            page.waitAds();
            page.clickOnExtendedSearchLink();

            ExpandedCatalogPage expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            expandedCatalogPage.clickOnAllMarksSelector();
            expandedCatalogPage.clickOnFirstMark();
            expandedCatalogPage.clickOnAllMarksSelector();

            expandedCatalogPage.scrollDown();

            expandedCatalogPage.applyFilters();

            expandedCatalogPage.waitForPageToLoad();

            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            int firstMarkCars = expandedCatalogPage.getTotalCars();

            expandedCatalogPage.clickOnAllMarksSelector();
            expandedCatalogPage.clickOnFirstMark();
            expandedCatalogPage.clickOnSecondMark();
            expandedCatalogPage.clickOnAllMarksSelector();

            expandedCatalogPage.scrollDown();

            expandedCatalogPage.applyFilters();

            expandedCatalogPage.waitForPageToLoad();

            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            int secondMarkCars = expandedCatalogPage.getTotalCars();

            expandedCatalogPage.clickOnAllMarksSelector();
            expandedCatalogPage.clickOnFirstMark();
            expandedCatalogPage.clickOnAllMarksSelector();

            expandedCatalogPage.scrollDown();

            expandedCatalogPage.applyFilters();

            expandedCatalogPage.waitForPageToLoad();

            expandedCatalogPage = new ExpandedCatalogPage(page.driver);
            expandedCatalogPage.waitAds();

            int totalMarkCars = expandedCatalogPage.getTotalCars();

            assertEquals(totalMarkCars, firstMarkCars + secondMarkCars);

        }
    }

}
