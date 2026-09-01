package tests;

import org.testng.Assert;
import java.util.Arrays;
import java.util.List;
import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CheckoutFlowBase extends BaseTest {

    protected static final List<String> PRODUCT_NAMES = Arrays.asList(
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt");

    protected CartPage addProductsAndOpenCart() {

        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");
        System.out.println("Login action completed");

        ProductsPage products = new ProductsPage(driver);
        Assert.assertTrue(products.isProductsPageDisplayed(), "Products page should be displayed");

        products.addProducts(PRODUCT_NAMES);
        Assert.assertTrue(
                products.isCartCountDisplayed(PRODUCT_NAMES.size()),
                "Cart badge should show selected product count");

        products.openCart();
        return new CartPage(driver);
    }

    protected CheckoutPage openCheckoutInformationPage() {

        CartPage cart = addProductsAndOpenCart();

        Assert.assertTrue(cart.isCartPageDisplayed(), "Cart page should be displayed");
        Assert.assertTrue(
                cart.areProductsDisplayed(PRODUCT_NAMES),
                "Selected products should be displayed in the cart");

        cart.checkout();

        CheckoutPage checkout = new CheckoutPage(driver);
        Assert.assertTrue(
                checkout.isInformationPageDisplayed(),
                "Checkout information page should be displayed");

        return checkout;
    }
}
