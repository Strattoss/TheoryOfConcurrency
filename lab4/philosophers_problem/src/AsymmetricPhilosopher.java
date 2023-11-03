public class AsymmetricPhilosopher extends Philosopher{
    protected final int id;

    public AsymmetricPhilosopher(Chopstick leftChopstick, Chopstick rightChopstick, int id) {
        super(leftChopstick, rightChopstick);
        this.id = id;
    }

    @Override
    public void run() {
        try {
            if (id % 2 == 0) {
                // even number; first pick up the right chopstick
                while (true) {
                    doAction("Thinking");

                    rightChopstick.acquire();
                    log("Picked up right chopstick");

                    if (leftChopstick.tryAcquire()) {
                        log("Picked up left chopsticks");

                        doAction("Eating");

                        leftChopstick.release();
                        log("Put down left chopsticks");

                    } else {
                        log("Failed to pick up left chopstick. Returning both chopsticks");
                    }


                    rightChopstick.release();
                    log("Put down right chopstick. Back to thinking");
                }
            } else {
                // odd number; first pick up the left chopstick
                while (true) {
                    doAction("Thinking");

                    leftChopstick.acquire();
                    log("Picked up left chopstick");

                    if (rightChopstick.tryAcquire()) {
                        log("Picked up right chopsticks");

                        doAction("Eating");

                        rightChopstick.release();
                        log("Put down right chopsticks");

                    } else {
                        log("Failed to pick up right chopstick. Returning both chopsticks");
                    }


                    leftChopstick.release();
                    log("Put down left chopstick. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

    }
}
