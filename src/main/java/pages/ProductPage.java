package pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends BasePage {

    private String addToCart = "#add-to-cart-button";
    private String currentProductName = "#productTitle";

    private Log log = LogFactory.getLog(LoginPage.class);


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToShoppingCart() {
        $(addToCart).waitUntil(visible, 5000).click();
    }

    public String getProductName() {
        String productName = $(currentProductName).getText();
        log.info("Result of searching was added " + productName);
        return productName;
    }
}
