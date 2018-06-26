package pages;

import infrastructure.Driver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by eugeniya.kruchok on 26.02.2018.
 */
public abstract class PageObject {

    public PageObject() {
        PageFactory.initElements(Driver.driver, this);
    }
}
