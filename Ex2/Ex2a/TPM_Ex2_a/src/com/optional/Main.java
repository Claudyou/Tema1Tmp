package com.optional;

public class Main {

    public static void main(String[] args) {
        LockFreeQueue twoThreadsQueue = new LockFreeQueue(10);
        LockBasedQueue nThreadsQueue = new LockBasedQueue(5);
        LockBasedQueueCorrect lockBasedQueueCorrect = new LockBasedQueueCorrect(5);

        for(int i=0; i<10;i++) {
            Producator prod1 = new Producator(lockBasedQueueCorrect);
            prod1.start();
        }
        for(int i=0; i<10;i++) {
            Consumator cons1 = new Consumator(lockBasedQueueCorrect);
            cons1.start();
        }



    }
}
