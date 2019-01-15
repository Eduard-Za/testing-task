import com.codeborne.selenide.Selenide;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class MainTest extends BaseTest {

    private Log log = LogFactory.getLog(MainTest.class);

    @Test(groups = {"correct"})
    public void mainTest() throws InterruptedException {

        LoginPage loginPage = Header.transferToLoginPage();
        HomePage homePage = loginPage.login();
        SearchResultPage searchResultPage = homePage.search();

        searchResultPage.order();

        //Сдесь тест мог бы упасть, если бы не софт ассерт

        boolean rightSequance = searchResultPage.checkSorting();
        SoftAssert softAssert = new SoftAssert();
        log.info("Create SoftAssert, because test is failed and we want to see execution " +
                SoftAssert.class.getSimpleName());
        softAssert.assertTrue(rightSequance, "Check that sorting is correct");

        ProductPage productPage = searchResultPage.getTheCheapestIphone();
        String nameProductFromProductPage = productPage.getProductName();

        log.info("Check prices quantity" + ProductPage.class.getSimpleName());
        boolean isSeveralPrices = productPage.checkPricesQuantity();

        // Логика для перехода по страницам (amazon группирует цены на товар), если есть дополнительные цены, окно гарнитуры и др.

        if (isSeveralPrices) {
            PriceListPage priceListPage = productPage.moveToPageWithPrices();
            priceListPage.addTheCheapestToTheBascet();
        } else {
            productPage.addProductToShoppingCart();
        }

        BasketPage basketPage = Header.openShoppingCart();

        // Ассерты

        String nameOfProductInBascet = basketPage.getProductName();
        assertThat("Comparison of product which was added to the basket with product from the basket",
                nameProductFromProductPage, containsString(nameOfProductInBascet));

        Boolean isOneProductInBascet = basketPage.checkThatOnlyOneProductInBasket();
        assertThat("Check that only one product in bascet", isOneProductInBascet);

        basketPage.deleteProducts();

        String messageAboutProducts = basketPage.getMessageAboutEmptyBasket();
        assertThat("Message about the empty bucket is displayed",
                messageAboutProducts, containsString("Your Shopping Basket is empty."));

        Header.logOut();

        String title = Selenide.title();
        assertThat("Validation that LogOut is completed",
                title, containsString("Amazon Sign In"));
    }

    @Test(groups = {"incorrect"})
    public void secondToEnsureParralel() throws InterruptedException {
        Thread.sleep(3000);
    }

}
