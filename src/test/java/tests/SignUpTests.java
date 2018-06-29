package tests;

import infrastructure.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;
import utils.StringGenerator;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 * Instance of page must be initialized to work with it.
 */
@Disabled("This feature was recently deprecated")
@ExtendWith(BeforeTestClass.class)
@ExtendWith(AfterTestClass.class)
@ExtendWith(AllureReportExtension.class)
class SignUpTests {

    private static String email = StringGenerator.generateEmail();

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
        homePage.openSignInPage();
        signUpPage.enterEmail(email).clickGetLinkButton();

        Assertions.assertTrue(signUpPage.isSuccess(), "Wrong text for success message");

        mailHog.open();
        mailHog.followLinkToSignIn();

        Assertions.assertTrue(new StartLearningPage().isAt(), "User isn't on Start Learning page");
        Assertions.assertTrue(new AccountSettingsPage().isSignedIn(), "User is't signed in");
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
