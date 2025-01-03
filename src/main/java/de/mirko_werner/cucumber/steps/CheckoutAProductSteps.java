package de.mirko_werner.cucumber.steps;

import de.mirko_werner.cucumber.api.AddToCartInterface;
import de.mirko_werner.cucumber.pages.CartPage;
import de.mirko_werner.cucumber.pages.CheckoutPage;
import de.mirko_werner.testdata.persistence.models.Customer;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Cookies;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutAProductSteps {

    Customer customer;
    AddToCartInterface addToCart;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    Cookies cookies;

    @Before
    public void setupPageObject() {
        addToCart = new AddToCartInterface();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @Given("I'm a guest customer")
    public void iAmGuestCustomer(Customer user) {
        customer = user;
    }

    @And("{int} product is in the cart")
    public void aProductIsInTheCart(int quantity) {
        cookies = addToCart.addRandomProductToCart(quantity);
    }

    @And("on the checkout page")
    public void onTheCheckoutPage() {
        cartPage.openUrl();
        cartPage.setCookies(cookies);
        cartPage.proceedToCheckout();
    }

    @When("I enter my billing details")
    public void iEnterMyBillingDetails() {
        checkoutPage.addBillingDetails(customer);
    }

    @And("place an order")
    public void placeAnOrder() {
        checkoutPage.placeOrder();
    }

    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() {
        assertThat(checkoutPage.checkThankYouOrderText(),is("Thank you. Your order has been received."));
    }
}
