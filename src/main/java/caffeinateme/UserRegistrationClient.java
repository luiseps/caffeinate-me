package caffeinateme;

public class UserRegistrationClient {

    long customerIdCounter = 1;
    public void registerUser(Customer newCustomer) {
        newCustomer.hasAcustomerIdOf(customerIdCounter++);
    }
}
