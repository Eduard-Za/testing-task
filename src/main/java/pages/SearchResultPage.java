package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage extends BasePage {

    private String selectLocator = "#sort";
    private String allProducts = "div[class='a-row a-spacing-none'] h2";


    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultPage order() {
        Select select = new
                Select($(selectLocator).waitUntil(visible, 5000));
        select.selectByVisibleText("Price: Low to High");
        return this;
    }


    public ProductPage getTheCheapest() {
        $$(allProducts).get(0).click();
        return new ProductPage(driver);
    }
}
