import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        naivePhilosophers(5);
//        starvablePhilosophers(5);
//        asymmetricPhilosophers(5);
//        waiterPhilosophers(5);
    }

    public static void naivePhilosophers(int n) {
        List<Chopstick> chopsticks = new ArrayList<>();
        List<Philosopher> philosophers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            chopsticks.add(new Chopstick());
        }

        for (int i = 0; i < n; i++) {
            if (i != n-1) {
                philosophers.add(new NaivePhilosopher(chopsticks.get(i), chopsticks.get(i + 1)));
            }
            else {
                philosophers.add(new NaivePhilosopher(chopsticks.get(i), chopsticks.get(0)));
            }
        }

        for (var philosopher : philosophers) {
            philosopher.start();
        }
    }

    public static void starvablePhilosophers(int n) {
        List<Chopstick> chopsticks = new ArrayList<>();
        List<Philosopher> philosophers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            chopsticks.add(new Chopstick());
        }

        for (int i = 0; i < n; i++) {
            if (i != n-1) {
                philosophers.add(new StarvablePhilosopher(chopsticks.get(i), chopsticks.get(i + 1)));
            }
            else {
                philosophers.add(new StarvablePhilosopher(chopsticks.get(i), chopsticks.get(0)));
            }
        }

        for (var philosopher : philosophers) {
            philosopher.start();
        }
    }

    public static void asymmetricPhilosophers(int n) {
        List<Chopstick> chopsticks = new ArrayList<>();
        List<Philosopher> philosophers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            chopsticks.add(new Chopstick());
        }

        for (int i = 0; i < n; i++) {
            if (i != n-1) {
                philosophers.add(new AsymmetricPhilosopher(chopsticks.get(i), chopsticks.get(i + 1), i));
            }
            else {
                philosophers.add(new AsymmetricPhilosopher(chopsticks.get(i), chopsticks.get(0), i));
            }
        }

        for (var philosopher : philosophers) {
            philosopher.start();
        }
    }

    public static void waiterPhilosophers(int n) {
        List<Chopstick> chopsticks = new ArrayList<>();
        List<Philosopher> philosophers = new ArrayList<>();

        Waiter waiter = new Waiter(n-1);

        for (int i = 0; i < n; i++) {
            chopsticks.add(new Chopstick());
        }

        for (int i = 0; i < n; i++) {
            if (i != n-1) {
                philosophers.add(new WaitingPhilosopher(chopsticks.get(i), chopsticks.get(i + 1), waiter));
            }
            else {
                philosophers.add(new WaitingPhilosopher(chopsticks.get(i), chopsticks.get(0), waiter));
            }
        }

        for (var philosopher : philosophers) {
            philosopher.start();
        }
    }
}