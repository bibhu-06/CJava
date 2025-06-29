package org.bibhu.multiThreading.pcqThread;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class SharedResources {
    private Queue<Integer> queue;
    private int bufferSize;

    public SharedResources(int bufferSize) {
        this.queue = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produceItem(int item) {
        while(queue.size() >= bufferSize) {
            try {
                log.info("Queue is full, producer thread " + Thread.currentThread().getName() + " is waiting for consumer");
                wait(); // Wait until there is space in the queue
            } catch (InterruptedException e) {
                // Handle interruption
            }
        }
        queue.add(item);
        log.info("Producer: Added item " + item + " by thread " + Thread.currentThread().getName());
        notify();
    }

    public synchronized void consumeItem() {
        while(queue.isEmpty()) {
            try {
                log.info("Queue is empty, consumer thread " + Thread.currentThread().getName() + " is waiting for producer");
                wait(); // Wait until there is an item to consume
            } catch (InterruptedException e) {
                // Handle interruption
            }
        }
        int item = queue.poll();
        log.info("Consumer: Consumed item " + item + " by thread " + Thread.currentThread().getName());
        notify();
    }
}
