package infrastructure;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ContainerExtensionContext;

/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class AfterTestClass implements AfterAllCallback {

    @Override
    public void afterAll(ContainerExtensionContext containerExtensionContext) throws Exception {
//        Browser.driver.quit();
    }
}
