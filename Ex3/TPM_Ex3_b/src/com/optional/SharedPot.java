package com.optional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class SharedPot {
    int maxNumberOfPersons;
    volatile long startTime;
    volatile long endTime;
    volatile int pieces;
    volatile int N;
    volatile boolean orderFlag;
    volatile boolean enterd=false;
    List<WildPerson> persons = new ArrayList<>();

    ReentrantLock lockEat = new ReentrantLock();
    ReentrantLock lockRefill = new ReentrantLock();


    public SharedPot(int N, int M) {
        startTime=System.nanoTime();
        this.N = N;
        this.pieces = N;
        this.orderFlag = false;
        this.maxNumberOfPersons = M;

    }

    public void setPersons(List<WildPerson> persons) {
        this.persons = persons;
    }

    public void addPerson(WildPerson persons) {
        this.persons.add(persons);
    }

    public synchronized void printOrder(String st) {
        System.out.println(st);
    }

    public synchronized  boolean haveAccess(WildPerson person){
        if(persons.size()<maxNumberOfPersons){ //Pana nu vine toata lumea la masa
            startTime = System.nanoTime();
            printOrder("alabala");
            return false;
        }
        if(person.getNumberOfPiecesEaten()<WildPerson.maxNumberOfPiecesEaten){//Daca nu a mancat tura asta
            return true;
        }

        for(var pers : persons){ //Daca a mancat toata lumea tura asta
            if(pers.getNumberOfPiecesEaten()<WildPerson.maxNumberOfPiecesEaten){
                return false;
            }
        }
        WildPerson.maxNumberOfPiecesEaten++;

        endTime=System.nanoTime();
        try {
            FileWriter writer = new FileWriter("../TPM_Ex3_a/time.txt",true);
            writer.write((endTime - startTime) + "\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        startTime=System.nanoTime();
        return true;
    }

    public void getPiece(WildPerson person) {
        while(!haveAccess(person)){}
        lockEat.lock();
        try {
            while (this.pieces <= 0 || orderFlag) {
                if (!orderFlag && !enterd)
                    this.orderFlag = true;
            }
            enterd=false;
            this.pieces -= 1;
            person.numberOfPiecesEaten++;
            printOrder("am mancat " + person.numberOfPiecesEaten + " am id -> " + person.id + "  tura: " +WildPerson.maxNumberOfPiecesEaten);
        } finally {
            lockEat.unlock();
        }
    }

    public boolean isOrderFlag() {
        return orderFlag;
    }

    public void Refill() {

        enterd=true;
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
