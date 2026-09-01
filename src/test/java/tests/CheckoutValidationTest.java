package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;

public class CheckoutValidationTest extends CheckoutFlowBase {

    @Test
    public void missingLastNameShowsCheckoutValidationErrorTest() {

        CheckoutPage checkout = openCheckoutInformationPage();

        String error = checkout.submitCheckoutDetailsExpectingError("Vinayak", "", "560001");

        Assert.assertTrue(
                error.contains("Last Name is required"),
                "Missing last name should show validation error");
    }
}
