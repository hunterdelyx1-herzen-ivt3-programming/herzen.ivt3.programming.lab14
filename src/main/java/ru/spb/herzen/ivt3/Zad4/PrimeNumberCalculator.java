package ru.spb.herzen.ivt3.Zad4;

import java.util.List;

public class PrimeNumberCalculator implements Runnable {
    List<Long> list;
    Long startNumber;
    Long endNumber;

    public PrimeNumberCalculator(List<Long> list, Long startNumber) {
        this.list = list;
        this.startNumber = startNumber;
        this.endNumber = startNumber + 1000000L;
    }

    @Override
    public void run() {
        for (Long number = startNumber; number < endNumber; number++) {

            boolean prime = true;
            for (Long i = 2L; i < Math.sqrt(number) && prime; i++) {
                if (number % i == 0) prime = false;
            }

            if (prime) list.add(number);
        }
    }
}
