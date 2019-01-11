package pages;

import com.codeborne.selenide.Condition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static utils.PropertiesLoader.loadProperty;

public class HomePage extends BasePage {

    private String searchingField = "#twotabsearchtextbox";
    private Log log = LogFactory.getLog(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SearchResultPage search() {

        String searchingValue = loadProperty("searching.value");
        log.info("Search - " + searchingValue);
        $(searchingField).waitUntil(visible, 5000)
                .setValue(searchingValue)
                .submit();
        return new SearchResultPage(driver);
    }
}
