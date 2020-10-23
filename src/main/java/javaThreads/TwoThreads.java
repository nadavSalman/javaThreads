package javaThreads;

public class TwoThreads {

    public static class Thread1 extends Thread {
        public void run() {
            System.out.println("A");
            System.out.println("B");
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            System.out.println("1");
            System.out.println("2");
        }
    }


    /**
     * We have no idea in what order the lines will execute,
     * except that “1” will be printed before “2” and “A” before “B.”
     * The output could be any one of the following:
     * 1 2 A B
     * 1 A 2 B
     * 1 A B 2
     * A 1 2 B
     * A 1 B 2
     * A B 1 2
     * Not only may the results vary from machine to machine,
     * but running the same program multiple times on the same machine may produce different results.
     * Never assume one thread will do something before another thread does,
     * unless you’ve used synchronization to force a specific ordering of execution.
     */
    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }
}