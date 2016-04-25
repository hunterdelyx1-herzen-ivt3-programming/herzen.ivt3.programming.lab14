package ru.spb.herzen.ivt3.Zad1;

import java.util.Random;

public class CounterThread implements Runnable {
    @Override
    public void run() {
        Random random = new Random();

        int startNumber = Math.abs(random.nextInt()) % 100;
        System.out.println("Task " + this.toString() + ": " + Thread.currentThread().getName() + ": Start Number = " + startNumber);

        for (int j = startNumber; j < startNumber + 30; j++) {
            System.out.println("Task " + this.toString() + ": " + Thread.currentThread().getName() + ": Current Number = " + j);
        }

        System.out.println("Task " + this.toString() + ": " + Thread.currentThread().getName() + ": Ended");
    }
}
