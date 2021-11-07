package com.optional;

public class WildPerson extends Thread{
    SharedPot pot;
    int id;
    Integer numberOfPiecesEaten;
    static int maxID=0;
    volatile static int maxNumberOfPiecesEaten=0;

    public WildPerson(SharedPot sharedPotLock){

        this.pot= sharedPotLock;
        this.id = maxID++;
        this.numberOfPiecesEaten=0;

    }

    public void run(){
        while (true) {
            pot.getPiece(this);

        }
    }

    public Integer getNumberOfPiecesEaten() {
        return numberOfPiecesEaten;
    }

    public static int getMaxID() {
        return maxID;
    }
}
