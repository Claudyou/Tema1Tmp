package com.optional;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        SharedPot pot = new SharedPot(7,25);

        Thread chef = new Chef(pot);
        chef.setDaemon(true);
        chef.start();

        for (int i = 0; i < 25; i++) {
            WildPerson person = new WildPerson(pot);
            pot.addPerson(person);
            person.start();
        }



}
}
