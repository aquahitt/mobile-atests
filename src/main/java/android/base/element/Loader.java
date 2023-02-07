package android.base.element;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static android.utils.ElementUtils.elementIsDisplayed;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

/**
 * Page object that is used for interaction with loader element.
 */
@Slf4j
public class Loader {
    private final By LOADER_LOCATOR = By.id("loader");
    private AppiumDriver driver;
    private Duration timeout;

    public Loader(final AppiumDriver driver, final Duration timeout) {
        this.driver = driver;
        this.timeout = timeout;
    }

    /**
     * Find and return {@link WebElement} loader if it was found, otherwise null.
     *
     * @param locator {@link By} locator to find an element
     * @return {@link WebElement} loader if it was found, otherwise null
     */
    @Step("Find and get the loader on the screen")
    private WebElement getLoader(final By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Wait for the loader to hide.
     *
     * @return current {@link Loader} instance
     */
    @Step("Wait for the loader to hide")
    public Loader waitLoaderIsFinished() {
        try {
            new FluentWait<>(driver)
                    .withTimeout(timeout)
                    .pollingEvery(ofMillis(500))
                    .until(invisibilityOf(getLoader(LOADER_LOCATOR)));
        } finally {
            return this;
        }
    }

    /**
     * Check if loader is not displayed.
     *
     * @return current {@link Loader} instance
     */
    @Step("Check if loader is not displayed")
    public Loader assertLoaderIsNotDisplayed() {
        assertFalse(elementIsDisplayed(getLoader(LOADER_LOCATOR)), "Loader is displayed");
        return this;
    }

    /**
     * Check if loader is displayed.
     *
     * @return current {@link Loader} instance
     */
    @Step("Check if loader is displayed")
    public Loader assertLoaderIsDisplayed() {
        assertTrue(elementIsDisplayed(getLoader(LOADER_LOCATOR)), "Loader is displayed");
        return this;
    }
}
