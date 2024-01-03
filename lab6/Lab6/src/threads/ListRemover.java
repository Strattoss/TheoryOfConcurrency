package threads;

import list.LockList;

public class ListRemover extends Thread{
    LockList lockList;
    int repeats;

    public ListRemover(LockList lockList, int repeats) {
        this.lockList = lockList;
        this.repeats = repeats;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeats; i++) {
            lockList.remove(i);
        }
    }
}
