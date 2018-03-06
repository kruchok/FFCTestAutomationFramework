package infrastructure;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.CurrentTime;

import java.util.Optional;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 */
public class AllureReportExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(TestExtensionContext testExtensionContext) throws Exception {
        Optional<Throwable> optional;
        if ((optional = testExtensionContext.getTestException()).isPresent()) {
            Throwable throwable = optional.get();
            if (throwable instanceof AssertionError) {
                makeScreenshotOnFailure(testExtensionContext.getDisplayName() + CurrentTime.getCurrentTime());
            }
        }
    }

    @Attachment("{name} screenshot")
    byte[] makeScreenshotOnFailure(String name) {
        return ((TakesScreenshot) Browser.driver).getScreenshotAs(OutputType.BYTES);
    }

}
