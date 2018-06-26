package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.navigation.LessonsMap;
import pages.widgets.ChallengeWidget;

import java.util.List;

public class LessonPage extends PageObject {
    private LessonsMap lessonsMap;
    private ChallengeWidget taskWidget;

    public LessonPage() { super(); }

    @Step("Navigate throughout chapters and lessons")
    public void checkMainMapNav() {
        lessonsMap = new LessonsMap();
        taskWidget = new ChallengeWidget();
        List<WebElement> list = lessonsMap.getChaptersLinks();
        WebElement link = list.get((int) (Math.random()*list.size()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        link.findElement(By.cssSelector("a span")).click();
        checkNestedLink(link);
    }

    private void checkNestedLink(WebElement link) {
        List<WebElement> nestedLinks = lessonsMap.getNestedLinks(link);
        WebElement nstLink = nestedLinks.get((int) ((Math.random()*nestedLinks.size())));
        WebElement nsLink = nstLink.findElement(By.tagName("a"));
        String chapterTitle = nsLink.getText().split("\\(")[0]
                .replace("\n", "");
        nsLink.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> lessons = nstLink.findElements(By.tagName("a"));
        lessons.remove(0);
        checkLesson(chapterTitle, lessons.get((int) ((Math.random()*lessons.size()))));
    }

    private void checkLesson(String chapterTitle, WebElement lesson) {
        String taskTitle = chapterTitle + ": " + lesson.getText();
        lesson.click();
        isTitle(taskTitle);
    }

    private void isTitle(String taskTitle) {
        String title = taskWidget.getChallengeTitleText();
        Assertions.assertTrue(
                title.equalsIgnoreCase(taskTitle),
                "Task title doesn't match lessons list. Title is \""
                        + title
                        + "\"" + ", but it is \""
                        + taskTitle + "\"");
    }
}
