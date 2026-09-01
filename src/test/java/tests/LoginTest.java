package tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import java.time.Duration;
import org.testng.Assert;
public class LoginTest extends BaseTest {

    @Test
public void verifySuccessfulLogin() {

    LoginPage loginPage =
            new LoginPage(driver);

    loginPage.login(
            "standard_user",
            "secret_sauce");

    String expectedUrl =
            "https://www.saucedemo.com/inventory.html";

    WebDriverWait wait =
            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10));

    wait.until(
            d -> d.getCurrentUrl()
                    .contains("inventory"));

    Assert.assertEquals(
            driver.getCurrentUrl(),
            expectedUrl);
    System.out.println(
            "Current URL: " + driver.getCurrentUrl());
    System.out.println(
            "Login successful");
}
}

