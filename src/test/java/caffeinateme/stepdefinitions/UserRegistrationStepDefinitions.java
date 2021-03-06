package caffeinateme.stepdefinitions;

import caffeinateme.steps.Customer;
import caffeinateme.steps.UserRegistrationClient;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class UserRegistrationStepDefinitions {


    @Steps
    UserRegistrationClient userRegistration;

    @Steps(shared = true)
    Customer customer;

    @Given("^(.*) has a Caffeinate-Me account$")
    public void cathy_has_a_Caffeinate_Me_account(String userName) throws Exception {
        userRegistration.registerUser(customer);
        customer.isCalled(userName);
    }

}
