import java.util.HashMap;

public class Repository {
    private int id;
    private int capacity;
    private int freeCapacity;
    private HashMap<Good, Integer> goods;

    public Repository(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        freeCapacity = capacity;
        goods = new HashMap<>();
    }

    public void addGood(Good good, int amount) {
        if (goods.containsKey(good)) {
            goods.put(good, goods.get(good) + amount);
        } else {
            goods.put(good,amount);
        }
        freeCapacity-=amount;
    }

    public void removeGood(Good good, int amount) {
        if (goods.containsKey(good)) {
            goods.put(good, goods.get(good) - amount);
        } else {
            System.out.println("EROOR GOOD NOT FOUND");
            return;
        }
        freeCapacity+=amount;
    }

    public int getId() {
        return id;
    }

    public int getFreeCapacity() {
        return freeCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<Good, Integer> getGoods() {
        return goods;
    }
}
