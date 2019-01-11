package drivers;

import exceptions.UnknownOsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static drivers.Drivers.Linux;
import static drivers.Drivers.MAC;
import static drivers.Drivers.WINDOWS;
import static utils.PropertiesLoader.loadProperty;

/**
 * Class sets the chromedriver path and returns an instance of ChromeDriver object.
 *
 * @author Eduard Zaretski
 */


public class ChromeWebDriverInstance {

    private static final String OS = "os.name";
    private static final String DRIVER = "webdriver.chrome.driver";
    private static final Log LOG = LogFactory.getLog(ChromeWebDriverInstance.class);


    /**
     * The static method checks for the system name and sets the system driver properties.
     *
     * @return new proper Driver instance.
     */
    public static WebDriver getWebDriverInstance() {
        LOG.info(ChromeWebDriverInstance.class.getSimpleName() + " Set driver");
        String os = System.getProperty(OS);
        String chromeDriverPath = null;
        if (os.contains(WINDOWS.getOsName())) {
            chromeDriverPath = loadProperty(WINDOWS.getPath());
        } else if (os.contains(MAC.getOsName())) {
            chromeDriverPath = loadProperty(MAC.getPath());
        } else if (os.contains(Linux.getOsName())) {
            chromeDriverPath = loadProperty(Linux.getPath());
        } else {
            throw new UnknownOsException("Unsupported OS");
        }
        System.setProperty(DRIVER, chromeDriverPath);
        LOG.info(ChromeDriver.class.getSimpleName() + " properties were setted");
        return new ChromeDriver();
    }
}
