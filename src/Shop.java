import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private static int count = 0;
    private static int unknownCount = 1;
    private String name;
    private ArrayList<Customer> customers;
    private ArrayList<Repository> repositories;
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
    //

    //Repositories
    public void addRepository(Repository repository) {
        for(int i=0;i<repositories.size();i++){
            if(repository.getCapacity() < repositories.get(i).getCapacity()){
                repositories.add(i,repository);
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
        for(Repository repositry : repositories) {
            if(repositry.getFreeCapacity()>=amount){
                repositry.addGood(good,amount);
            }
        }
    }

    public Good[] getGoods(){
        Good[] goods = new Good[this.goods.size()];
        for (int i = 0; i < this.goods.size(); i++) {
            goods[i] = this.goods.get(i);
        }
        return goods;
    }
    //

    //Discounts
    public void addDiscount(Discount discount){
        discounts.add(discount);
    }
    public void addDiscount(Discount discount, Customer customer){
        if(discounts.indexOf(discount) == -1){
            this.addDiscount(discount);
        }
        customer.getDiscounts().put(discount.getId(),false);
    }
    //

    public HashMap<Good, Integer> getItemsSold() {
        return itemsSold;
    }
}
