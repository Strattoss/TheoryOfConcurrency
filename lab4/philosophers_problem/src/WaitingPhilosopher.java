import java.util.concurrent.Semaphore;

public class WaitingPhilosopher extends Philosopher{
    private Waiter waiter;

    public WaitingPhilosopher(Chopstick leftChopstick, Chopstick rightChopstick, Waiter waiter) {
        super(leftChopstick, rightChopstick);
        this.waiter = waiter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction("Thinking");

                log("Waiting for the waiter's permission...");
                waiter.waitForWaiterPermision();

                leftChopstick.acquire();
                log("Picked up left chopstick");

                rightChopstick.acquire();
                log("Picked up right chopsticks");

                doAction("Eating...");

                leftChopstick.release();
                log("Released left chopstick");

                rightChopstick.release();
                log("Released right chopstick");

                log("Back to thinking");
                waiter.releasePermision();
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
