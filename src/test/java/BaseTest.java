import com.codeborne.selenide.WebDriverRunner;
import drivers.ChromeWebDriverInstance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static utils.PropertiesLoader.loadProperty;

public class BaseTest {

    protected static WebDriver driver;
    private Log log = LogFactory.getLog(BaseTest.class);


    @Before
    public void createDriver() {
        log.info("Test started");
        driver = ChromeWebDriverInstance.getWebDriverInstance();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
        String url = loadProperty("url");
        open(url);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


}
