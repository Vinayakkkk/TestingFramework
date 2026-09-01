package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

import com.vinayak.healing.config.HealingConfig;
import com.vinayak.healing.core.HealingWebDriver;
import com.vinayak.healing.factory.SelfHealingDriverFactory;
import com.vinayak.healing.execution.ContextManager;
import com.vinayak.healing.execution.ExecutionContext;

import utils.DriverFactory;

public class BaseTest {

    protected HealingWebDriver driver;

    @BeforeMethod
    public void setup(Method method) {
        // 1. Read the AI toggle (default is true if not passed via command line)
        boolean isAiEnabled = Boolean.parseBoolean(System.getProperty("ai.enabled", "true"));

        // 2. Initialize the isolated context for this specific test thread
        ExecutionContext ctx = new ExecutionContext(method.getName(), isAiEnabled);
        ContextManager.setContext(ctx);

        // 3. Standard WebDriver setup
        WebDriver chrome = DriverFactory.getDriver();
        HealingConfig config = new HealingConfig();

        driver = SelfHealingDriverFactory.create(chrome, config);
        driver.get("https://www.saucedemo.com/");

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();

        // 4. Crucial: Clear the context so the thread is clean for the next test
        ContextManager.clear();
    }
}