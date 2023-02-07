package android;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.getProperty;

@Slf4j
public class BaseConfig {
    protected static Configuration configProperties;

    /**
     * Loading configuration properties from the 'config.properties' file.
     */
    protected static void loadConfigProperties() {
        Configurations configs = new Configurations();
        try {
            configProperties = configs.properties(new File("config.properties"));
        } catch (ConfigurationException cex) {
            log.error("Can't load 'config.properties'");
        }
    }

    /**
     * Get the APK file placed in the 'apps' directory.
     *
     * @return {@link File}
     */
    protected static File getApkFile() {
        final File classpathRoot = new File(getProperty("user.dir"));
        final File appDir = new File(classpathRoot, "apps");
        File apkFile = null;
        try {
            apkFile = new File(appDir.getCanonicalPath(), configProperties.getString("android.apk.file.name", "app-debug.apk"));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
        return apkFile;
    }

    /**
     * Get the URL of the Appium Server.
     *
     * @return {@link URL}
     */
    protected static URL getAppiumServerURL() {
        URL url = null;
        try {
            String path = configProperties.getString("appium.server.protocol", "http") + "://" +
                    configProperties.getString("appium.server.host", "127.0.0.1") + ":" +
                    configProperties.getString("appium.server.port", "4723") +
                    configProperties.getString("appium.server.path", "/wd/hub");
            url = new URL(path);
        } catch (MalformedURLException e) {
            log.error(e.getLocalizedMessage());
        }
        return url;
    }
}
