package tests;

import infrastructure.AfterTestClass;
import infrastructure.AllureReportExtension;
import infrastructure.BeforeTestClass;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LessonPage;

@ExtendWith({BeforeTestClass.class, AfterTestClass.class})
@ExtendWith(AllureReportExtension.class)
class LessonsTests {

    @Test
    @DisplayName("User can navigate throughout lessons")
    @Severity(SeverityLevel.CRITICAL)
    void canNavigateThroughoutLessons() {
        LessonPage lessonPage = new LessonPage();
        lessonPage.open();
        lessonPage.checkMainMapNav();
    }

    @Test
    @DisplayName("User can run tests and get success message")
    @Severity(SeverityLevel.CRITICAL)
    void canRunTests() {
        String text = "Hello World!";
        LessonPage lessonPage = new LessonPage();
        lessonPage.open();
        lessonPage.typeInEditor("<h1>" + text + "</h1>");
        lessonPage.runTests();
        Assertions.assertTrue(lessonPage.isSuccessModal(), "There is no Success modal");
    }
}
