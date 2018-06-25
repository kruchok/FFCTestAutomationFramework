package infrastructure;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.PageObject;


/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class BeforeTestClass implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext containerExtensionContext) throws Exception {
        Config.initProperties();
        Driver.setDriver();
        PageObject.initBaseUrl();
        Driver.driver.get(PageObject.baseUrl);
        AllureEnvironment allureEnvironment = new AllureEnvironment();
        CsvDataProvider.init();
        Driver.logger = LogManager.getLogger();
    }
}
