package caffeinateme.stepdefinitions;

import caffeinateme.Barista;
import caffeinateme.Customer;
import caffeinateme.Order;
import caffeinateme.OrderReceipt;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;

import static org.junit.Assert.assertThat;

public class OrderACoffeeStepDefinitions {

    @Steps(shared = true)
    Customer customer;

    @Steps(shared = true)
    Barista barry;

    OrderReceipt orderReceipt;

    @When("^(?:.*) (?:orders?|has ordered) an? (.*)$")
    public void she_orders_a_large_cappuccino(String order) throws Exception {
        orderReceipt = customer.placesAnOrderFor(1, order);

        Serenity.setSessionVariable("orderReceipt").to(orderReceipt);
    }

    @Then("^Barry should receive the order$")
    public void barry_should_receive_the_cofee_order() throws Exception {
        assertThat(barry.pendingOrders(),  Matchers.hasItem(Order.matching(orderReceipt)));
    }
}