public class Repository {
    private int id;
    private int capacity;
    private int FreeCapacity;

    public Repository(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        FreeCapacity = capacity;
    }

    public int getFreeCapacity() {
        return FreeCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addGood(Good g, int amount){

    }
}
