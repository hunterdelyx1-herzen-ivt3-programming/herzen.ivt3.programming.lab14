package ru.spb.herzen.ivt3.Zad1;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Zad1 {
    public static void main(String[] args) {
        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Thread-%d")
                .setDaemon(false)
                .build();

        ExecutorService service = Executors.newFixedThreadPool(10, threadFactory);

        for (int i = 0; i < 20; i++) {
            service.submit(new CounterThread());
        }

        service.shutdown();
    }
}
