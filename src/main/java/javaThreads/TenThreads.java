package javaThreads;

/**
 * Creates ten threads to search for the maximum value of a large matrix.
 * Each thread searches one portion of the matrix.
 */
public class TenThreads {

    private static class WorkerThread extends Thread {
        int max = Integer.MIN_VALUE;//the max variable inst a shared data between other threards.
        int[] ourArray;

        public WorkerThread(int[] ourArray) {
            this.ourArray = ourArray;
        }

        // Find the maximum value in our particular piece of the array
        public void run() {
            for (int i = 0; i < ourArray.length; i++)
                max = Math.max(max, ourArray[i]);
        }

        /**
         * To retrive the calculate max value.
         * @return
         */
        public int getMax() {
            return max;
        }
    }

    public static void main(String[] args) {
        WorkerThread[] threads = new WorkerThread[10];
        int[][] bigMatrix = getBigHairyMatrix();
        int max = Integer.MIN_VALUE;

        // Give each thread a slice of the matrix to work with
        for (int i=0; i < 10; i++) {
            threads[i] = new WorkerThread(bigMatrix[i]);
            threads[i].start();
        }

        // Wait for each thread to finish
        try {
            for (int i=0; i < 10; i++) {
                threads[i].join();// will make sure that threads[i] is terminates before the next instruction is  executed by the program.
                max = Math.max(max, threads[i].getMax());
            }
        }
        catch (InterruptedException e) {
            // fall through
        }

        System.out.println("Maximum value was " + max);
    }

    private static int[][] getBigHairyMatrix() {
        int mat[][] = { { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                {1,45,23,76,76,8,787},
                {111,2,765,4567,576,567,567,567},
                {987,765},
                {987,765},
                {987,765},
                {987,765},
                {987,765}};
        return mat;
    }
}
