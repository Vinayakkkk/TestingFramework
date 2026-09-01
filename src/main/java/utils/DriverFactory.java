package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {

            ChromeOptions options = new ChromeOptions();

            /*
             * Local:
             *   mvn test
             *   -> normal Chrome
             *
             * CI:
             *   mvn -Dheadless=true test
             *   -> headless Chrome
             */
            boolean headless =
                    Boolean.parseBoolean(
                            System.getProperty("headless", "false"));

            if (headless) {
                options.addArguments("--headless=new");
            }

            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");

            driver = new ChromeDriver(options);
        }

        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}