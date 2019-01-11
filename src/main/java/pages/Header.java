package pages;

import com.codeborne.selenide.Selenide;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class Header extends BasePage {

    private static String signIn = "#nav-flyout-ya-signin>a";
    private static String innerMenu = "#nav-link-yourAccount";
    private static String additionalGarnitureWindow = "#attach-accessory-pane";
    private static String additionalBascet = "#attach-sidesheet-view-cart-button";
    private static String openBascetElement = "#nav-cart-count";

    private static By logOutLocator = By.xpath("//span[contains(text(), 'Sign Out')]");
    private static Log LOG = LogFactory.getLog(Header.class);


    public Header(WebDriver driver) {
        super(driver);
    }

    public static BasketPage openShoppingCart() {
        if (!Selenide.title().contains("Basket")) {
            $(additionalGarnitureWindow).waitUntil(and("Waits", visible, enabled), 10000)
                    .$(additionalBascet)
                    .waitUntil(enabled, 5000)
                    .click();
        } else {
            $(openBascetElement).waitUntil(visible, 5000).click();
        }
        return new BasketPage(driver);
    }

    public static LoginPage transferToLoginPage() {
        LOG.info(Header.class.getSimpleName() + " LOGIN");
        $(innerMenu).hover();
        $(signIn).waitUntil(exist, 5000)
                .click();
        return new LoginPage(driver);
    }


    public static HomePage logOut() {
        LOG.info(Header.class.getSimpleName() + " LOGOUT");
        $(innerMenu).hover();
        $(logOutLocator).waitUntil(exist, 5000)
                .click();
        return new HomePage(driver);
    }
}
