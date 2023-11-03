public class SynchronisedCounter extends AbstractCounter {

    @Override
    synchronized public void increment() {
        counter++;
    }

    @Override
    synchronized public void decrement() {
        counter--;
    }

    @Override
    public String getCounterName() {
        return "Synchronised counter";
    }
}
