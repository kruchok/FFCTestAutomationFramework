package pages.navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageObject;

import java.util.List;

public class LessonsMap extends PageObject {

    public LessonsMap() { super(); }

    @FindBy(css = ".map-accordion > .map-accordion-panel")
    private List<WebElement> chaptersLinks;

    public List<WebElement> getNestedLinks(WebElement link) {
        return link.findElements(By.cssSelector(".map-accordion-panel.map-accordion-panel-default"));
    }

    public List<WebElement> getChaptersLinks() {
        return chaptersLinks;
    }
}
