package pages;

import infrastructure.Browser;
import infrastructure.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.CurrentTime;


/**
 * Created by eugeniya.kruchok on 28.02.2018.
 */
public class AccountSettingsPage extends PageObject{

    public AccountSettingsPage() {
        super();
        Browser.logger.debug(this.getClass().getSimpleName()
                + " is opened at "
                + CurrentTime.getCurrentTime()
        );
    }

    private static String url = baseUrl + Config.getProperty("accountSettings");

    /**
     * 'Delete account' flow.
     */
    @FindBy(css = "button.btn-danger:nth-of-type(2)")
    private WebElement deleteAccountButton;

    @FindBy(css = "[role=\"document\"] .btn.btn-lg.btn-danger.btn-block")
    private WebElement confirmDeleteButton;

    @FindBy(css = "a[href=\"/signout\"]")
    private WebElement sighOutButton;

    /**
     * 'Username' form.
     */
    @FindBy(name = "username-settings")
    private WebElement usernameField;

    @FindBy(css = "#usernameSettings button[type=\"submit\"]")
    private WebElement saveUsernameButton;

    /**
     * 'Bio' form.
     */
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "location")
    private WebElement locationField;

    @FindBy(id = "picture")
    private WebElement pictureField;

    @FindBy(id = "about")
    private WebElement aboutField;

    @FindBy(css = "#camper-identity button[type=\"submit\"]")
    private WebElement saveBioForm;

    /**
     * 'Change email' form
     */
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "confirm-email")
    private WebElement confirmEmailField;

    @FindBy(css = "#email-form button[type=\"submit\"]")
    private WebElement saveChangeEmailForm;

    @FindBy(css = ".email-settings .help-block [role='alert']")
    private WebElement verifyEmailAddressMessage;

    /**
     * 'Language Settings' drop-down
     */
    @FindBy(id = "lang")
    private WebElement languageSelect;

    /**
     * 'Internet presence' form.
     */
    @FindBy(id = "github-url")
    private WebElement githubURLField;

    @FindBy(id = "linkedin")
    private WebElement linkedinField;

    @FindBy(id = "twitter")
     private WebElement twitterField;

    @FindBy(id = "website")
    private WebElement websiteField;

    @FindBy(css = "#internet-handle-settings button[type=\"submit\"]")
    private WebElement saveInternetPresenceButton;

    /**
     * Success/failure message
     */
    @FindBy(css = ".notification-list .notification-bar-message")
    private WebElement message;

    @Step("Open Account Settings page")
    public void open() {
        Browser.driver.get(url);
        Browser.logger.debug(this.getClass().getSimpleName()
                + " is opened at "
                + CurrentTime.getCurrentTime());
    }

    @Step("Click 'Sigh Out' Button")
    public void clickSignOutButton() {
        sighOutButton.click();
    }

    /**
     * Methods for Username field.
     * @param username
     * @return
     */
    @Step("Enter {username} into Username field")
    public AccountSettingsPage enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    @Step("Click 'Save' button")
    public void saveUsernameForm() {
            save(saveUsernameButton);
        }

    @Step("Verify that username is updated")
    public boolean isUsernameUpdated(String username) {
        String successMessage = getSuccessMessage();
        return successMessage.endsWith("Username updated successfully") ||
                usernameField.getAttribute("value").equals(username);
    }

    /**
     * Methods for Bio form.
     */
    @Step("Enter name ({name}), location ({location}), picture URL ({picture}), about ({about}) into Bio form")
    public AccountSettingsPage enterDataIntoBioForm(String name, String location, String picture, String about) {
        nameField.clear();
        nameField.sendKeys(name);

        locationField.clear();
        locationField.sendKeys(location);

        pictureField.clear();
        pictureField.sendKeys(picture);

        aboutField.clear();
        aboutField.sendKeys(about);

        return this;
    }

    @Step("Click 'Save' button")
    public void saveBioForm() {
            save(saveBioForm);
        }


    @Step("Verify that Bio form is updated")
    public boolean isBioFormUpdated(String name, String location, String picture, String about) {
        String successMessage = getSuccessMessage();
        return successMessage.endsWith("We have successfully updated your account.") ||
                nameField.getAttribute("value").equals(name) ||
                locationField.getAttribute("value").equals(location) ||
                pictureField.getAttribute("value").equals(picture) ||
                aboutField.getAttribute("value").equals(about);
    }

    /**
     * Methods for Delete account feature.
     */
    @Step("Delete Account")
    public void deleteAccount() {
        Browser.waitForElement(10).until(ExpectedConditions.visibilityOf(deleteAccountButton));
        deleteAccountButton.click();
        Browser.waitForElement(10).until(ExpectedConditions.visibilityOf(confirmDeleteButton));
        confirmDeleteButton.click();
    }

    @Step("Verify that account is deleted")
    public void isDeleted() {

    }

    /**
     * Methods for Change email feature.
     */
    @Step("Enter new email ({changedEmail})")
    public AccountSettingsPage enterEmail(String changedEmail) {
        emailField.clear();
        emailField.sendKeys(changedEmail);
        Browser.waitForElement(10).until(ExpectedConditions.attributeToBe(
                emailField, "value", changedEmail)
        );
        return this;
    }

    @Step("Enter confirm email {confirmEmail}")
    public AccountSettingsPage enterConfirmEmail(String confirmEmail) {
        confirmEmailField.clear();
        confirmEmailField.sendKeys(confirmEmail);
        Browser.waitForElement(10).until(ExpectedConditions.attributeToBe(
                confirmEmailField, "value", confirmEmail)
        );
        return this;
    }

    @Step("Click 'Save' button")
    public void saveEmailForm() {
        Browser.waitForElement(10).until(ExpectedConditions.visibilityOf(saveChangeEmailForm));
        saveChangeEmailForm.click();
    }

    @Step("Verify that email is changed to {changedEmail}")
    public boolean isEmailChanged(String changedEmail) {
        Browser.refreshPage();
        return verifyEmailAddressMessage.getText().contains("A change of email adress has not been verified.") ||
                emailField.getAttribute("value").equals(changedEmail);
    }

    /**
     * Methods for 'Change language' test.
     * @param language
     */
    @Step("Select {language} language from the list")
    public void selectLanguage(String language) {
        Select select = new Select(languageSelect);
        select.selectByVisibleText(language);
    }

    @Step("Verify that language is changed to {language}")
    public boolean isLanguageChanged(String language) {
        String message = getSuccessMessage();
        Select select = new Select(languageSelect);
        return message.equalsIgnoreCase("Your language has been updated to '" + language + "'") ||
                select.getFirstSelectedOption().getText().equalsIgnoreCase(language);
    }

    /**
     * Methods for 'Internet Presence form' test.
     */
    @Step("Enter Github URL ({githubURL})")
    public void enterGithubURL(String githubURL) {
        githubURLField.sendKeys(githubURL);
    }

    @Step("Enter Linkedin ({linkedin})")
    public void enterLinkedin(String linkedin) {
        linkedinField.sendKeys(linkedin);
    }

    @Step("Enter Twitter ({twitter})")
    public void enterTwitter(String twitter) {
        twitterField.sendKeys(twitter);
    }

    @Step("Enter Website ({website})")
    public void enterWebsite(String website) {
        websiteField.sendKeys(website);
    }

    @Step("Save 'Internet Presence' form")
    public void saveInternetPresenceForm() {
        save(saveInternetPresenceButton);
    }

    /**
     * LogOut command to log out in precondition or clean up.
     */
    @Step("Log out")
    public void logOut() {
        open();
        sighOutButton.click();
    }

    /**
     * Check if user is signed in.
     */
    @Step("Verify that right user is signed in")
    public boolean isSignedIn() {
        if (!(Browser.driver.getCurrentUrl().equals(url))) open();
        WebElement email = Browser.driver.findElement(By.id("email"));
        return email.getAttribute("value").equals(SignUpPage.getEmail());
    }

    /**
     * Helper methods for steps.
     * @return
     */
    public static String getRelativeUrl() {
        return url;
    }

    private void save(WebElement button) {
        Browser.waitForElement(10).until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    private String getSuccessMessage() {
        Browser.waitForElement(20).until(ExpectedConditions.visibilityOf(message));
        String messageText = message.getText();
        Browser.refreshPage();
        return messageText;
    }

    @Step("Verify that '{message}' message appears")
    public boolean isMessageAppears(String message) {
        String text = getSuccessMessage();
        return text.equals(message);
    }

    @Step("Verify that Github is {github}")
    public boolean isGithubSaved(String github) {
        return githubURLField.getAttribute("value").equals(github);
    }

    @Step("Verify that Linkedin is {linkedin}")
    public boolean isLinkedinSaved(String linkedin) {
        return linkedinField.getAttribute("value").equals(linkedin);
    }

    @Step("Verify that Twitter is {twitter}")
    public boolean isTwitterSaved(String twitter) {
        return twitterField.getAttribute("value").equals(twitter);
    }

    @Step("Verify that Website is {website}")
    public boolean isWebsiteSaved(String website) {
        return websiteField.getAttribute("value").equals(website);
    }
}
