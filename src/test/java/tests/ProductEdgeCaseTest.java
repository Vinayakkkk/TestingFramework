package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductsPage;

public class ProductEdgeCaseTest extends CheckoutFlowBase {

    @Test
    public void addAndRemoveSingleProductHidesCartBadgeTest() {

        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(driver);
        Assert.assertTrue(products.isProductsPageDisplayed(), "Products page should be displayed");

        products.addAndRemoveProduct("Sauce Labs Backpack");
    }
}
