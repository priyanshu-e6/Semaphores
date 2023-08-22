package io.e6x;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    //altering the count of a number by different threads using a semaphore
    public static void main(String[] args) throws InterruptedException {
        int[] count = new int[1];
        Counter counter = new Counter(count);
        //semaphore used as a lock (binary semaphore)
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<2000;i++) {
                        counter.runCounter();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<2000;i++) {
                        counter.runCounter();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count[0]);

        //semaphore in producer consumer problem. uses binary semaphore
        //emptyCount semaphore  : spaces left in queue. allocates the spaces in queue to fill.
        //fullCount : spaces filled in queue. allocates the data already filled in queue.
        //acquire the emptyCount permit when putting items. release the fullCount when removing.
        //producer acquires the emptyCount as it has to input items. releases fullCount when done.
        //consumer acquires the permit for fullCount and waits if none is available. releases emptyCount when done.
        SharedQueue sharedQueue = new SharedQueue(10);
        Thread threadProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sharedQueue.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread threadConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sharedQueue.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadConsumer.start();
        threadProducer.start();

        threadConsumer.join();
        threadProducer.join();


















    }
}