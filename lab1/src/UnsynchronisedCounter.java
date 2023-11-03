public class UnsynchronisedCounter extends AbstractCounter {

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public void decrement() {
        counter--;
    }

    @Override
    public String getCounterName() {
        return "Unsynchronised counter";
    }
}
