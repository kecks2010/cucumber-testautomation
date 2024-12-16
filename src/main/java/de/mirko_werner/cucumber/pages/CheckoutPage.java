package de.mirko_werner.cucumber.pages;

import de.mirko_werner.testdata.persistence.models.Address;
import de.mirko_werner.testdata.persistence.models.Customer;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends PageObject {

    @FindBy(id = "billing_first_name")
    private WebElement firstName;

    @FindBy(id = "billing_last_name")
    private WebElement lastName;

    @FindBy(id = "select2-billing_country-container")
    private WebElement countryDropdownIcon;

    @FindBy(css = "input[class='select2-search__field']")
    private WebElement searchFieldCountry;

    @FindBy(id = "billing_address_1")
    private WebElement streetAndNumber;

    @FindBy(id = "billing_city")
    private WebElement city;

    @FindBy(id = "select2-billing_state-container")
    private WebElement stateDropdownIcon;

    @FindBy(css = "input[class='select2-search__field']")
    private WebElement searchFieldState;

    @FindBy(id = "billing_postcode")
    private WebElement postcode;

    @FindBy(id = "billing_email")
    private WebElement email;

    @FindBy(id = "place_order")
    private WebElement placeOrderBtn;

    @FindBy(css = ".blockUI.blockOverlay")
    private List<WebElement> blockOverlay;

    @FindBy(css = "p[class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")
    private WebElement thankYouOrderText;

    public void addBillingDetails(Customer customer) {
        wait.until(ExpectedConditions.visibilityOf(email));
        Address mainAddress = customer.getAddressList().stream()
                .filter(address -> address.addressType().contentEquals("primary")).findFirst()
                        .orElse(null);
        assert mainAddress != null;
        firstName.sendKeys(customer.getFirstName());
        lastName.sendKeys(customer.getLastName());
        postcode.sendKeys(mainAddress.postalCode());
        streetAndNumber.sendKeys(mainAddress.street() + " " + mainAddress.number());
        email.sendKeys(customer.getEmailAddress());
        city.sendKeys(mainAddress.city());
        countryDropdownIcon.click();
        searchFieldCountry.sendKeys(mainAddress.country().getName());
        searchFieldCountry.sendKeys(Keys.RETURN);
        stateDropdownIcon.click();
        searchFieldState.sendKeys(mainAddress.state().getName());
        searchFieldState.sendKeys(Keys.RETURN);
    }
    public void placeOrder() {
        wait.until(ExpectedConditions.invisibilityOfAllElements(blockOverlay));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }

    public String checkThankYouOrderText() {
        wait.until(ExpectedConditions.invisibilityOfAllElements(blockOverlay));

        return wait.until(ExpectedConditions.visibilityOf(thankYouOrderText)).getText();
    }
}
