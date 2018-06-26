package pages;

import infrastructure.Driver;
import infrastructure.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 */
public class SignUpPage extends SignPage {

    private static String url = Driver.baseUrl + Config.getProperty("signUp");

    public SignUpPage() {super();}

    @FindBy(id = "flash-content")
    private WebElement successText;

    @Step("Open Sign Up page")
    public void open() {
        Driver.driver.get(url);
    }

    @Step("Verify that success Sign Up massage is displayed")
    public boolean isSuccess() {
        Driver.waitForElement(10).until(ExpectedConditions.visibilityOf(successText));
        return successText.getText().contains("We've created a new account for you.");
    }

    @Step("Sign up new user with {email} email")
    public void signUp(String email) {
        this.open();
        this.signIn(email);
    }






}
