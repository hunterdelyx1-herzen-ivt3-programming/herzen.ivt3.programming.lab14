package ru.spb.herzen.ivt3.Zad4;

import java.util.List;

public class LockablePrimeNumberCalculator extends PrimeNumberCalculator {
    List<Long> list;

    LockablePrimeNumberCalculator(List<Long> list, Long startNumber) {
        this.list = list;
        this.startNumber = startNumber;
        this.endNumber = startNumber + 1000000L;
    }

    protected void save(Long number) {
        list.add(number);
    }
}
