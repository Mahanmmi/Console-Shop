import java.util.Scanner;

public class Main{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        Shop myShop = new Shop("MyShop");
//        PrintStream printStream = new PrintStream("out.txt");
//        System.setOut(printStream);

        boolean isEnded = false;
        while (!isEnded) {
            String operationMode = scanner.next();
            switch (operationMode) {
                case "add": {
                    String objectMode = scanner.next();
                    switch (objectMode) {
                        case "customer": { //add customer
                            int customerId = scanner.nextInt();
                            String customerName = scanner.next();
                            Customer customer = new Customer(customerName, customerId);
                            myShop.addCustomer(customer);
                            break;
                        }
                        case "good": { //add good
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
                        case "repository": { //add repository
                            int repositoryId = scanner.nextInt();
                            int repositoryCapacity = scanner.nextInt();
                            Repository repository = new Repository(repositoryId, repositoryCapacity);
                            myShop.addRepository(repository);
                            myShop.addRepositoryById(repository);
                            break;
                        }
                        case "order": { //add order
                            int orderId = scanner.nextInt();
                            int orderCustomerId = scanner.nextInt();

                            Customer customer = myShop.searchCustomerById(orderCustomerId);

                            Order order = new Order(orderId, customer);
                            customer.addOrder(order);
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
                        case "item": { //add item
                            int orderId = scanner.nextInt();
                            int goodId = scanner.nextInt();
                            int goodAmount = scanner.nextInt();
                            Order order = myShop.searchOrderById(orderId);
                            if (order == null) {
                                break;
                            }
                            Good good = myShop.searchGoodById(goodId);
                            order.addItem(good, goodAmount);
                            break;
                        }
                        case "discount": { //add discount
                            int discountId = scanner.nextInt();
                            int discountPercent = scanner.nextInt();
                            Discount discount = new Discount(discountId, discountPercent);
                            myShop.addDiscount(discount);
                            break;
                        }
                    }
                    break;
                }
                case "report": {
                    String objectMode = scanner.next();
                    switch (objectMode) {
                        case "customers": { //show customers
                            Customer[] customers = myShop.getCustomers();
                            for (Customer element :
                                    customers) {
                                System.out.println(element);
                            }
                            break;
                        }
                        case "repositories": { //show repositories
                            Repository[] repositories = myShop.getRepositories();
                            for (Repository element :
                                    repositories) {
                                System.out.println(element);
                            }
                            break;
                        }
                        case "income": { //show income
                            System.out.println(myShop.getIncome());
                            break;
                        }
                    }
                    break;
                }
                case "remove": {
                    String objectMode = scanner.next();
                    switch (objectMode) {
                        case "item": { //remove item
                            int orderId = scanner.nextInt();
                            int goodId = scanner.nextInt();
                            Order order = myShop.searchOrderById(orderId);
                            Good good = myShop.searchGoodById(goodId);
                            order.removeItem(good);
                            break;
                        }
                    }
                    break;
                }
                case "submit": {
//                    System.out.print("submit ");
                    String objectMode = scanner.next();
                    switch (objectMode) {
                        case "order": { //submit order
//                            System.out.println("order");
                            int orderId = scanner.nextInt();
                            Order order = myShop.searchOrderById(orderId);
//                            Order[] abcd = myShop.getOrders();
//                            for (int i = 0; i < abcd.length; i++) {
//                                System.out.println(i + "->>> " + abcd[i]);
//                            }
//                            if(order == null)
//                                break;
                            Customer customer = order.getCustomer();



                            if (myShop.checkRepositories(order)) {

                                int price = customer.submitOrder(order);
                                if (price != -1) {
                                    myShop.addSoldItems(order);
                                    myShop.setIncome(myShop.getIncome() + price);
                                }
                            }
                            break;
                        }
                        case "discount": { //submit discount
//                            System.out.println("discount");
                            int orderId = scanner.nextInt();
                            int discountId = scanner.nextInt();
                            Order order = myShop.searchOrderById(orderId);
//                            System.out.println("order = " + order);
                            Discount discount = myShop.searchDiscountById(discountId);
                            myShop.addDiscount(discount, order);
                            break;
                        }
                    }
                    break;
                }
                case "terminate": { //end
                    isEnded = true;
                    break;
                }
            }
        }
    }
}

