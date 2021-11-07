package com.optional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        SharedPot pot = new SharedPot(7);

        Thread chef = new Chef(pot);
        chef.setDaemon(true);
        chef.start();

        ArrayList<Thread> threads = new ArrayList();
        for (int i = 0; i < 25; i++) {
            Thread pers = new WildPerson(pot);
            threads.add(pers);
            pers.start();
        }

        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("time.txt"));
            writer.write(String.valueOf(stopTime - startTime) + "\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
