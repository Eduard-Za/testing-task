package pages;

import com.codeborne.selenide.Condition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class Header extends BasePage {

    private static String signIn = "#nav-flyout-ya-signin>a";
    private static String innerMenu = "#nav-link-yourAccount";
    private static String openBascetElement = "#nav-cart-count";

    private static By logOutLocator = By.xpath("//span[contains(text(), 'Sign Out')]");
    private static Log LOG = LogFactory.getLog(Header.class);


    public Header(WebDriver driver) {
        super(driver);
    }

    public static BasketPage openShoppingCart() throws InterruptedException {
        Thread.sleep(500);
        if (Scroller.getBodyScroller().isDisplayed()) {
            Scroller.getAdditionalBascet().waitUntil(Condition.enabled, 5000);
            Scroller.getAdditionalBascet().click();
        } else {
            $(openBascetElement).waitUntil(enabled, 5000).click();
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

    public static void deleteProductsIfPresentIfTestFailed() throws InterruptedException {
        LOG.info("Delete data in listener after test");
        openShoppingCart().deleteProducts();
    }
}
