import java.util.concurrent.Semaphore;

public class Waiter {
    private Semaphore semaphore;

    public Waiter(int maxCompetingPhilosophers) {
        semaphore = new Semaphore(maxCompetingPhilosophers);
    }

    public void waitForWaiterPermision() throws InterruptedException {
        semaphore.acquire();
    }

    public void releasePermision() {
        semaphore.release();
    }
}
