import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class BaseTest {

    protected static List<WebDriver> driverList;
    protected static Properties prop;

    @BeforeAll
    public static void setUpDrivers(){
        driverList = new ArrayList<>();
        prop = new Properties();
        try {
            prop.load(BaseTest.class.getClassLoader().getResourceAsStream("drivers.properties"));

            switch (prop.getProperty("drivers", "all")){
                case "chrome":
                    driverList.add(new ChromeDriver());
                    break;
                case "firefox":
                    driverList.add(new FirefoxDriver());
                    break;
                case "all":
                    driverList.add(new ChromeDriver());
                    driverList.add(new FirefoxDriver());
                    break;
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void resetDrivers(){
        for (var driver :
                driverList) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.of(5, ChronoUnit.SECONDS));
        }
        init();
    }

    @AfterEach
    public void closeWindows(){

        for (var driver :
                driverList) {
            while (driver.getWindowHandles().size() > 1){
                String originalWindow = driver.getWindowHandle();
                for (String windowHandle : driver.getWindowHandles()) {
                    if(!originalWindow.contentEquals(windowHandle)) {
                        driver.switchTo().window(windowHandle);
                        driver.close();
                        driver.switchTo().window(originalWindow);
                        break;
                    }
                }
            }
        }
    }

    public abstract void init();

    @AfterAll
    public static void closeDrivers(){
        driverList.forEach(WebDriver::quit);
    }

}
