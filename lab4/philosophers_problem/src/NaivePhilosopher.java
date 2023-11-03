public class NaivePhilosopher extends Philosopher{
    public NaivePhilosopher(Chopstick leftChopstick, Chopstick rightChopstick) {
        super(leftChopstick, rightChopstick);
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction("Thinking");

                leftChopstick.acquire();
                log("Picked up left fork");

                rightChopstick.acquire();
                log("Picked up right fork");

                doAction("Eating");

                rightChopstick.release();
                log("Put down right fork");

                leftChopstick.release();
                log("Put down left fork. Back to thinking");
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
