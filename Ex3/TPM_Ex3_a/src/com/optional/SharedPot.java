package com.optional;

import java.util.concurrent.locks.ReentrantLock;

public class SharedPot {
    volatile int pieces;
    volatile int N;
    volatile boolean orderFlag;

    ReentrantLock lockEat = new ReentrantLock();
    ReentrantLock lockRefill = new ReentrantLock();


    public SharedPot(int N) {
        this.N = N;
        this.pieces = N;
        this.orderFlag = false;

    }

    public synchronized void printOrder(String st) {
        System.out.println(st);
    }

    public void getPiece() {
        lockEat.lock();
        try {
            while (this.pieces <= 0 || orderFlag) {
                if (!orderFlag)
                    this.orderFlag = true;
            }
            this.pieces -= 1;
            printOrder("am mancat");
        } finally {
            lockEat.unlock();
        }
    }

    public boolean isOrderFlag() {
        return orderFlag;
    }

    public void Refill() {

        printOrder("umplu oala");
        this.pieces = this.N;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printOrder("am terminat de reumplut oala");
        this.orderFlag = false;


    }
}
