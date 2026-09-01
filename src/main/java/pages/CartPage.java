package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vinayak.healing.core.HealingWebDriver;

public class CartPage {

    private final HealingWebDriver driver;
    private final WebDriverWait wait;

    public CartPage(HealingWebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10));
    }

    // ==========================================
    // LOCATORS
    // ==========================================

    
          private final By cartTitle = By.cssSelector("[data-test='title']");

    /*
     * Each product inside the cart.
     *
     * IMPORTANT:
     * Do not use shopping-cart-link here.
     * shopping-cart-link represents the single
     * cart icon/link in the header.
     */
    private final By cartItems =
            By.cssSelector(
                    "[data-test='inventory']");

    private final By checkoutButton =
            By.id("checkout");

    private final By continueShoppingButton =
            By.cssSelector("[data-test='continue-shop']");

    // ==========================================
    // VERIFY CART PAGE
    // ==========================================

    public boolean isCartPageDisplayed() {

        boolean displayed =
                wait.until(
                        ExpectedConditions.textToBe(
                                cartTitle,
                                "Your Cart"));

        logAndWait(
                "Verified Cart page is displayed");

        return displayed;
    }

    // ==========================================
    // VERIFY PRODUCTS IN CART
    // ==========================================

    public boolean areProductsDisplayed(
            List<String> productNames) {

        /*
         * First verify that the number of actual
         * cart item containers matches the number
         * of products added.
         */
        wait.until(
                ExpectedConditions
                        .numberOfElementsToBe(
                                cartItems,
                                productNames.size()));

        logAndWait(
                "Verified cart item count: "
                        + productNames.size());

        /*
         * Verify each expected product name
         * individually.
         */
        for (String productName : productNames) {

            wait.until(
                    ExpectedConditions
                            .visibilityOfElementLocated(
                                    productNameLocator(
                                            productName)));

            logAndWait(
                    "Verified product in cart: "
                            + productName);
        }

        return true;
    }

    // ==========================================
    // VERIFY AT LEAST ONE ITEM
    // ==========================================

    public boolean isItemDisplayed() {

        boolean displayed =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        cartItems))
                        .isDisplayed();

        logAndWait(
                "Verified cart item is displayed");

        return displayed;
    }

    // ==========================================
    // CHECKOUT
    // ==========================================

    public void checkout() {

        wait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                checkoutButton))
                .click();

        logAndWait(
                "Clicked checkout button");

        wait.until(
                ExpectedConditions
                        .urlContains(
                                "checkout-step-one"));

        logAndWait(
                "Checkout information page URL loaded");
    }

    // ==========================================
    // CONTINUE SHOPPING
    // ==========================================

    public void continueShopping() {

        wait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                continueShoppingButton))
                .click();

        logAndWait(
                "Clicked continue shopping button");

        wait.until(
                ExpectedConditions
                        .urlContains(
                                "inventory"));

        logAndWait(
                "Inventory page URL loaded");
    }

    // ==========================================
    // DYNAMIC PRODUCT NAME LOCATOR
    // ==========================================

    private By productNameLocator(
            String productName) {

        return By.xpath(
                "//*[@data-test='inventory-item-name' "
                        + "and normalize-space()='"
                        + productName
                        + "']");
    }

    // ==========================================
    // LOG + WAIT
    // ==========================================

    private void logAndWait(
            String message) {

        System.out.println(
                message);

        pause();
    }

    private void pause() {

        try {

            Thread.sleep(1000);

            System.out.println(
                    "Waited for 1 second");

        } catch (InterruptedException e) {

            Thread.currentThread()
                    .interrupt();
        }
    }
}