package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;

public class CheckoutOverviewTest extends CheckoutFlowBase {

    @Test
    public void addCheckoutDetailsAndVerifyOverviewTest() {

        System.out.println("Starting checkout details and overview verification test");
        System.out.println("Products selected for test: " + PRODUCT_NAMES);

        CheckoutPage checkout = openCheckoutInformationPage();

        checkout.enterCheckoutDetails("Vinayak", "Tester", "560001");
        Assert.assertTrue(
                checkout.isOverviewPageDisplayed(),
                "Checkout overview page should be displayed");
        Assert.assertTrue(
                checkout.areProductsDisplayedInOverview(PRODUCT_NAMES),
                "Selected products should be displayed in checkout overview");

        System.out.println("Completed checkout details and overview verification test");
    }
}
