package org.bibhu.multiThreading.pcThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SharedResources {
    private boolean isItemAvailable = false;

    public synchronized void addItem() {
        this.isItemAvailable = true;
        log.info("Producer: Adding item by thread " + Thread.currentThread().getName());
        notifyAll(); // Notify all waiting threads that an item is available
    }

    public synchronized void consumeItem() {
        //while loop to avoid spurious wakeups
        while (!isItemAvailable) {
            try {
                log.info("Consumer: Waiting for item by thread " + Thread.currentThread().getName());
                wait(); // Wait until some calls notify, this releases all locks held by this thread
            } catch (InterruptedException e) {
                log.error("Thread interrupted: " + e.getMessage(), e);
            }
        }
        this.isItemAvailable = false;
        log.info("Consumer: Consuming item by thread " + Thread.currentThread().getName());
    }
}
