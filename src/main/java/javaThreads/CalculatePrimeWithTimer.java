package javaThreads;

import java.util.Timer;
import java.util.TimerTask;

public class CalculatePrimeWithTimer extends Thread {
    public static final int MAX_PRIMES = 1000000;
    public static final int TEN_SECONDS = 10000;

    public volatile boolean finished = false;//that the shared flag is declared volatile. The assumption is that every object instantiate from this class will have access to this variable

    public void run() {
        int[] primes = new int[MAX_PRIMES];
        int count = 0;

        for (int i=2; count<MAX_PRIMES; i++) {

            // Check to see if the timer has expired
            if (finished) {
                break;
            }

            boolean prime = true;
            for (int j=0; j<count; j++) {
                if (i % primes[j] == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                primes[count++] = i;
                System.out.println("Found prime: " + i);
            }
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        final CalculatePrimes calculator = new CalculatePrimes();
        calculator.start();


        timer.schedule(
                new TimerTask() {
                    public void run()
                    {
                        calculator.finished = true;
                    }
                }, TEN_SECONDS);
        try {
            calculator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
