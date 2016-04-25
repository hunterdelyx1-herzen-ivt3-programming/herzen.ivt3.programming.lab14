package ru.spb.herzen.ivt3.Zad2;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import ru.spb.herzen.ivt3.Zad1.CounterThread;

import java.util.concurrent.*;

public class Zad2 {
    public static void main(String[] args) {
        final RejectedExecutionHandler rejectionHandler = new MyRejectedExecutionHandler();

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Thread-%d")
                .build();

        ThreadPoolExecutor service = new ThreadPoolExecutor(15, 15, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), threadFactory, rejectionHandler);

        for (int i = 0; i < 20; i++) {
            service.submit(new CounterThread());
        }
        service.shutdown();
    }
}
