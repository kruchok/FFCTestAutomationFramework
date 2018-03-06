package tests;

import infrastructure.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import pages.interfaces.SignIn;
import pages.navigation.HeaderNavigation;
import utils.EmailGenerator;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 */
@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
public class SignInTests {

    private static String email = EmailGenerator.generateEmail();

    @BeforeAll
    public static void beforeClass() {
        new SignUpPage().signUp(email);
        new AccountSettingsPage().logOut();
    }

    @Test
    @Feature("Sign In")
    @DisplayName("User can sign in")
    @Severity(SeverityLevel.CRITICAL)
    public void canSignIn() {
        HomePage homePage = new HomePage();

        homePage.open();
        homePage.openSignUpPage();

        SignInPage signInPage = new SignInPage();
        signInPage.enterEmail(email).clickGetLinkButton();

        Assert.assertTrue("There is no success message", signInPage.isSuccess());

        MailHog mailHog = new MailHog();
        mailHog.open();
        mailHog.followLinkToSignIn();

        Assert.assertTrue("Current URL is " + Browser.driver.getCurrentUrl() +
                " while should be " + StartLearningPage.getUrl(), new StartLearningPage().isAt());
        Assert.assertTrue("User is't signed in", new AccountSettingsPage().isSignedIn());
    }

    @AfterAll
    public static void afterClass() {
        new SignInPage().cleanUp();
        new MailHog().cleanUp();
    }


}
