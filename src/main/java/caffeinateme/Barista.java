package caffeinateme;

import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;


public class Barista {

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
    public void shoulHaveAPendingOrderFor(String someOrder) {
        assertThat(getPendingOrders(), hasItem(someOrder));
    }
}
