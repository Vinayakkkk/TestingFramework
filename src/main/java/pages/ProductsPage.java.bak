package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vinayak.healing.core.HealingWebDriver;
import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private final HealingWebDriver driver;
    private final WebDriverWait wait;

    public ProductsPage(HealingWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By title = By.className("title");
    private final By cartIcon =
            By.className("shopping_cart_link");
   private final By cartBadge =
        By.className("shopping_cart_badge");

    public boolean isProductsPageDisplayed() {

    boolean displayed =
            wait.until(
                    ExpectedConditions.textToBe(
                            title,
                            "Products"));

    logAndWait(
            "Verified Products page is displayed");

    return displayed;
}

    public void addProducts(List<String> productNames) {
    for (int i = 0; i < productNames.size(); i++) {
        String productName = productNames.get(i);
        
        // 1. Click the add to cart button
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton(productName))).click();
        logAndWait("Added product to cart: " + productName);
        
        // 2. Expect the badge to appear/update with the correct count string
        String expectedCount = String.valueOf(i + 1);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartBadge, expectedCount));
        
        logAndWait("Verified cart badge after adding product: " + expectedCount);
    }
}

    public void addAndRemoveProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton(productName))).click();
        logAndWait("Added product to cart for remove edge case: " + productName);

        wait.until(ExpectedConditions.textToBe(cartBadge, "1"));
        logAndWait("Verified cart badge before removing product");

        wait.until(ExpectedConditions.elementToBeClickable(removeButton(productName))).click();
        logAndWait("Removed product from cart: " + productName);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartBadge));
        logAndWait("Verified cart badge is hidden after removing product");
    }

    public boolean isCartCountDisplayed(int expectedCount) {
        boolean displayed = wait.until(ExpectedConditions.textToBe(cartBadge, String.valueOf(expectedCount)));
        logAndWait("Verified cart badge count: " + expectedCount);
        return displayed;
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        logAndWait("Clicked cart icon");
        wait.until(ExpectedConditions.urlContains("cart"));
        logAndWait("Cart page URL loaded");
    }

    private By addToCartButton(String productName) {
        String productId = productName.toLowerCase()
                .replace(" ", "-")
                .replace(".", "");
        return By.id("add-to-cart-" + productId + "-old");
    }

    private By removeButton(String productName) {
        String productId = productName.toLowerCase()
                .replace(" ", "-")
                .replace(".", "");
        return By.id("remove-" + productId + "-old");
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
