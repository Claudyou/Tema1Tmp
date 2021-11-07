package com.optional;

public class Consumator extends Thread {
    LockFreeQueue lockFreeQueue;
    LockBasedQueue lockBasedQueue;
    LockBasedQueueCorrect lockBasedQueueCorrect;

    public Consumator(LockBasedQueueCorrect lockBasedQueueCorrect) {
        this.lockBasedQueueCorrect = lockBasedQueueCorrect;
    }
    public void run() {
        int i=1;
        while(i<=10) {
            lockBasedQueueCorrect.deq();
            //System.out.println("am consumat " + lockBasedQueue.deq());
            i++;
        }
    }
}
