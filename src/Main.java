import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Shop myShop = new Shop("MyShop");
        while (true) {
            String operationMode = scanner.next();
            switch (operationMode) {
                case "add": {
                    String objectMode = scanner.next();
                    switch (objectMode) {
                        case "customer": {
                            int customerId = scanner.nextInt();
                            String customerName = scanner.next();
                            Customer customer = new Customer(customerName, customerId);
                            myShop.addCustomer(customer);
                            break;
                        }
                        case "good": {
                            int goodId = scanner.nextInt();
                            String goodName = scanner.next();
                            int goodPrice = scanner.nextInt();
                            int goodAmount = scanner.nextInt();
                            Good good = myShop.searchGoodById(goodId);
                            if (good == null) {
                                good = new Good(goodName, goodId, goodPrice);
                                myShop.addGood(good);
                            }
                            myShop.increamentGood(good, goodAmount);
                            break;
                        }
                        case "repository": {
                            int repositoryId = scanner.nextInt();
                            int repositoryCapacity = scanner.nextInt();
                            Repository repository = new Repository(repositoryId, repositoryCapacity);
                            myShop.addRepository(repository);
                            break;
                        }
                        case "order": {
                            int orderId = scanner.nextInt();
                            int orderCustomerId = scanner.nextInt();
                            Customer customer = myShop.searchCustomerById(orderCustomerId);
                            if (customer == null) {
                                break;
                            }
                            Order order = new Order(orderId, customer);
                            myShop.addOrder(order);
                            break;
                        }
                        case "balance": { //add balance
                            int addBalanceCustomerId = scanner.nextInt();
                            Customer customer = myShop.searchCustomerById(addBalanceCustomerId);
                            int addBalanceBalance = scanner.nextInt();
                            customer.setBalance(customer.getBalance() + addBalanceBalance);
                            break;
                        }
                        case "item": {
                            int orderId = scanner.nextInt();
                            int goodId = scanner.nextInt();
                            int goodAmount = scanner.nextInt();
                            Order order = myShop.searchOrderById(orderId);
                            if(order == null){
                                break;
                            }
                            Good good = myShop.searchGoodById(goodId);
                            order.addItem(good,goodAmount);
                        }
                        case "discount":{
                            int discountId = scanner.nextInt();
                            int discountPercent = scanner.nextInt();
                            Discount discount = new Discount(discountId,discountPercent);
                            myShop.addDiscount(discount);
                            break;
                        }
                    }
                    break;
                }
                case "report":
                    break;
                case "remove":
                    break;
                case "submit":
                    break;
            }
        }
    }
}
