package com.optional;

import java.util.concurrent.locks.ReentrantLock;

public class LockBasedQueueCorrect {
    volatile int head = 0, tail = 0;
    volatile int items [];
    int QSIZE;
    ReentrantLock lockEnq = new ReentrantLock();
    ReentrantLock lockDeq = new ReentrantLock();


    public LockBasedQueueCorrect(int QSIZE) {
        this.QSIZE = QSIZE;
        this.items = new int[ QSIZE ];
    }
    public String displayArray(int items[]){
        String array="";
        for(int i=0; i<items.length; i++){
            array+=items[i] + "; ";
        }
        return array;
    }
    public synchronized void printOrder(){
        System.out.println("head: " + this.head + "\t" + "tail: " + this.tail + "\t" + "items: " + displayArray(this.items));
        System.out.println("\n");
    }

    public synchronized void printOrder(String st){
        System.out.println(st);
        System.out.println("\n");
    }


    public void enq(int x) {
        lockEnq.lock();
        try {
            while ( tail - head == QSIZE ) {};
            items [ tail % QSIZE ] = x;
            // printOrder("pun");
            printOrder();
            tail ++;

        } finally {
            lockEnq.unlock();

        }
    }

    public int deq () {
        lockDeq.lock();
        try {
            while ( tail == head ) {};
            int item = items [ head % QSIZE ];
            items[ head % QSIZE]=0;
            // printOrder("pun");
            printOrder();
            head ++;

            return item;
        } finally {
            lockDeq.unlock();
        }
    }
}
