package io.e6x;

import java.util.concurrent.Semaphore;

public class Counter {
    int[] count ;
    public Counter(int[] count){
        this.count = count;
    }
    Semaphore semaphore = new Semaphore(1);
    public void runCounter() throws InterruptedException {
        semaphore.acquire();
        count[0]+=1;
        semaphore.release();
    }

}
