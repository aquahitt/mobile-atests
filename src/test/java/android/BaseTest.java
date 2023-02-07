package android;

import android.base.element.Loader;
import android.login.fragment.LoginFragment;
import android.main.fragment.MainFragment;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

import static android.BaseConfig.*;
import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@Slf4j
public class BaseTest {
    protected static AppiumDriver driver;
    private static Duration implicitWaitTimeout = null;
    private static Duration explicitWaitTimeout = null;

    protected Loader loader = new Loader(driver, explicitWaitTimeout);
    protected LoginFragment loginFragment = new LoginFragment(driver, explicitWaitTimeout);
    protected MainFragment mainFragment = new MainFragment(driver, explicitWaitTimeout);

    @BeforeAll
    @DisplayName("Setting up the Appium driver to run")
    public static void setUp() {
        loadConfigProperties();
        implicitWaitTimeout = ofSeconds(configProperties.getLong("implicit.wait.timeout"));
        explicitWaitTimeout = ofSeconds(configProperties.getLong("explicit.wait.timeout"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(DEVICE_NAME, configProperties.getString("android.device.name"));
        capabilities.setCapability(PLATFORM_NAME, "android");
        capabilities.setCapability(APP, getApkFile().getAbsolutePath());

        driver = new AppiumDriver(getAppiumServerURL(), capabilities);
        driver.manage().timeouts().implicitlyWait(implicitWaitTimeout);
    }

    @AfterAll
    @DisplayName("Turning off the Appium driver")
    public static void tearDown() {
        driver.quit();
    }
}
