package infrastructure;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class AfterTestClass implements AfterAllCallback {

    @Override
    public void afterAll(ExtensionContext containerExtensionContext) throws Exception {
//        Browser.driver.quit();
    }
}
