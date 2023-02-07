package android.main.fragment;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * Page object that is used for interaction with main fragment.
 */
public class MainFragment {
    @AndroidFindBy(className = "android.widget.TextView")
    private RemoteWebElement textView;

    public MainFragment(final AppiumDriver driver, final Duration timeout) {
        initElements(new AppiumFieldDecorator(driver, timeout), this);
    }

    /**
     * Check if text view has a certain text.
     *
     * @param expected expected text
     * @return current {@link MainFragment} instance
     */
    @Step("Check if text view has a certain text")
    public MainFragment assertTextViewHasText(final String expected) {
        assertEquals(expected, textView.getText());
        return this;
    }
}
