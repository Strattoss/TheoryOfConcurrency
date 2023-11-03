public class Main {
    public static void main (String[] args) {
//        runCounter(new SynchronisedCounter());
//        runCounter(new UnsynchronisedCounter());
        runNThreads(1000);
    }

    public static void runCounter(AbstractCounter counter) {
        Thread incrementer = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                counter.increment();
            }
        });

        Thread decrementer = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                counter.decrement();
            }
        });

        var startTime = System.currentTimeMillis();

        incrementer.start();
        decrementer.start();

        try {
            incrementer.join();
            decrementer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var endTime = System.currentTimeMillis();

        System.out.println(counter.getCounterName() + ":");
        System.out.println("\tTime = " + (endTime - startTime));
        System.out.println("\tEnd counter value = " + counter.counter);
        System.out.println();
    }

    public static void runNThreads(int threadNumber) {
        for (int i = 0; i < threadNumber; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getId());
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }
}