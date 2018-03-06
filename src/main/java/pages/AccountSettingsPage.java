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
}
