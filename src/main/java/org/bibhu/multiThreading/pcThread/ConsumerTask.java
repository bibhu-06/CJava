package org.bibhu.multiThreading.pcThread;

public class ConsumerTask implements Runnable {

    private final SharedResources sharedResources;

    public ConsumerTask(SharedResources sharedResources) {
        this.sharedResources = sharedResources;
    }

    @Override
    public void run() {
        System.out.println("Consumer thread started: " + Thread.currentThread().getName());
        sharedResources.consumeItem();
    }
}
