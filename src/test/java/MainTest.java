import com.codeborne.selenide.Selenide;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import pages.*;

import static org.hamcrest.CoreMatchers.containsString;

public class MainTest extends BaseTest {

    private Log log = LogFactory.getLog(MainTest.class);

    @Test
    public void mainTest() {

        LoginPage loginPage = Header.transferToLoginPage();
        HomePage homePage = loginPage.login();
        SearchResultPage searchResultPage = homePage.search();
        ProductPage productPage = searchResultPage.order().getTheCheapest();
        String nameProductFromProductPage = productPage.getProductName();
        productPage.addProductToShoppingCart();
        BasketPage basketPage = Header.openShoppingCart();

        String nameOfProductInBascet = basketPage.getProductName();
        Assert.assertThat("Comparison of product which was added to the basket with product from the basket",
                nameProductFromProductPage, containsString(nameOfProductInBascet));

        basketPage.deleteProducts();

        String messageAboutProducts = basketPage.getMessageAboutEmptyBasket();
        Assert.assertThat("Message about the empty bucket is displayed",
                messageAboutProducts, containsString("Your Shopping Basket is empty."));

        Header.logOut();

        String title = Selenide.title();
        Assert.assertThat("Validation that LogOut is completed",
                title, containsString("Amazon Sign In"));
    }

}
