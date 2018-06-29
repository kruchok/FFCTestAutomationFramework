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

    private static final String URL = "https://learn.freecodecamp.org";
    private static final String HELLO_LESSON = "/responsive-web-design/basic-html-and-html5/say-hello-to-html-elements";

    public LessonPage() { super(); }

    @FindBy(css = ".map-challenge-title a")
    private List<WebElement> challengeTitles;

    @FindBy(css = ".text-center.challenge-title")
    private WebElement tittle;

    @FindBy(css = ".view-line")
    private WebElement firstLine;

    @FindBy(css = ".classic-editor .CodeMirror-lines")
    private WebElement lines;

    @FindBy(css = "[id=\"fcc-main-frame\"]")
    private WebElement resultFrame;

    @FindBy(css = "body")
    private WebElement resultBody;

    @FindBy(css = ".instructions-panel .tool-panel-group button")
    private WebElement runCodeButton;

    @FindBy(css = ".challenge-success-modal")
    private WebElement challengeSuccessModal;

    @Step("Open lesson page")
    public void open() {
        Driver.driver.get(URL);
    }

    @Step("Open Hello World lesson")
    public void openHelloWorldLesson() {
        Driver.driver.get(URL + HELLO_LESSON);
    }

    @Step("Navigate throughout chapters and lessons")
    public void checkMainMapNav() {
        challengeTitles.remove(0);
        challengeTitles.get((int) (Math.random()*challengeTitles.size())).click();
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

    @Step("Verify that title of lesson presents")
    public boolean isTitle() {
        Driver.waitForElement(5).until(ExpectedConditions.visibilityOf(tittle));
        return tittle.isDisplayed();
    }

    public String getEditorText() {
        return lines.getText();
    }

    public List<WebElement> getNestedLinks(WebElement link) {
        return link.findElements(By.cssSelector(".map-title"));
    }

    public String getResultText() {
        Driver.driver.switchTo().frame(resultFrame);
        String resultText = resultBody.getText();
        Driver.driver.switchTo().parentFrame();
        return resultText;
    }
}
