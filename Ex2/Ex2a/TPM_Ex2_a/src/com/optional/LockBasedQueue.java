package com.optional;

import java.util.concurrent.locks.ReentrantLock;

public class LockBasedQueue {
    int head = 0, tail = 0;
    int items [];
    int QSIZE;
    ReentrantLock lock = new ReentrantLock();


    public LockBasedQueue(int QSIZE) {
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
        System.out.println("head: " + this.head + "\t" + "tail: " + this.tail + "\t" + "items" + displayArray(this.items));
        System.out.println("\n");
    }
    public synchronized void printOrder(String st){
        System.out.println(st);
        System.out.println("\n");
    }


    public void enq(int x) {
        while ( tail - head == QSIZE ) {};
        lock.lock();
        try {
            items [ tail % QSIZE ] = x;
            tail ++;
            printOrder("pun");
        } finally {
            lock.unlock();
        }
    }

    public int deq () {
        while ( tail == head ) {};
        lock.lock();
        try {
            int item = items [ head % QSIZE ];
            head ++;
            printOrder("scot");
            return item;
        } finally {
            lock.unlock();
        }
    }
}
