package pages;

import infrastructure.Browser;
import infrastructure.Property;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Created by eugeniya.kruchok on 28.02.2018.
 */
public class AccountSettingsPage extends PageObject{

    public AccountSettingsPage() { super(); }

    private static String url = baseUrl + Property.getProperty("accountSettings");

    @FindBy(css = "button.btn-danger:nth-of-type(2)")
    private WebElement deleteAccountButton;

    @FindBy(css = "a[href=\"/signout\"]")
    private WebElement sighOutButton;

    @FindBy(css = "input[name=\"username-settings\"]")
    private WebElement usernameField;

    @FindBy(css = "#usernameSettings .col-sm-2.col-xs-12 button[type=\"submit\"]")
    private WebElement saveButton;

    @FindBy(css = ".notification-list .notification-bar-message")
    private WebElement message;

    @Step("Open Account Settings page")
    public void open() { Browser.driver.get(url); }

    @Step("Delete Account")
    public void deleteAccount() {
        Browser.waitForElement(10).until(ExpectedConditions.visibilityOf(deleteAccountButton));
        deleteAccountButton.click();
        WebElement confirmDeleteButton = Browser.waitForElement(5).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(text(), 'Delete everything related to this account')]")
                )
        );
        confirmDeleteButton.click();
    }

    @Step("Log out")
    public void logOut() {
        open();
        sighOutButton.click();
    }

    @Step("Click 'Sigh Out' Button")
    public void clickSignOutButton() {
        sighOutButton.click();
    }

    @Step("Verify that account is deleted")
    public void isDeleted() {

    }

    @Step("Verify that right user is signed in")
    public boolean isSignedIn() {
        if (!(Browser.driver.getCurrentUrl().equals(url))) open();
        WebElement email = Browser.driver.findElement(By.id("email"));
        return email.getAttribute("value").equals(SignUpPage.getEmail());
    }

    public static String getRelativeUrl() {
        return url;
    }

    @Step("Enter {username} into Username field")
    public UsernameCommand enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
        return new UsernameCommand();
    }

    public boolean isUsernameUpdated(String username) {
        Browser.waitForElement(20).until(ExpectedConditions.visibilityOf(message));
        String messageText = message.getText();
        Browser.driver.navigate().refresh();
        return messageText.endsWith("Username updated successfully") ||
                usernameField.getAttribute("value").equals(username);
    }

    public class UsernameCommand {
        @Step("Click 'Save' button")
        public void clickSaveButton() {
            Browser.waitForElement(10).until(ExpectedConditions.elementToBeClickable(saveButton));
            saveButton.click();
        }
    }
}
