package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MenuPage;
import pages.ProductsPage;

public class GhostElementTest extends CheckoutFlowBase {

    @Test
    public void verifyGhostElementVisibilityTrapTest() {
        System.out.println("=== Starting Ghost Element (Visibility Trap) Test ===");

        // 1. Log in to reach the main application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page must be displayed");

        // 2. DO NOT click the hamburger menu. 
        // The 'About' link is in the HTML DOM, but physically hidden (display: none).
        MenuPage menuPage = new MenuPage(driver);
        
        // 3. Attempt to heal and click the broken locator
        try {
            menuPage.clickAboutLink();
            
            // If the code reaches this line, the engine hallucinated a click on a hidden element!
            Assert.fail("TRAP FAILED: The framework bypassed the visibility gate and clicked a hidden element.");
            
        } catch (Exception e) {
            System.out.println("TRAP PASSED: Framework correctly refused to interact with the hidden element.");
            System.out.println("Exception caught: " + e.getClass().getSimpleName());
        }

        System.out.println("=== Ghost Element Visibility Trap Test Completed ===");
    }
}