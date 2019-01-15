package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class Scroller extends BasePage {

    public static SelenideElement getBodyScroller() {
        return bodyScroller;
    }

    private static SelenideElement bodyScroller = $("#attach-accessory-pane");

    public static SelenideElement getAdditionalBascet() {
        return additionalBascet;
    }

    private static SelenideElement additionalBascet = bodyScroller.$("#attach-sidesheet-view-cart-button");

    public Scroller(WebDriver driver) {
        super(driver);
    }


}
