package list;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node {
    Object object;
    Node nextNode;
    private final Lock lock = new ReentrantLock();

    public Node(Object object) {
        this.object = object;
    }

    boolean hasNext() {
        return nextNode != null;
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }
}
