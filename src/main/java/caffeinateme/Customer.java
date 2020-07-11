package caffeinateme;


import net.thucydides.core.annotations.Step;

public class Customer {

    private int distanceFromShop;
    String actor;

    @Step("Notify caffeinate me that the customer is {0} from the shop")
    public void notifyDistanceFromShop(int distanceFromShop) {
        this.distanceFromShop = distanceFromShop;
    }

    @Step("#actor places order for {0}")
    public void placesOrderFor(String order){

    }
}
