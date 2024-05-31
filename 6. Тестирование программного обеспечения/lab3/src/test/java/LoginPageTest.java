import org.example.pages.LoginPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

@Disabled
public class LoginPageTest extends BaseTest{
    List<LoginPage> pages;
    @Override
    public void init(){
        pages = new ArrayList<>();
        for (var driver :
                driverList) {
            driver.get(prop.getProperty("url"));
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (Exception ignored) {
            }
            pages.add(new LoginPage(driver));
        }
    }

    @Test
    public void checkNoEmailEntered(){
        for (var page :
                pages) {
            page.driver.manage().deleteAllCookies();
            page.driver.navigate().refresh();
            page.clickOnEnterPasswordBtn();
            assertEquals("Поле «Имя аккаунта» должно быть заполнено", page.getErrorText());
        }
    }

    @Test
    public void checkNoAccount(){
        for (var page :
                pages) {
            page.driver.manage().deleteAllCookies();
            page.driver.navigate().refresh();
            page.enterEmail(Constants.BAD_EMAIL);
            page.clickOnEnterPasswordBtn();
            assertEquals("Такой аккаунт не зарегистрирован", page.getErrorText());
        }
    }

    @Test
    public void checkNoPasswordEntered(){
        for (var page :
                pages) {
            page.driver.manage().deleteAllCookies();
            page.driver.navigate().refresh();
            page.enterEmail(Constants.WORKING_EMAIL);
            page.clickOnEnterPasswordBtn();
            page.clickOnLoginButton();
            assertEquals("Поле «Пароль» должно быть заполнено", page.getPasswordErrorText());
        }
    }

    @Test
    public void checkWrongPasswordEntered(){
        for (var page :
                pages) {
            page.driver.manage().deleteAllCookies();
            page.driver.navigate().refresh();
            page.enterEmail(Constants.WORKING_EMAIL);
            page.clickOnEnterPasswordBtn();
            page.enterPassword(Constants.BAD_PASSWORD);
            page.clickOnLoginButton();
            assertEquals("Неверный пароль, попробуйте ещё раз", page.getPasswordErrorText());
        }
    }

    @Test
    public void checkLogin(){
        for (var page :
                pages) {
            page.enterEmail(Constants.WORKING_EMAIL);
            page.clickOnEnterPasswordBtn();
            page.enterPassword(Constants.WORKING_PASSWORD);
            page.clickOnLoginButton();

            page.wait.until(
                    titleContains("Входящие - Почта Mail.ru")
            );
            assertTrue(page.getPageTitle().contains("Входящие - Почта Mail.ru"));
        }
    }

}
