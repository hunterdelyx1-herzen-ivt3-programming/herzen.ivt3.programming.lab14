package ru.spb.herzen.ivt3.Zad4;

public abstract class PrimeNumberCalculator implements Runnable {
    protected Long startNumber;
    protected Long endNumber;

    private boolean isPrime(Long number) {
        for (Long i = 2L; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    @Override
    public void run() {
        for (Long number = startNumber; number < endNumber; number++) {
            if (isPrime(number)) save(number);
        }
    }

    protected abstract void save(Long number);
}
