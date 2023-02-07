package android;

import android.base.element.Loader;
import android.login.fragment.LoginFragment;
import android.main.fragment.MainFragment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

import static android.BaseConfig.getApkFile;
import static android.BaseConfig.getAppiumServerURL;
import static java.lang.System.getProperty;
import static java.time.Duration.ofSeconds;

@Slf4j
public class BaseTest {
    protected static AppiumDriver driver;
    private static Duration explicitWaitTimeout = ofSeconds(10);
    private static Duration implicitWaitTimeout = ofSeconds(1);

    protected Loader loader = new Loader(driver, explicitWaitTimeout);
    protected LoginFragment loginFragment = new LoginFragment(driver, explicitWaitTimeout);
    protected MainFragment mainFragment = new MainFragment(driver, explicitWaitTimeout);

    @BeforeAll
    @DisplayName("Setting up the Appium driver to run")
    public static void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getProperty("android.device.name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.APP, getApkFile().getAbsolutePath());

        driver = new AppiumDriver(getAppiumServerURL(), capabilities);
        driver.manage().timeouts().implicitlyWait(implicitWaitTimeout);
    }

    @AfterAll
    @DisplayName("Turning off the Appium driver")
    public static void tearDown() {
        driver.quit();
    }
}
