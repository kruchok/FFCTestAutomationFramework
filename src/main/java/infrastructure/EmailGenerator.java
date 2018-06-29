package infrastructure;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PageObject;

public class EmailGenerator extends PageObject {

    public EmailGenerator() { super();}

//    @FindBy(css = "a[href=\"/email-generator\"]")
//    private WebElement generateEmailButton;

//    @FindBy(id = "userName")
//    private WebElement userName;

//    @FindBy(id = "domainName2")
//    private WebElement domainName;

    @FindBy(id = "mail")
    private WebElement mail;

    @FindBy(css = ".pm-text p:nth-child(2) b")
    private WebElement codeField;

    @FindBy(id = "click-to-refresh")
    private WebElement refresh;

    @FindBy(css = ".title-subject")
    private WebElement titleSubject;

    @FindBy(css = ".click-to-delete-mail")
    private WebElement clickToDelete;

    private static final String URL = "https://temp-mail.org/";

    @Step("Open email page on Mail Generator")
    public void open() {
        Driver.driver.get(URL);
    }

    @Step("Open Mail Generator page in new tab")
    public void openInNewTab() {
        ((JavascriptExecutor) Driver.driver).executeScript("window.open('" + URL + "');");
        Driver.switchToLastWindow();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Get random email")
    public String getEmail() {
        if (!(Driver.driver.getCurrentUrl().equals(URL))) {
            this.open();
        }
        return mail.getAttribute("value");
    }

    @Step("Get code for authentication")
    public String getCode() {
        String code = "";
        try {
            Thread.sleep(5000);
            refresh.click();
            titleSubject.click();
            code = codeField.getText();
            clickToDelete.click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Driver.driver.close();
        Driver.switchToWindow(0);
        return code;
    }
}
