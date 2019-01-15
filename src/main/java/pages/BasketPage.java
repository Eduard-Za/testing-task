package pages;

import com.codeborne.selenide.Condition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage extends BasePage {

    private String delete = "input[value='Delete']";
    private String productName = "div[class='sc-list-item-content'] li:first-child";
    private String message = "div[class='a-row sc-cart-header']";

    private Log log = LogFactory.getLog(BasketPage.class);

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public BasketPage deleteProducts() {
        log.info("Delete products with name/names: " + $$(productName).stream().map(e -> e.getText()).collect(Collectors.toList()));
        $$(delete).stream().map(e -> e.waitUntil(visible, 5000)).forEach(e -> {
            e.click();
            e.should(Condition.disappear);
        });
        return this;
    }

    public String getProductName() {
        String name = $(productName).waitUntil(visible, 5000).getText();
        log.info("Product name is " + name);
        return name;
    }

    public String getMessageAboutEmptyBasket() {
        return $(message).waitUntil(visible, 5000).getText();
    }

    public boolean checkThatOnlyOneProductInBasket() {
        return $$(delete).size() == 1;
    }
}
