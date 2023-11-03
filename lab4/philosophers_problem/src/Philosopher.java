import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Philosopher extends Thread{
    protected final Chopstick leftChopstick, rightChopstick;

    public Philosopher(Chopstick leftChopstick, Chopstick rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    protected void doAction(String message) throws InterruptedException {
        log(message);
        Thread.sleep(((int) (Math.random() * 10)));
    }

    protected void log(String message) {
        System.out.println(Thread.currentThread().getName() + " " + System.nanoTime() + ": " + message);
    }
}
