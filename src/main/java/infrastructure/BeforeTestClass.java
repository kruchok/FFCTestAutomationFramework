package infrastructure;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import pages.PageObject;

/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class BeforeTestClass implements BeforeAllCallback {

    @Override
    public void beforeAll(ContainerExtensionContext containerExtensionContext) throws Exception {
        Property.initProperties();
        Browser.setDriver();
        PageObject.initBaseUrl();
        Browser.driver.get(PageObject.baseUrl);
        AllureEnvironment allureEnvironment = new AllureEnvironment();
        CsvDataProvider.init();
    }
}
