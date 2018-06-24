package pages;

import infrastructure.Browser;
import infrastructure.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.interfaces.SignUp;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 */
public class SignUpPage extends SignPage implements SignUp {

    public SignUpPage() {super();}

    private static String url = baseUrl + Config.getProperty("signUp");

//    @FindBy(css = "input[type=\"email\"]")
//    private WebElement emailField;
//
//    @FindBy(id = "magic-btn")
//    private WebElement submitButton;

    @FindBy(id = "flash-content")
    private WebElement successText;

    @Override
    @Step("Open Sign Up page")
    public void open() {
        Browser.driver.get(url);
    }

    @Step("Verify that success Sign Up massage is displayed")
    public boolean isSuccess() {
        Browser.waitForElement(10).until(ExpectedConditions.visibilityOf(successText));
        return successText.getText().contains("We've created a new account for you.");
    }


    @Step("Sign up new user with {email} email")
    public void signUp(String email) {
//        String testUrl = Browser.driver.getCurrentUrl();
        open();
        signIn(email);
    }






}
