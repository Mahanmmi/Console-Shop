public class Good {
    private static int count = 0;
    private String name;
    private int id;
    private int price;

    public Good(String name, int id, int price) {
        this.name = name;
        this.id = id;
        this.price = price;
        count++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}
