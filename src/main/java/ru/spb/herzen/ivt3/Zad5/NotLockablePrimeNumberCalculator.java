package ru.spb.herzen.ivt3.Zad5;

import ru.spb.herzen.ivt3.Zad4.PrimeNumberCalculator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class NotLockablePrimeNumberCalculator extends PrimeNumberCalculator {
    private ConcurrentLinkedQueue<Long> list;

    public NotLockablePrimeNumberCalculator(ConcurrentLinkedQueue<Long> list, Long startNumber) {
        super(startNumber);
        this.list = list;
    }

    protected void save(Long number) {
        list.add(number);
    }
}
