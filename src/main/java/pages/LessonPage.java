package pages;

import infrastructure.Config;
import infrastructure.Driver;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.regex.Pattern;

public class LessonPage extends PageObject {

    private static final String URL = Driver.baseUrl + Driver.getRelativeUrl("challenges") +
            Driver.getRelativeUrl("basicHtmlAndHtml5") +
            Driver.getRelativeUrl("sayHello");

    public LessonPage() { super(); }

    @FindBy(css = ".map-accordion > .map-accordion-panel")
    private List<WebElement> chaptersLinks;

    @FindBy(css = ".challenges-title")
    private WebElement challengeTitle;

    @FindBy(css = ".classic-editor .CodeMirror-code .CodeMirror-line")
    private WebElement firstLine;

    @FindBy(css = ".classic-editor .CodeMirror-lines")
    private WebElement lines;

    @FindBy(css = "[id=\"fcc-main-frame\"]")
    private WebElement resultFrame;

    @FindBy(css = "body")
    private WebElement resultBody;

    @FindBy(css = "button[data-reactid=\"426\"]")
    private WebElement runCodeButton;

    @FindBy(css = ".challenges-success-modal")
    private WebElement challengeSuccessModal;

    @Step("Open lesson page")
    public void open() {
        Driver.driver.get(URL);
    }

    @Step("Navigate throughout chapters and lessons")
    public void checkMainMapNav() {
        WebElement link = chaptersLinks.get((int) (Math.random()*chaptersLinks.size()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        link.findElement(By.cssSelector("a")).click();
        checkNestedLink(link);
    }

    @Step("Type \"{text}\" into Editor")
    public void typeInEditor(String text) {
        Actions action = new Actions(Driver.driver);
        action.moveToElement(firstLine);
        action.click();
        action.sendKeys(text);
        action.build().perform();
    }

    @Step("Run tests")
    public void runTests() {
        runCodeButton.click();
    }

    @Step("Verify that Success modal presents")
    public boolean isSuccessModal() {
        try {
            Driver.waitForElement(5).until(ExpectedConditions.visibilityOf(challengeSuccessModal));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Verify that Editor text is \"{text}\"")
    public boolean isEditorText(String text) {
        return this.getEditorText().contains(text);
    }

    @Step("Verify that result text is \"{text}\"")
    public boolean isResultText(String text) {

        return this.getResultText().equals(text);
    }

    private void checkNestedLink(WebElement link) {
        List<WebElement> nestedLinks = getNestedLinks(link);
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
        String title = challengeTitle.getText();
        Assertions.assertTrue(
                title.equalsIgnoreCase(taskTitle),
                "Task title doesn't match lessons list. Title is \""
                        + title
                        + "\"" + ", but it is \""
                        + taskTitle + "\"");
    }

    public String getEditorText() {
        return lines.getText();
    }

    public List<WebElement> getNestedLinks(WebElement link) {
        return link.findElements(By.cssSelector(".map-accordion-panel.map-accordion-panel-default"));
    }

    public String getResultText() {
        Driver.driver.switchTo().frame(resultFrame);
        String resultText = resultBody.getText();
        Driver.driver.switchTo().parentFrame();
        return resultText;
    }
}
