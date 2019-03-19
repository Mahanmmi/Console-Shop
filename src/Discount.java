public class Discount {
    private int id;
    private int percent;

    public Discount(int id, int percent) {
        this.id = id;
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public int getId() {
        return id;
    }
}
