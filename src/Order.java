import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int count = 0;
    private int id;
    private Customer customer;
    private String status;
    private HashMap<Good, Integer> items;
    private ArrayList<Discount> discounts;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        status = "pending";
        items = new HashMap<>();
        discounts = new ArrayList<>();
        count++;
    }

    public void addItem(Good good, int amount) {
        if (items.containsKey(good)) {
            items.put(good, items.get(good) + amount);
        } else {
            items.put(good, amount);
        }
    }

    public void addDiscount(Discount discount) {
        discount.setOrder(this);
        discounts.add(discount);
    }

    public int calculatePrice() {
        int price = 0;
        for (Map.Entry<Good, Integer> entry : items.entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        for (Discount discount : discounts) {
            price *= ((100.0 - discount.getPercent()) / 100.0);
        }
        return price;
    }

    public void removeItem(Good good) {
        items.remove(good);
    }

    public HashMap<Good, Integer> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }
}
