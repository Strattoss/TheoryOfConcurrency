import java.util.Random;

class Producer extends Thread {
    private final Buffer buf;

    public Producer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            buf.put(i);
            try {
                sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
