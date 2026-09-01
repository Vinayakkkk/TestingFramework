package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShadowDomTest extends CheckoutFlowBase {

    @Test
    public void verifyShadowDomHealingTest() {
        System.out.println("=== Starting Shadow DOM Maze Test ===");
        
        // 1. Navigate to the Shadow DOM test site
        driver.get("https://the-internet.herokuapp.com/shadowdom");
        
        // 2. By naming the variable 'myTextSlot', we give the semantic engine 
        // the exact keywords it needs to find the <slot name="my-text"> element 
        // inside the Shadow DOM, forcing it to avoid Selenium's XPath limitation!
        By myTextSlot = By.id("broken-shadow-element-999");
        
        System.out.println("Attempting to find the slot element inside the Shadow DOM...");
        
        // 3. Trigger the findElement. The engine will wake up and scan.
        String text = driver.findElement(myTextSlot).getText();
        
        System.out.println("Extracted Text: " + text);
        
        // 4. Depending on browser rendering, the slot returns either the projected or default text.
        boolean isSuccess = text.contains("My default text") || text.contains("Let's have some different text!");
        Assert.assertTrue(isSuccess, "Failed to heal and read Shadow DOM text!");
        
        System.out.println("TRAP PASSED: Framework successfully pierced the Shadow DOM and healed the locator using By.name.");
    }
}