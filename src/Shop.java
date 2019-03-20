import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private static int count = 0;
    private static int unknownCount = 0;
    private String name;
    private ArrayList<Customer> customers;
    private ArrayList<Repository> repositories;
    private ArrayList<Order> orders;
    private ArrayList<Good> goods;
    private ArrayList<Discount> discounts;
    private HashMap<Good, Integer> itemsSold;
    private int income;


    //Constructor
    public Shop() {
        this("___UNKNOWN___" + unknownCount);
        unknownCount++;
    }

    public Shop(String name) {
        this.name = name;
        customers = new ArrayList<>();
        repositories = new ArrayList<>();
        goods = new ArrayList<>();
        discounts = new ArrayList<>();
        itemsSold = new HashMap<>();
        orders = new ArrayList<>();
        income = 0;
        count++;
    }
    //

    //Customers
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer[] getCustomers() {
        Customer[] customers = new Customer[this.customers.size()];
        for (int i = 0; i < this.customers.size(); i++) {
            customers[i] = this.customers.get(i);
        }
        return customers;
    }
    public Customer searchCustomerById(int id){
        for (Customer customer : customers) {
            if(customer.getId() == id)
                return customer;
        }
        return null;
    }
    //

    //Repositories
    public void addRepository(Repository repository) {
        for (int i = 0; i < repositories.size(); i++) {
            if (repository.getCapacity() < repositories.get(i).getCapacity()) {
                repositories.add(i, repository);
                return;
            }
        }
        repositories.add(repository);
    }

    public Repository[] getRepositories() {
        Repository[] repositories = new Repository[this.repositories.size()];
        for (int i = 0; i < this.repositories.size(); i++) {
            repositories[i] = this.repositories.get(i);
        }
        return repositories;
    }
    //

    //Income
    public void setIncome(int income) {
        this.income = income;
    }

    public int getIncome() {
        return income;
    }
    //

    //Goods
    public void addGood(Good good) {
        goods.add(good);
    }

    public void increamentGood(Good good, int amount) {
        for (Repository repository : repositories) {
            if (repository.getFreeCapacity() >= amount) {
                repository.addGood(good, amount);
            }
        }
    }

    public Good[] getGoods() {
        Good[] goods = new Good[this.goods.size()];
        for (int i = 0; i < this.goods.size(); i++) {
            goods[i] = this.goods.get(i);
        }
        return goods;
    }

    public Good searchGoodById(int id){
        for (Good good : goods) {
            if(good.getId() == id)
                return good;
        }
        return null;
    }
    //

    //Discounts
    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public void addDiscount(Discount discount, Order order) {
        if (discounts.indexOf(discount) != -1) {
            order.addDiscount(discount);
            discounts.remove(discount);
        }
    }
    //

    //Orders
    public void addOrder(Order order){
        orders.add(order);
    }
    public Order searchOrderById(int id){
        for (Order element : orders) {
            if (element.getId() == id)
                return element;
        }
        return null;
    }
    //

    public HashMap<Good, Integer> getItemsSold() {
        return itemsSold;
    }
}
