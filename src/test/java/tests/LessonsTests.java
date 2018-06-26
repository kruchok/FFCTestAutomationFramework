package tests;

import infrastructure.AfterTestClass;
import infrastructure.AllureReportExtension;
import infrastructure.BeforeTestClass;
import infrastructure.MailHog;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LessonPage;
import pages.SignInPage;
import pages.SignUpPage;
import utils.StringGenerator;

@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
class LessonsTests {

    private static String email = StringGenerator.generateEmail();

    @BeforeAll
    static void preconditionSetUp() {
        new SignUpPage().signUp(email);
    }

    @Test
    @DisplayName("User can navigate throughout lessons")
    @Severity(SeverityLevel.CRITICAL)
    void canNavigateThroughoutLessons() {
        LessonPage lessonPage = new LessonPage();
        lessonPage.checkMainMapNav();
    }

    @AfterAll
    static void afterClass() {
        new SignInPage().cleanUp();
        new MailHog().cleanUp();
    }
}
