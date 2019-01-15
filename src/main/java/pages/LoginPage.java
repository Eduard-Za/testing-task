package pages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static utils.PropertiesLoader.loadProperty;

public class LoginPage extends BasePage {

    private String emailField = "#ap_email";
    private String passwordField = "#ap_password";
    private String signInButton = "#signInSubmit";
    private Log log = LogFactory.getLog(LoginPage.class);


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public HomePage login() {


        String userName = loadProperty("user.username");
        String password = loadProperty("user.password");

        log.info("Login with credentials: username " + userName + " and password " + password);

        $(emailField).setValue(userName);
        $(passwordField).setValue(password);
        $(signInButton).click();

        return new HomePage(driver);
    }

}
