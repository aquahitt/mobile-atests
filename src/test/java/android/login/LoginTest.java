package android.login;

import android.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Epic("Sign in to application")
@DisplayName("Sign in to application")
@Feature("Login")
@TestMethodOrder(OrderAnnotation.class)
public class LoginTest extends BaseTest {
    @Test
    @Order(1)
    @Tags({@Tag("Positive")})
    @DisplayName("Check initial state of screen elements")
    void checkScreenElements() {
        loginFragment.assertFormTitleHasText("Вход в Alfa-Test")
                .assertUsernameFieldIsClickable()
                .assertUsernameFieldPlaceholderHasText("Логин")
                .assertPasswordFieldIsClickable()
                .assertPasswordFieldPlaceholderHasText("Пароль")
                .assertConfirmButtonIsClickable()
                .assertShowPasswordToggleIsClickable()
                .assertConfirmButtonHasText("Вход")
                .assertErrorMessageIsEmpty();
    }

    @Test
    @Order(2)
    @Tags({@Tag("Negative")})
    @DisplayName("Error when username and password are empty")
    void loginWithEmptyAuthData() {
        loginFragment.clearUsernameField()
                .clearPasswordField()
                .clickSignInButton()
                .assertErrorMessageHasText("Введены неверные данные");
    }

    @Test
    @Order(3)
    @Tags({@Tag("Negative")})
    @DisplayName("Error when password is incorrect")
    void loginWithIncorrectPassword() {
        loginFragment.enterUsername("Login")
                .enterPassword(randomAlphanumeric(nextInt(1, 51)))
                .clickSignInButton();
        loader.assertLoaderIsDisplayed()
                .waitLoaderIsFinished();
        loginFragment.assertErrorMessageHasText("Введены неверные данные");
    }

    @Test
    @Order(4)
    @Tags({@Tag("Negative")})
    @DisplayName("Loader isn't displayed when password is too long")
    void loginWithVeryLongPassword() {
        loginFragment.enterUsername("Login")
                .enterPassword(randomAlphanumeric(51))
                .clickSignInButton();
        loader.assertLoaderIsNotDisplayed();
    }

    @Test
    @Order(5)
    @Tags({@Tag("Negative")})
    @DisplayName("Loader isn't displayed when username is too long")
    void loginWithVeryLongUsername() {
        loginFragment.enterUsername(randomAlphanumeric(51))
                .enterPassword("Password")
                .clickSignInButton();
        loader.assertLoaderIsNotDisplayed();
    }

    @Test
    @Order(6)
    @Tags({@Tag("Negative")})
    @DisplayName("Error when incorrect registry")
    @Description("Should show error message when username and password are correct but has incorrect registry")
    void loginWithIncorrectRegistry() {
        loginFragment.enterUsername("LoGiN")
                .enterPassword("PaSsWoRd")
                .clickSignInButton();
        loader.assertLoaderIsDisplayed()
                .waitLoaderIsFinished();
        loginFragment.assertErrorMessageHasText("Введены неверные данные");
    }

    @Test
    @Order(7)
    @Tags({@Tag("Positive")})
    @DisplayName("SignIn with correct username and password")
    void loginWithCorrectAuthData() {
        loginFragment.enterUsername("Login")
                .enterPassword("Password")
                .clickSignInButton();
        loader.assertLoaderIsDisplayed()
                .waitLoaderIsFinished();
        mainFragment.assertTextViewHasText("Вход в Alfa-Test выполнен");
    }
}
