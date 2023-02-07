package android.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static java.lang.Boolean.parseBoolean;
import static java.util.Objects.isNull;

public class ElementUtils {
    /**
     * Getting the state of the element display.
     *
     * @param element {@link WebElement}
     * @return {@link Boolean} true if it is displayed, otherwise false
     */
    @Step("Getting the state of the element display")
    public static Boolean elementIsDisplayed(final WebElement element) {
        if (isNull(element)) return false;
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Getting the clickable state of the element.
     *
     * @param element {@link WebElement}
     * @return {@link Boolean} true if it is clickable, otherwise false
     */
    @Step("Getting the clickable state of the element")
    public static Boolean elementIsClickable(final WebElement element) {
        if (isNull(element)) return false;
        try {
            return parseBoolean(element.getAttribute("clickable"));
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
