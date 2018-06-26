package infrastructure;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


/**
 * Created by eugeniya.kruchok on 01.03.2018.
 */
public class BeforeTestClass implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext containerExtensionContext) throws Exception {
        Config.initProperties();
        Driver.setDriver();
        Driver.initBaseUrl();
        Driver.driver.get(Driver.baseUrl);
        AllureEnvironment allureEnvironment = new AllureEnvironment();
        CsvDataProvider.init();
        Driver.logger = LogManager.getLogger();
    }
}
