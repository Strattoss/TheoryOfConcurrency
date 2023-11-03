import java.util.Random;

class Consumer extends Thread {
    private final Buffer buf;

    public Consumer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            System.out.println("[" + currentThread().getName() + "] Final value: " + buf.get());
            try {
                sleep(random.nextInt(400));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
