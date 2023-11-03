public class Chopstick {
    private boolean acquired = false;

    synchronized public void acquire() {
        while (acquired) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        acquired = true;
    }

    synchronized public void release() {
        while (!acquired) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        acquired = false;
        notifyAll();
    }

    synchronized public boolean tryAcquire() {
        if (acquired) {
            return false;
        }
        else {
            acquired = true;
            return true;
        }
    }
}
