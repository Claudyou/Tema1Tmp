package com.optional;

public class WildPerson extends Thread{
    SharedPot pot;

    public WildPerson(SharedPot sharedPotLock){
        this.pot= sharedPotLock;
    }

    public void run(){
        pot.getPiece();
    }
}
