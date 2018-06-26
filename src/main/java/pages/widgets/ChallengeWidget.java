package pages.widgets;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageObject;

public class ChallengeWidget extends PageObject {

    public ChallengeWidget() { super(); }

    @FindBy(css = ".challenges-instructions-panel")
    private WebElement challengeBar;

    @FindBy(css = ".challenges-title")
    private WebElement challengeTitle;

    public String getChallengeTitleText() {
        return challengeTitle.getText();
    }
}
