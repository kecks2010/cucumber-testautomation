package de.mirko_werner.cucumber.steps;

import de.mirko_werner.cucumber.pages.CartPage;
import de.mirko_werner.cucumber.pages.HomePage;
import de.mirko_werner.cucumber.pages.StorePage;
import de.mirko_werner.cucumber.pages.components.ProductComponent;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddToCardSteps {

    private HomePage homePage;
    private StorePage storePage;
    private CartPage cartPage;
    private ProductComponent productComponent;

    @Before
    public void setupPageObjects() {
        homePage = new HomePage();
        storePage = new StorePage();
        cartPage = new CartPage();
        productComponent = new ProductComponent();
    }

    @Given("I'm on the store page")
    public void iMOnTheStorePage() {
        storePage.openUrl();
    }

    @Given("I'm on the main page")
    public void iMOnTheMainPage() {
        homePage.openUrl();
    }

    @When("I add {string} to the Cart")
    public void iAddToTheCart(String productName) {
        productComponent.addToCart(productName);
    }

    @Then("I see {int} {string} with {string} in the Cart")
    public void iSeeOneInTheCart(int quantity, String productName, String price) {
        assertThat(cartPage.getProductName(), is(productName));
        assertThat(cartPage.getProductPrice(), is(price));
        assertThat(Integer.parseInt(cartPage.getProductQuantity()), is(quantity));
    }

    @And("The total price is {string}")
    public void theTotalPriceIs(String totalprice) {
        assertThat(cartPage.getTotalPrice(), is(totalprice));
    }

    @And("a product is in the cart")
    public void aProductIsInTheCart() {

    }
}