package ru.spb.herzen.ivt3.Zad4;

public class PrimeNumberCalculator implements Runnable {
    protected Long startNumber;
    protected Long endNumber;

    public PrimeNumberCalculator(Long startNumber) {
        this.startNumber = startNumber;
        this.endNumber = startNumber + 1000000L;
    }

    private boolean isPrime(Long number) {
        if (number == 1L) return false;

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

    protected void save(Long number) {
        System.out.println(number);
    };
}
