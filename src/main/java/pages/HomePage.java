package pages;

import org.openqa.selenium.By;
import com.vinayak.healing.core.HealingWebDriver;

public class HomePage {

    private HealingWebDriver driver;

    private By productsTitle =
            By.xpath("//span[@class='tit' and text()='Products']");

    public HomePage(HealingWebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageDisplayed() {
        return driver.findElement(productsTitle).isDisplayed();
    }
}
