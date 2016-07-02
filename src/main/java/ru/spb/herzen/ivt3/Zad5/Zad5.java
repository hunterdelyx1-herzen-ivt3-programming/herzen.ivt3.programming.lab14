package ru.spb.herzen.ivt3.Zad5;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import ru.spb.herzen.ivt3.Zad2.MyRejectedExecutionHandler;
import ru.spb.herzen.ivt3.Zad3.MyThreadPoolExecutor;
import ru.spb.herzen.ivt3.Zad4.Zad4;

import java.util.concurrent.*;

public class Zad5 {

    public static void main(String[] args) {
        Long first = Zad5.calc(25, 5);
        Long second = Zad5.calc(25, 15);

        System.out.println("=====");
        System.out.println("fist threads execution time: " + first);
        System.out.println("second threads time: " + second);
    }

    public static Long calc(int threadsNumber, int number) {
        final RejectedExecutionHandler rejectionHandler = new MyRejectedExecutionHandler();

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Thread-%d")
                .build();

        MyThreadPoolExecutor service = new MyThreadPoolExecutor(number, number, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(threadsNumber), threadFactory, rejectionHandler);

        ConcurrentLinkedQueue<Long> list = new ConcurrentLinkedQueue<>();


        Long startNumber = 1L;
        for (int i = 0; i < number; i++) {
            service.submit(new NotLockablePrimeNumberCalculator(list, startNumber));
            startNumber += 1000000L;
        }

        service.shutdown();
        try {
            while (!service.awaitTermination(1, TimeUnit.SECONDS));
        } catch (Exception exception) {

        }

        System.out.println("There is " + list.size() + " prime numbers in range [" + 1L + "; " + (startNumber) + "]");
        System.out.println("Execution time is " + service.getExecutionTime());
        return service.getExecutionTime();
    }
}
