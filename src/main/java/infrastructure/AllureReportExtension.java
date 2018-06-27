package infrastructure;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.CurrentTime;

import java.util.Optional;

/**
 * Created by eugeniya.kruchok on 27.02.2018.
 */
public class AllureReportExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext testExtensionContext) throws Exception {
        Optional<Throwable> optional;
        if ((optional = testExtensionContext.getExecutionException()).isPresent()) {
            Throwable throwable = optional.get();
            if (throwable instanceof AssertionError || throwable instanceof RuntimeException) {
                makeScreenshotOnFailure(testExtensionContext.getDisplayName() + CurrentTime.getCurrentTime());
            }
        }
    }

    @Attachment("{name} screenshot")
    byte[] makeScreenshotOnFailure(String name) {
        return ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.BYTES);
    }

}
