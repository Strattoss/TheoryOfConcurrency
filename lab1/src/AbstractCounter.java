public abstract class AbstractCounter {
    protected int counter = 0;

    public abstract void increment();
    public abstract void decrement();

    public abstract String getCounterName();

    public int getCounter() {
        return counter;
    }
}
