package pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$$;
import static java.util.Comparator.comparing;

public class PriceListPage extends BasePage {

    private String sectionsWithProducts = "div[class='a-row a-spacing-mini olpOffer']";
    private String price = "span[class='a-size-large a-color-price olpOfferPrice a-text-bold']";
    private String button = "input[name='submit.addToCart']";
    private Log log = LogFactory.getLog(PriceListPage.class);


    public PriceListPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Helps to choose the cheapest value.
     */
    public PriceListPage addTheCheapestToTheBascet() {
        log.info("Choose the cheapest value from " + PriceListPage.class.getSimpleName());
        $$(sectionsWithProducts).stream()
                .sorted(comparing(e ->
                        Float.parseFloat(e.$(price).getText().substring(1, e.$(price).getText().length()))))
                .findFirst().get().$(button).click();
        return this;

    }
}
