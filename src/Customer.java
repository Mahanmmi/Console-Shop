import java.util.HashMap;

public class Customer {
    private String name;
    private HashMap<Integer,Boolean> discounts; //false = not used yet true = used
    public Customer(String name) {
        this.name = name;
    }

    public HashMap<Integer, Boolean> getDiscounts() {
        return discounts;
    }
}
