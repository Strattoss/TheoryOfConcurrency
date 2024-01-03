package list;

public class LockListGrain implements LockList{
    Node head = null;


    @Override
    public boolean contains(Object searchedObject) {
        if (head == null) {
            return false;
        }

        Node curr = head;
        curr.lock();
        while (curr != null) {
            if (curr.object == searchedObject) {
                curr.unlock();
                return true;
            }
            var nextNode = curr.nextNode;
            if (nextNode != null) {
                nextNode.lock();
            }
            curr.unlock();
            curr = nextNode;
        }

        return false;
    }

    @Override
    public boolean remove(Object searchedObject) {

        if (head == null) {
            return false;
        }

        // searched value is in the head
        head.lock();
        if (head.object == searchedObject) {
            if (head.nextNode == null) {
                head.unlock();
                head = null;
                return true;
            }
            head.nextNode.lock();

            var tmp = head;
            head = head.nextNode;

            tmp.unlock();
            head.unlock();
            return true;
        }

        Node prev = head;
        Node curr = head.nextNode;
        curr.lock();
        while (curr != null) {
            if (curr.object == searchedObject) {
                // relink nodes
                prev.nextNode = curr.nextNode;

                prev.unlock();
                curr.unlock();
                return true;
            }

            prev = prev.nextNode;
            prev.unlock();
            if (curr.nextNode != null) {
                curr.nextNode.lock();
            }
            curr = curr.nextNode;
        }

        prev.unlock();
        return false;
    }

    @Override
    public boolean add(Object newObject) {
        if (head == null) {
            var newNode = new Node(newObject);
            newNode.lock();
            head = newNode;
            head.unlock();
            return true;
        }

        Node curr = head;
        curr.lock();
        while (curr.hasNext()) {
            curr.nextNode.lock();
            curr.unlock();
            curr = curr.nextNode;
        }

        var newNode = new Node(newObject);
        newNode.lock();

        curr.nextNode = newNode;
        curr.unlock();
        newNode.unlock();

        return true;
    }
}
