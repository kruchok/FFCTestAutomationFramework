package tests;

import infrastructure.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import utils.EmailGenerator;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 * Instance of page must be initialized to work with it.
 */

@ExtendWith(BeforeTestClass.class)
@ExtendWith(AfterTestClass.class)
@ExtendWith(AllureReportExtension.class)
class SignUpTests {

    private static String email = EmailGenerator.generateEmail();

    @Test
    @Feature("Sign Up")
    @DisplayName("User can sign up")
    @Severity(SeverityLevel.CRITICAL)
    void canSignUp() {

        /**
         * Initialize page instances.
         */
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = new SignUpPage();
        MailHog mailHog = new MailHog();

        /**
         * Steps
         */

        homePage.open();
        homePage.openSignUpPage();
        signUpPage.enterEmail(email).clickGetLinkButton();

        Assert.assertTrue("Wrong text for success message", signUpPage.isSuccess());

        mailHog.open();
        mailHog.followLinkToSignIn();

        Assert.assertTrue("User isn't on Start Learning page", new StartLearningPage().isAt());
        Assert.assertTrue("User is't signed in", new AccountSettingsPage().isSignedIn());
    }

    @AfterEach
    void afterTestCleanUp() {
        new SignUpPage().cleanUp();
    }

    @AfterAll
    static void afterClassCleanUp() {
        new MailHog().cleanUp();
    }
}
