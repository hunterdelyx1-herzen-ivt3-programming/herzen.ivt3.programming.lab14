package ru.spb.herzen.ivt3.Zad4;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import ru.spb.herzen.ivt3.Zad2.MyRejectedExecutionHandler;
import ru.spb.herzen.ivt3.Zad3.MyThreadPoolExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Zad4 {
    public static void main(String[] args) {
        Long first = Zad4.calc(25, 5);
        Long second = Zad4.calc(25, 15);

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

        List<Long> list = Collections.synchronizedList(new ArrayList<Long>());

        Long startNumber = 1L;
        for (int i = 0; i < number; i++) {
            service.submit(new LockablePrimeNumberCalculator(list, startNumber));
            startNumber += 1000000L;
        }

        service.shutdown();

        try {
            while (!service.awaitTermination(1, TimeUnit.SECONDS)) ;
        } catch (Exception exception) {

        }

        System.out.println("There is " + list.size() + " prime numbers in range [" + 1L + "; " + (startNumber) + "]");
        System.out.println("Execution time is " + service.getExecutionTime());
        return service.getExecutionTime();
    }
}
