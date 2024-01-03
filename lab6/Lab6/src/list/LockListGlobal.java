package list;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockListGlobal implements LockList{
    Lock lock = new ReentrantLock();
    Node head = null;

    @Override
    public boolean contains(Object searchedObject) {
        lock.lock();

        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.object == searchedObject) {
                lock.unlock();
                return true;
            }
            currentNode = currentNode.nextNode;
        }

        lock.unlock();
        return false;
    }

    @Override
    public boolean remove(Object searchedObject) {
        lock.lock();

        if (head == null) {
            lock.unlock();
            return false;
        }

        // searched value is in the head
        if (head.object == searchedObject) {
            head = head.nextNode;
            lock.unlock();
            return true;
        }

        Node prev = head;
        Node curr = head.nextNode;
        while (curr != null) {
            if (curr.object == searchedObject) {
                // relink nodes
                prev.nextNode = curr.nextNode;

                lock.unlock();
                return true;
            }

            prev = prev.nextNode;
            curr = curr.nextNode;
        }

        lock.unlock();
        return false;
    }

    @Override
    public boolean add(Object newObject) {
        lock.lock();

        if (head == null) {
            head = new Node(newObject);

            lock.unlock();
            return true;
        }

        Node curr = head;
        while (curr.hasNext()) {
            curr = curr.nextNode;
        }

        curr.nextNode = new Node(newObject);

        lock.unlock();
        return true;
    }
}
