package caffeinateme.stepdefinitions;

import caffeinateme.Barista;
import caffeinateme.Customer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import static  org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class OrderCoffeeSteps {

    @Steps
    Customer cathy;

    @Steps(shared = true)
    Barista barry;

    String cathysOrder;

    @Given("(.*) is (\\d+) meters? from the coffe shop")
    public void cathy_is_meters_from_the_coffe_shop(String name, int distanceInMeters) {
        cathy.notifyDistanceFromShop(distanceInMeters);
    }

    @When("Cathy (?:order|has ordered) a (.*)")
    public void cathy_order_a_large_cappuccino(String order) {
        cathysOrder = order;
        cathy.placesOrderFor(cathysOrder);
    }

    @Then("Barry should receive the order")
    public void barry_should_receive_the_order() {
       barry.shouldHaveAPendingOrderFor(cathysOrder);
    }

    @Then("Barry should know that the coffee order is Urgent")
    public void barry_should_know_that_the_coffee_order_is_Urgent() {
       assertThat(barry.getUrgentOrders(), hasItem(cathysOrder));
    }

}
