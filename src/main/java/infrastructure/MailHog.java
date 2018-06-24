package infrastructure;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PageObject;
import pages.SignUpPage;

import java.util.NoSuchElementException;

/**
 * Created by eugeniya.kruchok on 28.02.2018.
 */
public class MailHog extends PageObject {

    private static String url = Config.getProperty("mailhog");

    @FindBy(css = "div.msglist-message")
    private WebElement mail;

    @FindBy(xpath = "//table//th[text()='To']/following-sibling::td")
    private WebElement emaiAddress;

    @FindBy(css = "a[ng-click=\"deleteAll()\"]")
    private static WebElement deleteAllButton;

    @Step("Open email page on MailHog")
    public void open() {
        ((JavascriptExecutor) Browser.driver).executeScript("window.open('" + url + "');");
        Browser.switchToLastWindow();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Verify and sing in by link in mail")
    public void followLinkToSignIn() {
        openMail();
        Mail mail = new Mail();
        if (verifyEmail()) {
            mail.getSignInLink().click();
        } else {
            throw new NoSuchElementException("Email address is wrong");
        }
        Browser.switchToLastWindow();
    }

    /**
     * Delete all mails in mail box.
     */
    @Step("Clean up email list")
    public void cleanUp() {
        open();
        deleteAllButton.click();
        WebElement confirmDeleteButton = Browser.waitForElement(10).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#confirm-delete-all .btn.btn-danger")
                )
        );
        confirmDeleteButton.click();
    }

    /**
     * Open first mail (last received) from the list.
     */
    public void openMail() {
        mail.click();
        Browser.defaultWait();
    }

    /**
     * Verify that opened mail is send to right email address.
     */
    public boolean verifyEmail() {
        return emaiAddress.getText().equals(SignUpPage.getEmail());
    }

    /**
     * Inner class for separate mail item.
     * Use to obtain Sign In link.
     */

    private class Mail extends PageObject {

        public Mail() {
            super();
        }

        @FindBy(css = "a[href*=\"passwordless-auth\"]")
        private WebElement signInLink;

        /**
         * @return Sign In link
         */
        public WebElement getSignInLink() {
            return signInLink;
        }
    }
}
