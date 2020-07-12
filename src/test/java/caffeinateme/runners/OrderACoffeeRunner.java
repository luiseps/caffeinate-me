package caffeinateme.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/order_a_coffee.feature",
        glue="caffeinateme.stepdefinitions",
        snippets=SnippetType.CAMELCASE		)



public class OrderACoffeeRunner {
}
