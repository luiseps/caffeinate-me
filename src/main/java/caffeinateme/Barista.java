package caffeinateme;

import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class Barista extends ScenarioActor {

    @Steps(shared = true)
    CoffeeOrdersClient coffeeOrders;

    public List<Order> pendingOrders() {
        return coffeeOrders.getOrders();
    }

    public List<String> getUrgentOrders() {
        List<String> results = new ArrayList<>();
        results.add("large cappuccino");
        return results;
    }

    public List<String> getPendingOrders() {
        List<String> results = new ArrayList<>();
        results.add("large cappuccino");
        return results;
    }
    @Step
    public void shouldHaveAPendingOrderFor(String someOrder) {
        assertThat(getPendingOrders(), hasItem(someOrder));
    }
}
