package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.ProductsPage;

public class CartEdgeCaseTest extends CheckoutFlowBase {

    @Test
    public void continueShoppingReturnsToProductsPageTest() {

        CartPage cart = addProductsAndOpenCart();

        Assert.assertTrue(cart.isCartPageDisplayed(), "Cart page should be displayed");

        cart.continueShopping();

        ProductsPage products = new ProductsPage(driver);
        Assert.assertTrue(products.isProductsPageDisplayed(), "Products page should be displayed after continuing shopping");
    }
}
