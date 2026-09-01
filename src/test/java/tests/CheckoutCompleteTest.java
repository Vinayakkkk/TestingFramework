package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;

public class CheckoutCompleteTest extends CheckoutFlowBase {

    @Test
    public void completeCheckoutAndVerifyThankYouMessageTest() {

        System.out.println("Starting complete checkout and verify thank you message test");
        System.out.println("Products selected for test: " + PRODUCT_NAMES);

        CheckoutPage checkout = openCheckoutInformationPage();

        checkout.enterCheckoutDetails("Vinayak", "Tester", "560001");
        Assert.assertTrue(
                checkout.isOverviewPageDisplayed(),
                "Checkout overview page should be displayed");
        Assert.assertTrue(
                checkout.areProductsDisplayedInOverview(PRODUCT_NAMES),
                "Selected products should be displayed in checkout overview");

        checkout.finishOrder();

        Assert.assertEquals(
                checkout.getOrderCompleteMessage(),
                "Thank you for your order!",
                "Order completion message should be displayed");

        System.out.println("Completed checkout and verified thank you message successfully");
    }
}
