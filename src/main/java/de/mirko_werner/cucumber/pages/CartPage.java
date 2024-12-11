package de.mirko_werner.cucumber.pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@EndPoint("/cart")
public class CartPage extends PageObject {

    @FindBy(css = "td[class='product-name'] a")
    private WebElement productNameField;

    @FindBy(css = "input[type='number']")
    private WebElement productQuantityField;

    @FindBy(css = "span[class='woocommerce-Price-amount amount']")
    private WebElement productPriceField;

    @FindBy(xpath = "//th[contains(text(), 'Total')]/following::td//bdi")
    private WebElement totalPriceField;

    @FindBy(css = "a[class='checkout-button button alt wc-forward']")
    private WebElement checkoutButton;

    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOf(productNameField));
        return productNameField.getText();
    }

    public String getProductQuantity() {
        wait.until(ExpectedConditions.visibilityOf(productQuantityField));
        return productQuantityField.getDomAttribute("value");
    }

    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(productPriceField));
        return productPriceField.getText();
    }

    public String getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPriceField));
        return totalPriceField.getText();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.visibilityOf(checkoutButton));
        checkoutButton.click();
    }

    public void setCookies(io.restassured.http.Cookies restAssuredCookies) {
        restAssuredCookies.asList().forEach(restAssurdCookie -> getWebDriver().manage()
                .addCookie(new Cookie(restAssurdCookie.getName(), restAssurdCookie.getValue(), restAssurdCookie.getDomain(),
                restAssurdCookie.getPath(), restAssurdCookie.getExpiryDate(), restAssurdCookie.isSecured(),
                restAssurdCookie.isHttpOnly(), restAssurdCookie.getSameSite())));
        getWebDriver().navigate().refresh();
    }
}
