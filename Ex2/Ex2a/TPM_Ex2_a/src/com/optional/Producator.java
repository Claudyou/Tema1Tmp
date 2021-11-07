package com.optional;

import java.util.Random;

public class Producator extends Thread{
    LockFreeQueue lockFreeQueue;
    LockBasedQueue lockBasedQueue;
    LockBasedQueueCorrect lockBasedQueueCorrect;

 Producator(LockBasedQueueCorrect lockBasedQueueCorrect) {
        this.lockBasedQueueCorrect = lockBasedQueueCorrect;
    }

    public void run() {
        int i=1;
        Random random = new Random();
        while(i<=10) {
            int val = random.nextInt();
            lockBasedQueueCorrect.enq(val);
            //System.out.println("am produs: " + val);
            i++;
        }
    }
}
