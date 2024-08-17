package framework.driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import framework.utils.PropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class DriverUtils {
    private static final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private static Map<Long, WebDriver> drivers = new HashMap<>();
    private static Browsers defaultBrowser;

    static {
        initDriver();
    }

    private DriverUtils() {
        initDriver();
    }

    private static void initDriver() {
        String browserName = PropertiesUtils.get().readBrowserName();
        if (browserName.equals(PropertiesUtils.ERROR_READ_PROPERTY)) {
            defaultBrowser = Browsers.DEFAULT_TEMPORARY;
        } else {
            defaultBrowser = getBrowserByPartialName(browserName);
        }
    }

    private static Browsers getBrowserByPartialName(String browserName) {
        Browsers browser = Browsers.DEFAULT_TEMPORARY;
        browserName = browserName.toLowerCase()
                .replaceAll("[_-]", " ")
                .replaceAll("[ ]+", " ")
                .trim();
        for (Browsers current : Browsers.values()) {
            String currentName = current.name().toLowerCase().replace("_", " ");
            if (currentName.contains(browserName)) {
                browser = current;
                break;
            }
        }
        return browser;
    }

    public static WebDriver addDriverByPartialName(String browserName) {
        return addDriver(getBrowserByPartialName(browserName));
    }

    public static WebDriver addDriver(Browsers browser) {
        drivers.put(Thread.currentThread().getId(), browser.runBrowser());
        return drivers.get(Thread.currentThread().getId());
    }
    
    public static WebDriver getDriver() {
        WebDriver driver = drivers.get(Thread.currentThread().getId());
        //
        if (driver == null) {
            driver = addDriver(defaultBrowser);
        }
        return driver;
    }

    public static void refreshPage() {
        getDriver().navigate().refresh();
    }

    public static void navigateToUrl(String url) {
        getDriver().navigate().to(url);
    }

    public static void getUrl(String url) {
        getDriver().get(url);
    }

    public static void takeScreenShot() {
        //String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);
        //
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

    public static void takePageSource() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = getDriver().getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + "_source.html.txt");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }


    public static void quit() {
        for (Map.Entry<Long, WebDriver> driverEntry : drivers.entrySet()) {
            if (driverEntry.getValue() != null) {
                driverEntry.getValue().quit();
            }
        }
    }
}
