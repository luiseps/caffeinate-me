package caffeinateme.stepdefinitions;

import caffeinateme.entities.*;
import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;

import java.util.List;

import static org.junit.Assert.assertThat;

public class OrderACoffeeStepDefinitions {

    @Steps(shared = true)
    Customer customer;

    @Steps(shared = true)
    Barista barry;

    OrderReceipt orderReceipt;

    @Steps(shared = true)
    private ProductCatalog productCatalog;

    @When("^(?:.*) (?:orders?|has ordered) an? (.*)$")
    public void she_orders_a_large_cappuccino(String order) throws Exception {
        orderReceipt = customer.placesAnOrderFor(1, order);

        Serenity.setSessionVariable("orderReceipt").to(orderReceipt);
    }

    @Then("^Barry should receive the order$")
    public void barry_should_receive_the_cofee_order() throws Exception {
        assertThat(barry.pendingOrders(),  Matchers.hasItem(Order.matching(orderReceipt)));
    }

    @Given("^Sarah has ordered:$")
    public void sarahHasOrdered(List<OrderItem> orders) {
        orders.forEach(
                order ->{
                    customer.placesAnOrderFor(order.getQuantity(), order.getProduct());
                }
        );
    }

    Receipt receipt;

    @When("^she ask for a receipt$")
    public void sheAskForAReceipt() throws Throwable{
       receipt = customer.requestAReceipt();
    }

    @Then("^she should receive a receipt totalling$")
    public void sheShouldReceiveAReceiptTotalling(List<Receipt> expectedReceipts) {
        Receipt expectedReceipt = expectedReceipts.get(0);
        Assertions.assertThat(receipt).isEqualToIgnoringNullFields(expectedReceipt);
    }

    @And("^the following prices:$")
    public void theFollowingPrices(List<ProductPrice> productPrices) {
        productCatalog.addProductsWithPrices(productPrices);
    }
}