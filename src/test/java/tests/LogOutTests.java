package tests;

import infrastructure.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.eclipse.sisu.Priority;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.AccountSettingsPage;
import pages.HomePage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.navigation.HeaderNavigation;
import utils.EmailGenerator;

/**
 * Created by eugeniya.kruchok on 05.03.2018.
 */
@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
class LogOutTests {

    private static String email = EmailGenerator.generateEmail();

    @BeforeAll
    static void beforeClass() {
        new SignUpPage().signUp(email);
        new AccountSettingsPage().logOut();
    }

    @Test
    @Feature("Sign In")
    @DisplayName("User can log out")
    @Severity(SeverityLevel.NORMAL)
    void canLogOut() {
        SignInPage signInPage = new SignInPage();
        signInPage.signIn(email);

        HeaderNavigation navigation = new HeaderNavigation();
        navigation.openAccountSettings();

        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.clickSignOutButton();

        Assert.assertTrue("Current URL is " + Browser.driver.getCurrentUrl() +
                " while should be " + HomePage.getUrl(), new HomePage().isAt());
    }

    @AfterAll
    static void afterClass() {
        SignInPage signInPage = new SignInPage();
        signInPage.signIn(email);
        signInPage.cleanUp();
        new MailHog().cleanUp();
    }
}
