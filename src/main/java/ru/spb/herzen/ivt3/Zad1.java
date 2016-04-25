package ru.spb.herzen.ivt3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.lang.Math;

public class Zad1
{
    public static void main( String[] args )
    {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 20; i++) {
            service.submit( () -> {
                Random random = new Random();

                int startNumber = Math.abs(random.nextInt() % 99);
                System.out.println(Thread.currentThread().getName() + ": Start Number = " + startNumber);

                for(int j = startNumber; j < startNumber + 30; j++) {
                    System.out.println(Thread.currentThread().getName() + ": Current Number = " + j);
                }

                System.out.println(Thread.currentThread().getName() + ": Ended");
            });
        }
    }
}
