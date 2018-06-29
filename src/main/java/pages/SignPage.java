package pages;

import infrastructure.Driver;
import infrastructure.EmailGenerator;
import infrastructure.MailHog;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Created by eugeniya.kruchok on 02.03.2018.
 */
public class SignPage extends PageObject {

    private static String email;

    public SignPage() { super(); }

    @FindBy(css = "input[type=\"email\"]")
    private WebElement emailField;

    @FindBy(id = "send-email-btn")
    private WebElement submitButton;

    @FindBy(id = "code")
    private WebElement codeInput;

    @FindBy(id = "send-code-btn")
    private WebElement submitCodeButton;


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
        this.enterEmail(email).clickGetCodeButton();
        EmailGenerator generator = new EmailGenerator();
        generator.openInNewTab();
        String code = generator.getCode();
        this.enterCode(code).submit();
        try {
            Driver.waitForElement(3).until(ExpectedConditions.urlToBe(PrivacyPolicyPage.getUrl()));
            new PrivacyPolicyPage().acceptTerms();
        } catch (TimeoutException e) {
            Driver.waitForElement(5).until(ExpectedConditions.urlToBe(Driver.baseUrl));
        }



    }

    public SignPage enterCode(String code) {
        codeInput.sendKeys(code);
        return this;
    }

    public void submit() {
        submitCodeButton.click();
    }


    /**
     * Inner class is created to implement Fluent API.
     * Fluent API makes creating of tests easier.
     */

    public class EmailCommand {

        @Step("Click 'Get a magic link' button")
        public void clickGetCodeButton() {
            submitButton.click();
        }

        @Deprecated
        public void clickGetLinkButton() {

        }
    }

    public static String getEmail() {
        return email;
    }
}
