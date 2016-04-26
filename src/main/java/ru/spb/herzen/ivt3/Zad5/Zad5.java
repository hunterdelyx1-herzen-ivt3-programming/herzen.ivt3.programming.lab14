package ru.spb.herzen.ivt3.Zad5;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import ru.spb.herzen.ivt3.Zad2.MyRejectedExecutionHandler;
import ru.spb.herzen.ivt3.Zad3.MyThreadPoolExecutor;
import ru.spb.herzen.ivt3.Zad4.Zad4;

import java.util.concurrent.*;

public class Zad5 {

    public static void main(String[] args) {
        Zad5.calc(5);
        Zad5.calc(15);
    }

    public static Long calc(int pulls) {
        final RejectedExecutionHandler rejectionHandler = new MyRejectedExecutionHandler();

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Thread-%d")
                .build();

        MyThreadPoolExecutor service = new MyThreadPoolExecutor(25, 25, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(pulls), threadFactory, rejectionHandler);

        ConcurrentLinkedQueue<Long> list = new ConcurrentLinkedQueue<>();


        Long startNumber = 1L;
        for (int i = 0; i < pulls; i++) {
            service.submit(new NotLockablePrimeNumberCalculator(list, startNumber));
            startNumber += 1000000L;
        }

        service.shutdown();
        try {
            while (!service.awaitTermination(1, TimeUnit.SECONDS));
        } catch (Exception exception) {

        }

        return service.getExecutionTime();
    }
}
