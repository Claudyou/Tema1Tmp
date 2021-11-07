package com.optional;

public class LockFreeQueue {
    int head = 0, tail = 0;
    int QSIZE;
    int items [];

    public LockFreeQueue(int QSIZE) {
        this.QSIZE = QSIZE;
        this.items = new int[ QSIZE ];
    }


    public void enq(int x) {
        while ( tail - head == QSIZE ) {};
        items [ tail % QSIZE ] = x;
        tail ++;
    }

    public int deq () {
        while ( tail == head ) {};
        int item = items [ head % QSIZE ];
        head ++;
        return item;
    }
}
