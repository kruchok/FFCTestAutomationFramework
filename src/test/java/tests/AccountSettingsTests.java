package tests;

import infrastructure.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AccountSettingsPage;
import pages.SignUpPage;
import pages.navigation.HeaderNavigation;
import utils.EmailGenerator;
import utils.UsernameGenerator;

/**
 * Created by eugeniya.kruchok on 06.03.2018.
 */
@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
class AccountSettingsTests {

    private static String email = EmailGenerator.generateEmail();
    private static String username = UsernameGenerator.generateUsername();
    private static String name = CsvDataProvider.get("name");
    private static String location = CsvDataProvider.get("location");
    private static String picture = CsvDataProvider.get("picture");
    private static String about = CsvDataProvider.get("about");
    private static String changedEmail = CsvDataProvider.get("email");
    private static String language = CsvDataProvider.get("language");
    private static String github = CsvDataProvider.get("github");
    private static String linkedin = CsvDataProvider.get("linkedin");
    private static String twitter = CsvDataProvider.get("twitter");
    private static String website = CsvDataProvider.get("website");

    @BeforeAll
    static void preconditionSetUp() {
        new SignUpPage().signUp(email);
    }

    @BeforeEach
    void beforeEachTest() {
        new HeaderNavigation().openAccountSettings();
    }

    @Test
    @DisplayName("User can change Username")
    @Severity(SeverityLevel.TRIVIAL)
    void canChangeUsername() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.enterUsername(username).saveUsernameForm();

        Assert.assertTrue("No success message", settingsPage.isUsernameUpdated(username));
    }

    @Test
    @DisplayName("User can edit Bio")
    @Severity(SeverityLevel.NORMAL)
    void canEditBio() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.enterDataIntoBioForm(name, location, picture, about).saveBioForm();

        Assert.assertTrue("Bio form isn't updated", settingsPage.isBioFormUpdated(name, location, picture, about));
    }

    @Test
    @DisplayName("User can change email")
    @Severity(SeverityLevel.CRITICAL)
    void canChangeEmail() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.enterEmail(changedEmail).enterConfirmEmail(changedEmail).saveEmailForm();

        Assert.assertTrue("Email isn't changed", settingsPage.isEmailChanged(changedEmail));
    }

    @Test
    @DisplayName("User can change language")
    @Severity(SeverityLevel.CRITICAL)
    void canChangeLanguage() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.selectLanguage(language);

        Assert.assertTrue("Language wasn't changed", settingsPage.isLanguageChanged(language));
    }

    @Test
    @DisplayName("User can edit 'Internet Presence' form")
    @Severity(SeverityLevel.CRITICAL)
    void canEditInternetPresenceForm() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.enterGithubURL(github);
        settingsPage.enterLinkedin(linkedin);
        settingsPage.enterTwitter(twitter);
        settingsPage.enterWebsite(website);

        settingsPage.saveInternetPresenceForm();

//        Assert.assertTrue("'Internet Presence' form isn't saved",
//                settingsPage.isInternetPresenceSaved(github, linkedin, twitter, website));

        Assertions.assertAll("'Internet Presence' form isn't saved", () -> {
                    Assertions.assertTrue(settingsPage.isMessageAppears("We have successfully updated your account."));
                    Assertions.assertTrue(settingsPage.isGithubSaved(github));
                    Assertions.assertTrue(settingsPage.isLinkedinSaved(linkedin));
                    Assertions.assertTrue(settingsPage.isTwitterSaved(twitter));
                    Assertions.assertTrue(settingsPage.isWebsiteSaved(website));
                }
            );
    }

    @AfterAll
    static void cleanUp() {
        new SignUpPage().cleanUp();
        new MailHog().cleanUp();
    }
}
