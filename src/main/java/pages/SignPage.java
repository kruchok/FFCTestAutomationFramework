package pages;

import infrastructure.MailHog;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.navigation.HeaderNavigation;


/**
 * Created by eugeniya.kruchok on 02.03.2018.
 */
public class SignPage extends PageObject {

    public SignPage() { super(); }

    private static String email;


    /**
     * Делать общий класс родитель не абстрактный
     * а абстракные методы добавить в интерфейсы
     */


    @FindBy(css = "input[type=\"email\"]")
    private WebElement emailField;

    @FindBy(id = "magic-btn")
    private WebElement submitButton;


    /**
     * @param email is stored into email field for further verification of email in MailHog class.
     * @return instance of EmailCommand class, which allows to implement Fluent API.
     */

    @Step("Enter {email} email")
    public EmailCommand enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        SignPage.email = email;
        return new EmailCommand();
    }

    @Step("Delete account")
    public void cleanUp() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.open();
        settingsPage.deleteAccount();
    }

    @Step("Sign in user with {email} email")
    public void signIn(String email) {
        this.enterEmail(email).clickGetLinkButton();
        MailHog mailHog = new MailHog();
        mailHog.open();
        mailHog.followLinkToSignIn();
    }


    /**
     * Inner class is created to implement Fluent API.
     * Fluent API makes creating of tests easier.
     */

    public class EmailCommand {

        @Step("Click 'Get a magic link' button")
        public void clickGetLinkButton() {
            submitButton.click();
        }
    }

    public static String getEmail() {
        return email;
    }
}
