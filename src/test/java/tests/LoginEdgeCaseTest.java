package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginEdgeCaseTest extends BaseTest {

    @Test
    public void invalidPasswordShowsLoginErrorTest() {

        LoginPage loginPage = new LoginPage(driver);

        String error = loginPage.loginExpectingError("standard_user", "wrong_password");

        Assert.assertTrue(
                error.contains("Username and password do not match"),
                "Invalid password should show mismatch error");
    }

    @Test
    public void lockedOutUserShowsLoginErrorTest() {

        LoginPage loginPage = new LoginPage(driver);

        String error = loginPage.loginExpectingError("locked_out_user", "secret_sauce");

        Assert.assertTrue(
                error.contains("Sorry, this user has been locked out"),
                "Locked out user should show locked account error");
    }
}
