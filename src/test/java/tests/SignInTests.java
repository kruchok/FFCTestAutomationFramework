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
 */
@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
class SignInTests {

    private static String email = StringGenerator.generateEmail();

    @BeforeAll
    static void beforeClass() {
        new SignUpPage().signUp(email);
        new AccountSettingsPage().logOut();
    }

    @Test
    @DisplayName("User can sign in")
    @Severity(SeverityLevel.CRITICAL)
    void canSignIn() {
        HomePage homePage = new HomePage();

        homePage.open();
        homePage.openSignUpPage();

        SignInPage signInPage = new SignInPage();
        signInPage.enterEmail(email).clickGetLinkButton();

        Assertions.assertTrue(signInPage.isSuccess(), "There is no success message");

        MailHog mailHog = new MailHog();
        mailHog.open();
        mailHog.followLinkToSignIn();

        Assertions.assertAll("User isn't signed in", () -> {
            Assertions.assertTrue(new StartLearningPage().isAt(), "Current URL is " + Browser.driver.getCurrentUrl() +
                    " while should be " + StartLearningPage.getUrl());
            Assertions.assertTrue(new AccountSettingsPage().isSignedIn(), "User is't signed in");
        });
    }

    @AfterAll
    static void afterClass() {
        new SignInPage().cleanUp();
        new MailHog().cleanUp();
    }


}
