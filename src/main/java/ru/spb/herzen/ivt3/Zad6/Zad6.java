package ru.spb.herzen.ivt3.Zad6;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import ru.spb.herzen.ivt3.Zad2.MyRejectedExecutionHandler;
import ru.spb.herzen.ivt3.Zad3.MyThreadPoolExecutor;

import java.util.Random;
import java.util.concurrent.*;

import java.lang.Math;

public class Zad6 {
    static String[] keys = {"Orlov", "Muromtseva", "Varlamov", "Nikolaev", "Dunaev", "Bashmanova"};

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> list = new ConcurrentHashMap<String, Integer>();

        Random random = new Random();

        for (String key : Zad6.keys) {
            list.putIfAbsent(key, Math.abs(random.nextInt()) % 99);
        }

        list.forEach((String key, Integer value) -> {
            System.out.println(key + " : " + value);
        });

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Thread-%d")
                .setDaemon(false)
                .build();

        ExecutorService service = Executors.newFixedThreadPool(30, threadFactory);

        for (int i = 0; i<30; i++) {
            service.submit(() -> {
                for (int j = 0; j<20; j++){
                    Integer index = Math.abs(random.nextInt()) % Zad6.keys.length;
                    Integer value = list.get(Zad6.keys[index]);
                    System.out.println(Thread.currentThread().getName()+ " key: " + Zad6.keys[index] + ", value: " + value);
                    if( value % 5 == 0) list.put(Zad6.keys[index], value/5);
                    else list.put(Zad6.keys[index], value + 7);
                }
            });
        }

        service.shutdown();
        try {
            while (!service.awaitTermination(1, TimeUnit.SECONDS));
        } catch (Exception exception) {

        }

        System.out.println("\nResult:");
        list.forEach((String key, Integer value) -> {
            System.out.println(key + " : " + value);
        });

    }
}
