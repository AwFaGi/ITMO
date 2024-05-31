import org.example.pages.LoginPage;
import org.example.pages.RecoveryPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class RecoveryPageTest extends BaseTest{
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
    public void checkNoAccount(){
        for (var page :
                pages) {
            page.goToRecovery();
            assertEquals(2, page.driver.getWindowHandles().size());

            String originalWindow = page.driver.getWindowHandle();
            for (String windowHandle : page.driver.getWindowHandles()) {
                if(!originalWindow.contentEquals(windowHandle)) {
                    page.driver.switchTo().window(windowHandle);
                    break;
                }
            }

            RecoveryPage recoveryPage = new RecoveryPage(page.driver);
            recoveryPage.enterEmail(Constants.BAD_EMAIL);
            recoveryPage.clickOnContinueButton();
            assertEquals("Такого аккаунта нет", recoveryPage.getErrorText());
        }
    }
}
