import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PKmon {
    public static void main(String[] args) {
//        zad1();
//        zad2(3, 5);
        zad3(5);
    }


    public static void zad1() {
        Buffer buffer = new Buffer();

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }

    public static void zad2(int producersNum, int consumersNum) {
        Buffer buffer = new Buffer();

        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();

        for (int i = 0; i < producersNum; i++) {
            producers.add(new Producer(buffer));
        }

        for (int i = 0; i < consumersNum; i++) {
            consumers.add(new Consumer(buffer));
        }

        for (Producer producer :
                producers) {
            producer.start();
        }

        for (Consumer consumer :
                consumers) {
            consumer.start();
        }
    }

    public static void zad3(int threadsNum) {
        var random = new Random();
        List<Buffer> buffers = new ArrayList<>();

        for (int i = 0; i < threadsNum-1; i++) {
            buffers.add(new Buffer());
        }

        List<ProdAndCon> prodAndCons = new ArrayList<>();

        Producer producer = new Producer(buffers.get(0));

        for (int i = 0; i < threadsNum-2; i++) {
            prodAndCons.add(
                    new ProdAndCon(
                            buffers.get(i),
                            buffers.get(i+1),
                            random.nextInt(100, 500),
                            random.nextInt(100, 500),
                            i
                    )
            );
        }

        Consumer consumer = new Consumer(buffers.get(threadsNum-2));

        producer.start();
        for (ProdAndCon prodAndCon :
                prodAndCons) {
            prodAndCon.start();
        }
        consumer.start();

    }
}