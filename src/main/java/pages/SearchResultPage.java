package pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage extends BasePage {

    private String selectLocator = "#sort";
    private String allProducts = "div[class='a-row a-spacing-none'] h2";
    private String allPrices = "span[class='a-size-base a-color-price s-price a-text-bold']";

    private Log log = LogFactory.getLog(SearchResultPage.class);


    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultPage order() {
        Select select = new
                Select($(selectLocator).waitUntil(visible, 5000));
        select.selectByVisibleText("Price: Low to High");
        $(selectLocator).waitUntil(disappear, 5000);
        return this;
    }

    /**
     * Helps us to choose the cheapest iphone 7 128GB.
     *
     * @return new ProductPage - ProductPage with iphone 7 128 gb.
     */
    public ProductPage getTheCheapestIphone() {
        $$(allProducts).stream().filter(e -> {
            e.scrollTo().waitUntil(visible, 5000);
            return e.getText().toLowerCase().contains("iphone 7") && e.getText().toLowerCase().contains("128");
        }).findFirst().get().click();
        return new ProductPage(driver);
    }

    /**
     * Returns true if result from page is the same as sorted result from page.
     *
     * @return result - shows that sorting by price correct
     */
    public boolean checkSorting() {
        $$(allProducts).get(0).waitUntil(visible, 5000);
        List<Float> pricesFromPage = $$(allPrices).stream()
                .map(element -> element.getText().substring(1, element.getText().length()))
                .map(element -> Float.parseFloat(element)).collect(Collectors.toList());
        List<Float> expectedFromPage = pricesFromPage.stream().sorted().collect(Collectors.toList());
        boolean result = pricesFromPage.equals(expectedFromPage);
        log.info("Sorting result is " + result + "  !!!!!!!!");
        return result;
    }
}
