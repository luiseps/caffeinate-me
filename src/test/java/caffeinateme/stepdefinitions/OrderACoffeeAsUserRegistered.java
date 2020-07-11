package caffeinateme.stepdefinitions;

import caffeinateme.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class OrderACoffeeAsUserRegistered {

    @Steps
    UserRegistrationClient userRegistration;

    @Steps
    Customer cathy;

    @Steps
    Barista barry;

    @Given("^Cathy has a Caffeinate-Me account$")
    public void cathy_has_a_Caffeinate_Me_account() throws Exception {
        userRegistration.registerUser(cathy);
    }

    OrderReceipt orderReceipt;

    @When("^s?he orders a (.*)$")
    public void she_orders_a_large_cappuccino(String order) throws Exception {
        orderReceipt = cathy.placesAnOrderFor(1, order);
    }

    @Then("^Barry should receive the cofee order$")
    public void barry_should_receive_the_cofee_order() throws Exception {
        assertThat(barry.pendingOrders(),  hasItem(Order.matching(orderReceipt)));
    }
}
