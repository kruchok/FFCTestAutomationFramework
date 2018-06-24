package tests;

import infrastructure.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AccountSettingsPage;
import pages.SignUpPage;
import pages.navigation.HeaderNavigation;
import utils.StringGenerator;

/**
 * Created by eugeniya.kruchok on 06.03.2018.
 */
@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
class AccountSettingsTests {

    private static String email = StringGenerator.generateEmail();
    private static String username = StringGenerator.generateUsername();
    private static String name = CsvDataProvider.get("name");
    private static String location = CsvDataProvider.get("location");
    private static String picture = CsvDataProvider.get("picture");
    private static String about = CsvDataProvider.get("about");
    private static String changedEmail = StringGenerator.generateEmail();
    private static String language = CsvDataProvider.get("language");
    private static String github = CsvDataProvider.get("githubUrl");
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

        Assertions.assertTrue(settingsPage.isUsernameUpdated(username), "No success message");
    }

    @Test
    @DisplayName("User can edit Bio")
    @Severity(SeverityLevel.NORMAL)
    void canEditBio() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.enterDataIntoBioForm(name, location, picture, about).saveBioForm();

        Assertions.assertTrue(settingsPage.isBioFormUpdated(name, location, picture, about), "Bio form isn't updated");
    }

    @Test
    @DisplayName("User can change email")
    @Severity(SeverityLevel.CRITICAL)
    void canChangeEmail() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.enterEmail(changedEmail).enterConfirmEmail(changedEmail).saveEmailForm();

        Assertions.assertTrue(settingsPage.isEmailChanged(changedEmail), "Email isn't changed");
    }

    @Test
    @DisplayName("User can change language")
    @Severity(SeverityLevel.CRITICAL)
    void canChangeLanguage() {
        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.selectLanguage(language);

        Assertions.assertTrue(settingsPage.isLanguageChanged(language), "Language wasn't changed");
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
                    Assertions.assertTrue(settingsPage.isGithubSaved("hhfhfha"), "GitHub link isn't saved");
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
