package threads;

import list.LockList;

public class ListAdder extends Thread{
    LockList lockList;
    int repeats;

    public ListAdder(LockList lockList, int repeats) {
        this.lockList = lockList;
        this.repeats = repeats;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeats; i++) {
            lockList.add(i);
        }
    }
}
