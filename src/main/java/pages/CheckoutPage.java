package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vinayak.healing.core.HealingWebDriver;
import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private final HealingWebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(HealingWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private final By title =
            By.cssSelector("[data-test='title']");
    private final By firstName =
            By.id("first-name");
    private final By lastName =
            By.name("lastName");
    private final By postalCode =
            By.id("postal-code");
    private final By continueButton =
            By.cssSelector("[data-test='continue']");
    private final By checkoutItems = By.className("cart");
    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");
    private final By errorMessage =
            By.cssSelector("[data-test='error']");

    public boolean isInformationPageDisplayed() {
        boolean displayed = wait.until(ExpectedConditions.textToBe(title, "Checkout: Your Information"));
        logAndWait("Verified Checkout Information page is displayed");
        return displayed;
    }

    public void enterCheckoutDetails(String first, String last, String zipCode) {
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        WebElement postalCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode));

        firstNameInput.clear();
        firstNameInput.sendKeys(first);
        logAndWait("Entered first name: " + first);
        lastNameInput.clear();
        lastNameInput.sendKeys(last);
        logAndWait("Entered last name: " + last);
        postalCodeInput.clear();
        postalCodeInput.sendKeys(zipCode);
        logAndWait("Entered postal code: " + zipCode);

        wait.until(ExpectedConditions.attributeToBe(firstName, "value", first));
        logAndWait("Verified first name value");
        wait.until(ExpectedConditions.attributeToBe(lastName, "value", last));
        logAndWait("Verified last name value");
        wait.until(ExpectedConditions.attributeToBe(postalCode, "value", zipCode));
        logAndWait("Verified postal code value");

        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        logAndWait("Clicked continue button");
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        logAndWait("Checkout overview page URL loaded");
    }

    public String submitCheckoutDetailsExpectingError(String first, String last, String zipCode) {
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        WebElement postalCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode));

        firstNameInput.clear();
        firstNameInput.sendKeys(first);
        logAndWait("Entered first name for validation edge case: " + first);

        lastNameInput.clear();
        lastNameInput.sendKeys(last);
        logAndWait("Entered last name for validation edge case: " + last);

        postalCodeInput.clear();
        postalCodeInput.sendKeys(zipCode);
        logAndWait("Entered postal code for validation edge case: " + zipCode);

        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        logAndWait("Clicked continue button expecting validation error");

        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        logAndWait("Verified checkout validation message: " + message);
        return message;
    }

    public boolean isOverviewPageDisplayed() {
        boolean displayed = wait.until(ExpectedConditions.textToBe(title, "Checkout: Overview"));
        logAndWait("Verified Checkout Overview page is displayed");
        return displayed;
    }

    public boolean areProductsDisplayedInOverview(List<String> productNames) {
        wait.until(ExpectedConditions.numberOfElementsToBe(checkoutItems, productNames.size()));
        logAndWait("Verified checkout overview item count: " + productNames.size());

        for (String productName : productNames) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator(productName)));
            logAndWait("Verified product in checkout overview: " + productName);
        }

        return true;
    }

    public void finishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
        logAndWait("Clicked finish button");
        wait.until(ExpectedConditions.urlContains("checkout-complete"));
        logAndWait("Checkout complete page URL loaded");
    }

    public String getOrderCompleteMessage() {
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
        logAndWait("Verified order complete message: " + message);
        return message;
    }

    private By productNameLocator(String productName) {
    return By.xpath(
            "//div[@data-test='inventory-item-name' and normalize-space()="
                    + xpathLiteral(productName)
                    + "]"
    );
}

private String xpathLiteral(String value) {

    if (!value.contains("'")) {
        return "'" + value + "'";
    }

    if (!value.contains("\"")) {
        return "\"" + value + "\"";
    }

    String[] parts = value.split("'");

    StringBuilder result =
            new StringBuilder("concat(");

    for (int i = 0; i < parts.length; i++) {

        if (i > 0) {
            result.append(", \"'\", ");
        }

        result.append("'")
                .append(parts[i])
                .append("'");
    }

    result.append(")");

    return result.toString();
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
