package de.mirko_werner.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;

@EndPoint("/store")
public class StorePage extends PageObject {

    @FindBy(css = "a[title='View cart']")
    private WebElement viewCart;

    @FindBy(css = "a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart")
    private List<WebElement> addToCardLinks;

    public void addToCart(String productName) {
        WebElement addToCartLink = addToCardLinks.stream()
                .filter(webElement -> Objects.requireNonNull(webElement.getDomAttribute("aria-label")).contains(productName))
                .findAny().orElse(null);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartLink)).click();
        wait.until(ExpectedConditions.visibilityOf(viewCart)).click();
    }
}
