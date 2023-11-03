import java.util.ArrayDeque;
import java.util.Deque;

class Buffer {
    private final Deque<Integer> deque = new ArrayDeque<>();
    private final int maxDequeSize = 10;
    private boolean canPut = true;

    synchronized public void put(int i) {
        while (!canPut || deque.size() >= maxDequeSize) {
//            if (deque.size() >= maxDequeSize - 1) {
//                System.out.println("Size: " + deque.size());
//            }
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        deque.addLast(i);
        canPut = false;
        notifyAll();
    }

    synchronized public int get() {
        while (canPut || deque.isEmpty()) {
//            if (deque.isEmpty()) {
//                System.out.println("Deque empty. Waiting...");
//            }
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Integer result = deque.removeFirst();
        canPut = true;
        notifyAll();
        return result;
    }
}
