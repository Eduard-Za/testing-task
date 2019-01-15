import com.codeborne.selenide.WebDriverRunner;
import drivers.ChromeWebDriverInstance;
import listeners.ListenerFailed;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.open;
import static utils.PropertiesLoader.loadProperty;

@Listeners({ListenerFailed.class})
public class BaseTest {

    protected static WebDriver driver;
    private Log log = LogFactory.getLog(BaseTest.class);


    @BeforeTest(alwaysRun=true)
    public void createDriver() {
        log.info("Test started");
        driver = ChromeWebDriverInstance.getWebDriverInstance();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        String url = loadProperty("url");
        open(url);
    }

    @AfterTest(alwaysRun=true)
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


}
