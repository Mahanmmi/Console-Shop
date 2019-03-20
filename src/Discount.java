public class Discount {
    private static int count = 0;
    private int id;
    private int percent;
    private Order order;

    public Discount(int id, int percent) {
        this.id = id;
        this.percent = percent;
        count++;
    }

    public int getPercent() {
        return percent;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public int getId() {
        return id;
    }
}
