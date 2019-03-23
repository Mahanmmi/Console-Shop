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
            goods.put(good, amount);
        }
        freeCapacity -= amount;
    }

    public boolean removeGood(Good good, int amount) {
        if (goods.containsKey(good) && goods.get(good) >= amount) {
            goods.put(good, goods.get(good) - amount);
            freeCapacity += amount;
            return true;
        }
        return false;
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

    public boolean checkRepository(Good good, int amount) {
        return goods.containsKey(good) && goods.get(good) >= amount;
    }

    @Override
    public String toString() {
        return id +
                "," + capacity +
                "," + freeCapacity;
    }
}
