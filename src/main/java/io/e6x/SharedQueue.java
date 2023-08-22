package io.e6x;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SharedQueue {
    Queue<Integer> queue ;
    int size;
    Semaphore fullCount;
    Semaphore emptyCount;

    public SharedQueue(int size){
        queue = new LinkedList<>();
        this.size = size;
        this.fullCount = new Semaphore(0);
        this.emptyCount = new Semaphore(size);
    }
    public void produce() throws InterruptedException {
        emptyCount.acquire();
        Random random = new Random();
        queue.add(random.nextInt());
        fullCount.release();
    }
    public void consume() throws InterruptedException {
        fullCount.acquire();
        int value = queue.poll();
        System.out.println(value);
        emptyCount.release();
    }


}
