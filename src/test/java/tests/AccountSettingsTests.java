package tests;

import infrastructure.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
public class AccountSettingsTests {

    private static String email = EmailGenerator.generateEmail();
    private static String username = UsernameGenerator.generateUsername();

    @BeforeAll
    public static void preconditionSetUp() {
        new SignUpPage().signUp(email);
    }

    @Test
    @DisplayName("User can change Username")
    @Severity(SeverityLevel.TRIVIAL)
    public void canChangeUsername() {
        new HeaderNavigation().openAccountSettings();

        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.enterUsername(username).clickSaveButton();

        Assert.assertTrue("No success message", settingsPage.isUsernameUpdated(username));
    }

    @AfterAll
    public static void cleanUp() {
        new SignUpPage().cleanUp();
        new MailHog().cleanUp();
    }
}
