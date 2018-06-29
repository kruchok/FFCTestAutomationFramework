package tests;

import infrastructure.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 */
@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
class SignInTests {

    @BeforeAll
    static void beforeClass() {
//        Driver.clearCookies();
    }

    @Test
    @DisplayName("User can sign in")
    @Severity(SeverityLevel.CRITICAL)
    void canSignIn() {
        EmailGenerator generator = new EmailGenerator();
        generator.open();
        String email = generator.getEmail();

        HomePage homePage = new HomePage();
        homePage.open();
        homePage.openSignInPage();

        SignInPage signInPage = new SignInPage();
        signInPage.enterEmail(email).clickGetCodeButton();

        generator.openInNewTab();
        String code = generator.getCode();
        signInPage.enterCode(code).submit();

        PrivacyPolicyPage policyPage = new PrivacyPolicyPage();
        Assertions.assertTrue(policyPage.isAt());

        policyPage.acceptTerms();

        Assertions.assertAll("User isn't signed in", () -> {
            Assertions.assertTrue(homePage.isAt(), "Current URL is " + Driver.driver.getCurrentUrl() +
                    " while should be " + homePage.getUrl());
        });
    }

    @AfterAll
    static void cleanUp() {
        new SignInPage().cleanUp();
    }
}
