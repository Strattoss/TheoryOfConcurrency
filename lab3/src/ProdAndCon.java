import java.util.Random;

import static java.lang.Thread.sleep;

public class ProdAndCon extends Thread {
    private final Buffer getBuff, putBuff;
    private final int getDelay, putDelay;
    private final int id;

    public ProdAndCon(Buffer getBuff, Buffer putBuff, int getDelay, int putDelay, int id) {
        this.getBuff = getBuff;
        this.putBuff = putBuff;
        this.getDelay = getDelay;
        this.putDelay = putDelay;
        this.id = id;
    }

    public void run() {
        Random random = new Random();

        for (int i = 0; i < 100; ++i) {
            int receiverValue = getBuff.get();
            try {
                sleep(random.nextInt(getDelay));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("[" + currentThread().getName() + "] Processing value: " + receiverValue);

            putBuff.put(receiverValue);
            try {
                sleep(random.nextInt(putDelay));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
