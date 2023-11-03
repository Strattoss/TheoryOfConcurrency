public class StarvablePhilosopher extends Philosopher{
    public StarvablePhilosopher(Chopstick leftChopstick, Chopstick rightChopstick) {
        super(leftChopstick, rightChopstick);
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction("Thinking");

                leftChopstick.acquire();
                log("Picked up left chopstick");

                if (rightChopstick.tryAcquire()) {
                    log("Picked up right chopsticks");

                    doAction("Eating");

                    rightChopstick.release();
                    log("Put down right chopsticks");

                }
                else {
                    log("Failed to pick up right chopstick. Returning both chopsticks");
                }


                leftChopstick.release();
                log("Put down left chopstick. Back to thinking");
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
