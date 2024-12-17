package de.mirko_werner.cucumber.api;

import de.mirko_werner.cucumber.models.EndPoint;
import de.mirko_werner.cucumber.models.PageObject;
import de.mirko_werner.testdata.persistence.models.Product;
import de.mirko_werner.testdata.repositories.ProductRepository;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

@EndPoint("/?wc-ajax=add_to_cart")
public class AddToCartInterface extends PageObject {

    public Cookies addRandomProductToCart(int quantity) {

        Product product = ProductRepository.getInstance().getRandomProduct();

        Response response = RestAssured
                .given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("product_sku", "")
                    .formParam("product_id", product.id())
                    .formParam("quantity", quantity)
                .when()
                    .post(this.getUrl())
                .then()
                    .extract().response();
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to add product " + product.id() + " to the cart, " +
                    "HTTP status code: " + response.getStatusCode());
        }

        return response.detailedCookies();
    }
}
