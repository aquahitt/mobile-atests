package android;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.getProperty;

@Slf4j
public class BaseConfig {
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
            apkFile = new File(appDir.getCanonicalPath(), getProperty("android.apk.file.name", "app-debug.apk"));
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
            String path = getProperty("appium.server.protocol", "http") + "://" +
                    getProperty("appium.server.host", "127.0.0.1") + ":" +
                    getProperty("appium.server.port", "4723") +
                    getProperty("appium.server.path", "/wd/hub");
            url = new URL(path);
        } catch (MalformedURLException e) {
            log.error(e.getLocalizedMessage());
        }
        return url;
    }
}
