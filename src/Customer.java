import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    private static int count = 0;
    private String name;
    private int id;
    private int balance;
    ArrayList<Order> orders;
    ArrayList<Order> pendingOrders;
    ArrayList<Order> submittedOrders;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
        orders = new ArrayList<>();
        pendingOrders = new ArrayList<>();
        submittedOrders = new ArrayList<>();
        count++;
    }

    public void addOrder(Order order) {
        orders.add(order);
        pendingOrders.add(order);
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Order[] getOrders() {
        Order[] orders = new Order[this.orders.size()];
        for (int i = 0; i < this.orders.size(); i++) {
            orders[i] = this.orders.get(i);
        }
        return orders;
    }

    public Order[] getPendingOrders() {
        Order[] pendingOrders = new Order[this.pendingOrders.size()];
        for (int i = 0; i < this.pendingOrders.size(); i++) {
            pendingOrders[i] = this.pendingOrders.get(i);
        }
        return pendingOrders;
    }

    public Order[] getSubmittedOrders() {
        Order[] submittedOrders = new Order[this.submittedOrders.size()];
        for (int i = 0; i < this.submittedOrders.size(); i++) {
            submittedOrders[i] = this.submittedOrders.get(i);
        }
        return submittedOrders;
    }

    public int submitOrder(Order order) { // -1 -> not successful
        int price = order.calculatePrice();
        if (price > balance)
            return -1;
        else {
            balance -= price;
            pendingOrders.remove(order);
            order.setStatus("submitted");
            submittedOrders.add(order);
            return price;
        }
    }

    @Override
    public String toString() {
        return id +
                "," + name +
                "," + balance +
                "," + orders.size() +
                "," + submittedOrders.size();
    }
}
