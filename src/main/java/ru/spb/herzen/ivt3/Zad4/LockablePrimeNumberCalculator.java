package ru.spb.herzen.ivt3.Zad4;

import java.util.List;

public class LockablePrimeNumberCalculator extends PrimeNumberCalculator {
    private List<Long> list;

    public LockablePrimeNumberCalculator(List<Long> list, Long startNumber) {
        super(startNumber);
        this.list = list;
    }

    protected void save(Long number) {
        list.add(number);
    }
}
