package com.optional;

public class Chef extends Thread{
    SharedPot pot;

    public Chef(SharedPot sharedPotLock){
        this.pot= sharedPotLock;
    }
    public void run(){
        while(true) {
            if(pot.isOrderFlag()) {
                pot.Refill();
            }
        }
    }
}
