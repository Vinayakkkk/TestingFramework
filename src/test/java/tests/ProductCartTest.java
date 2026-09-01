package tests;
import pages.LoginPage;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;

public class ProductCartTest extends CheckoutFlowBase {

    @Test
    public void verifyIdenticalTwinsAmbiguityHealingTest() {
        System.out.println("=== Starting Identical Twins Ambiguity Healing Test ===");
LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page must be displayed");

        // 1. Add multiple items so multiple identical 'Remove' buttons appear simultaneously
        List<String> itemsToAdd = List.of(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt"
        );
        productsPage.addProducts(itemsToAdd);

        // 2. Target the middle item ("Sauce Labs Bike Light")
        // This triggers the broken locator: By.id("remove-sauce-labs-bike-light-old")
        // The engine must pick the middle 'Remove' button among all 3 active 'Remove' buttons
        System.out.println("Triggering heal on 'Sauce Labs Bike Light' remove button...");
        productsPage.addAndRemoveProduct("Sauce Labs Bike Light");

        // 3. Verify badge decreases appropriately
        Assert.assertTrue(productsPage.isCartCountDisplayed(2), "Cart count should be 2 after removing one item");

        System.out.println("=== Identical Twins Ambiguity Healing Test Passed ===");
    }
}