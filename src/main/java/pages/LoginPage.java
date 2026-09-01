package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.vinayak.healing.core.HealingWebDriver;
import java.time.Duration;

public class LoginPage {

    private final HealingWebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(HealingWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🚨 INTENTIONALLY BROKEN LOCATORS TO TRIGGER THE ORCHESTRATOR 🚨
    
    private final By usern = By.cssSelector("[data-test='username']");
        
    private final By passs = By.id("password");
        
    // [AUTO-HEALED] Original: By.id("logbutt")
        private final By button = By.id("logbutt");
    
    private final By errorMessage = By.cssSelector("[data-test='error-message-dynamic']");

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usern)).sendKeys(user);
        logAndWait("Entered username");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(passs)).sendKeys(pass);
        logAndWait("Entered password");
        
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
        logAndWait("Clicked login button");
    }

    public String loginExpectingError(String user, String pass) {
        login(user, pass);
        
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        logAndWait("Verified login error message: " + message);
        return message;
    }

    private void logAndWait(String message) {
        System.out.println(message);
        pause();
    }

    private void pause() {
        try {
            Thread.sleep(1000);
            System.out.println("Waited for 1 second");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}