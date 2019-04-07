import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Shop {
    private static int count = 0;
    private static int unknownCount = 0;
    private String name;
    private ArrayList<Customer> customers;
    private ArrayList<Repository> repositories;
    private ArrayList<Repository> repositoriesById;
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
        repositoriesById = new ArrayList<>();
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

    public Customer searchCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id)
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

    public void addRepositoryById(Repository repository) {
        for (int i = 0; i < repositoriesById.size(); i++) {
            if (repository.getId() < repositoriesById.get(i).getId()) {
                repositoriesById.add(i, repository);
                return;
            }
        }
        repositoriesById.add(repository);
    }

    public Repository[] getRepositories() {
        Repository[] repositories = new Repository[this.repositories.size()];
        for (int i = 0; i < this.repositories.size(); i++) {
            repositories[i] = this.repositories.get(i);
        }
        return repositories;
    }

    public boolean checkRepositories(Order order) {
        HashMap<Good, Integer> orderGoods = order.getItems();

        ArrayList<Repository> repositoryList = new ArrayList<>();

        Set<Map.Entry<Good, Integer>> orderGoodsEntries = orderGoods.entrySet();

        for (Map.Entry<Good, Integer> entry : orderGoodsEntries) {
            boolean isGoodAvailable = false;
            for (int i = 0; i < repositoriesById.size(); i++) {
                Repository repository = repositoriesById.get(i);
                isGoodAvailable = repository.removeGood(entry.getKey(), entry.getValue());
                if (isGoodAvailable) {
                    repositoryList.add(repository);
                    break;
                }
            }
            if (!isGoodAvailable) {
                int counter = 0,abcde;
                for (Map.Entry<Good, Integer> entry2 : orderGoodsEntries) {
                    repositoryList.get(counter).addGood(entry2.getKey(), entry2.getValue());
                    counter++;
                }
                return false;
            }
        }

        int counter = 0;
        for (Map.Entry<Good, Integer> entry : orderGoodsEntries) {
            repositoryList.get(counter).addGood(entry.getKey(), entry.getValue());
            counter++;
        }
        return true;
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
                return;
            }
        }
    }

    public void decreamentGood(Good good, int amount) {
        for (Repository repository : repositoriesById) {
            if (repository.removeGood(good, amount))
                return;
        }
    }

    public Good[] getGoods() {
        Good[] goods = new Good[this.goods.size()];
        for (int i = 0; i < this.goods.size(); i++) {
            goods[i] = this.goods.get(i);
        }
        return goods;
    }

    public Good searchGoodById(int id) {
        for (Good good : goods) {
            if (good.getId() == id)
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

    public Discount searchDiscountById(int id) {
        for (Discount discount : discounts) {
            if (discount.getId() == id)
                return discount;
        }
        return null;
    }
    //

    //Orders
    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order searchOrderById(int id) {
        for (Order element : orders) {
            if (element.getId() == id)
                return element;
        }
        return null;
    }

    public Order[] getOrders() {
        Order[] out = new Order[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            out[i] = orders.get(i);
        }
        return out;
    }
    //

    public void addSoldItems(Order order) {
        HashMap<Good, Integer> orderGoods = order.getItems();
        for (Map.Entry<Good, Integer> entry : orderGoods.entrySet()) {
            int newGoodCount = entry.getValue();
            if (itemsSold.containsKey(entry.getKey())) {
                newGoodCount += itemsSold.get(entry.getKey());
            }
            itemsSold.put(entry.getKey(), newGoodCount);

            decreamentGood(entry.getKey(), entry.getValue());
        }
    }

    public HashMap<Good, Integer> getItemsSold() {
        return itemsSold;
    }
}
