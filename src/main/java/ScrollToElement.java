import io.testproject.java.annotations.v2.Action;
import io.testproject.java.sdk.v2.addons.WebElementAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

@Action(name = "Scroll To Element With Javascript",
        description = "Using Javascript to scroll to the element",
        summary = "Scroll to a given element using Javascript")
public class ScrollToElement implements WebElementAction {
    @Override
    public ExecutionResult execute(WebAddonHelper helper, WebElement element) throws FailureException {
        ActionReporter reporter = helper.getReporter();
        WebDriver driver = helper.getDriver();
        element = driver.findElement(helper.getSearchCriteria());
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            if(element != null && element.isDisplayed()) {
                reporter.result("Element was scrolled to successfully.");
                return ExecutionResult.PASSED;
            } else {
                reporter.result("Element was not found.");
                return ExecutionResult.FAILED;
            }
        } catch(Exception e) {
            reporter.result("Failed to execute script: " + e.toString());
            return ExecutionResult.FAILED;
        }
    }
}
