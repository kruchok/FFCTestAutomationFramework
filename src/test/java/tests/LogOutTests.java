package tests;

import infrastructure.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
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
import utils.StringGenerator;

/**
 * Created by eugeniya.kruchok on 05.03.2018.
 */
@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
class LogOutTests {

//    private static String email = StringGenerator.generateEmail();

    @BeforeAll
    static void beforeClass() {
        String email = new EmailGenerator().getEmail();
        SignInPage signInPage = new SignInPage();
        signInPage.open();
        new SignInPage().signIn(email);
    }

    @Test
    @DisplayName("User can log out")
    @Severity(SeverityLevel.NORMAL)
    void canLogOut() {
        HeaderNavigation navigation = new HeaderNavigation();
        navigation.openAccountSettings();

        AccountSettingsPage settingsPage = new AccountSettingsPage();
        settingsPage.clickSignOutButton();

        Assertions.assertTrue(new HomePage().isAt(), "Current URL is " + Driver.driver.getCurrentUrl() +
                " while should be " + new HomePage().getUrl());
    }

    @AfterAll
    static void afterClass() {
        String email = new EmailGenerator().getEmail();
        SignInPage signInPage = new SignInPage();
        signInPage.open();
        signInPage.signIn(email);
        signInPage.cleanUp();
    }
}
