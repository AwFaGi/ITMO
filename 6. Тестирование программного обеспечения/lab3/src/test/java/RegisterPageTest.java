import org.example.pages.LoginPage;
import org.example.pages.RegisterPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

@Disabled
public class RegisterPageTest extends BaseTest{
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
    public void checkEmptyFields(){
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterPassword("");
            registerPage.clickCreateButton();

            assertEquals("Укажите имя", registerPage.getErrorNameInputText());
            assertEquals("Укажите фамилию", registerPage.getErrorSurnameInputText());
            assertEquals("Укажите дату рождения", registerPage.getErrorDateOfBirthText());
            assertEquals("Укажите ваш пол", registerPage.getErrorGenderInputText());
            assertEquals("Укажите желаемое имя ящика", registerPage.getErrorEmptyMailboxNameText());
            assertEquals("Укажите пароль", registerPage.getErrorEmptyPasswordText());
            assertEquals("Укажите пароль", registerPage.getErrorEmptyRepeatPasswordText());

        }
    }

    @Test
    public void checkShortEmail(){
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterMailboxName(Constants.SHORT_EMAIL_REG);
            registerPage.clickCreateButton();

            assertEquals("Введено некорректное имя ящика. " +
                    "Имя ящика должно быть длиной от 5 до 31 символов.",
                    registerPage.getErrorEmptyMailboxNameText()
            );

        }
    }

    @Test
    public void checkLongEmail(){
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterMailboxName(Constants.LONG_EMAIL_REG);
            registerPage.clickCreateButton();

            assertEquals("Введено некорректное имя ящика. " +
                            "Имя ящика должно быть длиной от 5 до 31 символов.",
                    registerPage.getErrorEmptyMailboxNameText()
            );

        }
    }

    @Test
    public void checkCyrillicEmail(){
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterMailboxName(Constants.CYRILLIC_EMAIL_REG);
            registerPage.clickCreateButton();

            assertEquals("В имени ящика нельзя использовать кириллицу",
                    registerPage.getErrorEmptyMailboxNameText()
            );

        }
    }

    @Test
    public void checkOtherEmail(){
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterMailboxName(Constants.OTHER_EMAIL_REG);
            registerPage.clickCreateButton();

            assertEquals("Введено некорректное имя ящика. " +
                            "Допустимо использовать только латинские буквы, цифры, " +
                            "знак подчеркивания, точку и минус.",
                    registerPage.getErrorEmptyMailboxNameText()
            );

        }
    }

    @Test
    public void testExistingEmailRegistration() {
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterMailboxName(Constants.WORKING_EMAIL);
            registerPage.clickCreateButton();
            assertEquals("Ящик с таким именем уже существует", registerPage.getErrorExistsMailboxNameText());
        }
    }

    @Test
    public void testShortPasswordRegistration() {
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterPassword(Constants.BAD_PASSWORD_ONE);
            registerPage.clickCreateButton();
            assertEquals("Используйте не менее 8 символов", registerPage.getErrorLengthPasswordText());
        }
    }

    @Test
    public void testNoSpecialCharactersRegistration() {
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterPassword(Constants.BAD_PASSWORD_TWO);
            registerPage.clickCreateButton();
            assertEquals("Не используйте личные данные, последовательности (123456, qwerty) и популярные пароли (password).", registerPage.getErrorInvalidPasswordText());
        }
    }

    @Test
    public void testInvalidCharactersRegistration() {
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterPassword(Constants.BAD_PASSWORD_THREE);
            registerPage.clickCreateButton();
            assertEquals("Используйте только буквы (a–z, A–Z), цифры и символы ! @ # $ % ^ & * ( ) - _ + = ; : , . / ? \\ | ` ~ { }", registerPage.getErrorInvalidPasswordText());
        }
    }

    @Test
    public void testPasswordsMismatchRegistration() {
        for (var page :
                pages) {

            page.goToRegister();
            page.wait.until(
                    titleContains("Создать бесплатную почту")
            );
            RegisterPage registerPage = new RegisterPage(page.driver);

            registerPage.enterPassword(Constants.WORKING_PASSWORD);
            registerPage.enterRepeatPassword(Constants.BAD_PASSWORD_ONE);
            assertEquals("Пароли не совпадают", registerPage.getErrorPasswordMatchText());
        }
    }

}
