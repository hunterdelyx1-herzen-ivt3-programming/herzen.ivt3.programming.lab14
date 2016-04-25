package ru.spb.herzen.ivt3.Zad4;

public abstract class PrimeNumberCalculator implements Runnable {
    protected Long startNumber;
    protected Long endNumber;

    @Override
    public void run() {
        for (Long number = startNumber; number < endNumber; number++) {

            boolean prime = true;
            for (Long i = 2L; i < Math.sqrt(number) && prime; i++) {
                if (number % i == 0) prime = false;
            }

            if (prime) save(number);
        }
    }

    protected abstract void save(Long number);
}
