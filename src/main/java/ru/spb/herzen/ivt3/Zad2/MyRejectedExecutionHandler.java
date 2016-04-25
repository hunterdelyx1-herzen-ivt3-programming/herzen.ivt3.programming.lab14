package ru.spb.herzen.ivt3.Zad2;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        System.out.println(runnable.toString() + " is rejected");
    }
}