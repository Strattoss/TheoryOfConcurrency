import list.LockListGlobal;
import list.LockListGrain;
import threads.ListAdder;
import threads.ListContainsChecker;
import threads.ListRemover;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        timeLockListGlobal(10000);
        timeLockListGrain(10000);

    }

    public static void timeLockListGlobal(int n) {
        var globalList = new LockListGlobal();

        ListAdder listAdder = new ListAdder(globalList, n);
        ListRemover listRemover = new ListRemover(globalList, n);
        ListContainsChecker listContainsChecker = new ListContainsChecker(globalList, n);

        List<Thread> threads = new ArrayList<>();
        threads.add(listAdder);
        threads.add(listRemover);
        threads.add(listContainsChecker);

        var startTime = System.nanoTime();
        listAdder.start();
        listContainsChecker.start();
        listRemover.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        var endTime = System.nanoTime();

        System.out.println("Global lock. n = " + n + ", time (ns) = " + (endTime - startTime));

    }

    public static void timeLockListGrain(int n) {
        var globalList = new LockListGrain();

        ListAdder listAdder = new ListAdder(globalList, n);
        ListRemover listRemover = new ListRemover(globalList, n);
        ListContainsChecker listContainsChecker = new ListContainsChecker(globalList, n);

        List<Thread> threads = new ArrayList<>();
        threads.add(listAdder);
        threads.add(listRemover);
        threads.add(listContainsChecker);

        var startTime = System.nanoTime();
        listAdder.start();
        listContainsChecker.start();
        listRemover.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        var endTime = System.nanoTime();

        System.out.println("Grain lock. n = " + n + ", time (ns) = " + (endTime - startTime));
    }
}
