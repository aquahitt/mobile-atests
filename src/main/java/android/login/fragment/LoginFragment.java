package android.login.fragment;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;

import static android.utils.ElementUtils.elementIsClickable;
import static java.lang.Boolean.parseBoolean;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;

/**
 * Page object that is used for interaction with login fragment.
 */
@Slf4j
public class LoginFragment {
    @AndroidFindBy(id = "tvTitle")
    private RemoteWebElement formTitle;
    @AndroidFindBy(id = "etUsername")
    private RemoteWebElement usernameEditText;
    @AndroidFindBy(id = "etPassword")
    private RemoteWebElement passwordEditText;

    @AndroidFindBy(accessibility = "Show password")
    private RemoteWebElement showPasswordToggle;

    @AndroidFindBy(id = "btnConfirm")
    private RemoteWebElement confirmButton;
    @AndroidFindBy(id = "tvError")
    private RemoteWebElement errorTextView;

    public LoginFragment(final AppiumDriver driver, final Duration timeout) {
        initElements(new AppiumFieldDecorator(driver, timeout), this);
    }

    /**
     * Clear username input field.
     *
     * @return current {@link LoginFragment} instance
     */
    @Step("Clear username input field")
    public LoginFragment clearUsernameField() {
        usernameEditText.clear();
        return this;
    }

    /**
     * Enter username to input field.
     *
     * @param username user username
     * @return current {@link LoginFragment} instance
     */
    @Step("Enter username to input field")
    public LoginFragment enterUsername(final String username) {
        usernameEditText.clear();
        usernameEditText.sendKeys(username);
        assertEquals(username, usernameEditText.getText());
        return this;
    }

    /**
     * Clear password input field.
     *
     * @return current {@link LoginFragment} instance
     */
    @Step("Clear password input field")
    public LoginFragment clearPasswordField() {
        passwordEditText.clear();
        return this;
    }

    /**
     * Enter password to input field.
     *
     * @param password user password
     * @return current {@link LoginFragment} instance
     */
    @Step("Enter username to input field")
    public LoginFragment enterPassword(final String password) {
        passwordEditText.clear();
        passwordEditText.sendKeys(password);
        assertEquals(password, passwordEditText.getText());
        return this;
    }

    /**
     * Click 'SignIn' button.
     *
     * @return current {@link LoginFragment} instance
     */
    @Step("Click 'SignIn' button")
    public LoginFragment clickSignInButton() {
        confirmButton.click();
        return this;
    }

    @Step("Check if form title has a certain text")
    public LoginFragment assertFormTitleHasText(final String expected) {
        assertEquals(expected, formTitle.getText());
        return this;
    }

    @Step("Check if username field placeholder has a certain text")
    public LoginFragment assertUsernameFieldPlaceholderHasText(final String expected) {
        assertEquals(expected, usernameEditText.getText());
        return this;
    }

    @Step("Check if username field is clickable")
    public LoginFragment assertUsernameFieldIsClickable() {
        assertTrue(elementIsClickable(usernameEditText), "Username field is clickable");
        return this;
    }

    @Step("Check if password field placeholder has a certain text")
    public LoginFragment assertPasswordFieldPlaceholderHasText(final String expected) {
        assertEquals(passwordEditText.getText(), expected);
        return this;
    }

    @Step("Check if password field is clickable")
    public LoginFragment assertPasswordFieldIsClickable() {
        assertTrue(elementIsClickable(passwordEditText), "Confirm button is clickable");
        return this;
    }

    @Step("Check if error message is empty")
    public LoginFragment assertErrorMessageIsEmpty() {
        assertEquals(EMPTY, errorTextView.getText());
        return this;
    }

    @Step("Check if error message has a certain text")
    public LoginFragment assertErrorMessageHasText(final String expected) {
        assertEquals(expected, errorTextView.getText());
        return this;
    }

    @Step("Check if confirm button is clickable")
    public LoginFragment assertConfirmButtonIsClickable() {
        assertTrue(elementIsClickable(confirmButton), "Confirm button is clickable");
        return this;
    }

    @Step("Check if confirm button has a certain text")
    public LoginFragment assertConfirmButtonHasText(final String expected) {
        assertEquals(expected, confirmButton.getText());
        return this;
    }

    @Step("Check if shop password toggle is clickable")
    public LoginFragment assertShowPasswordToggleIsClickable() {
        assertTrue(parseBoolean(showPasswordToggle.getAttribute("clickable")), "Element is clickable");
        return this;
    }
}
