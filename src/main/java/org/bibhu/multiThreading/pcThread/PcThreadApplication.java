package org.bibhu.multiThreading.pcThread;

public class PcThreadApplication {
    public static void main(String[] args) {
        SharedResources sharedResources = new SharedResources();

        Thread producerThread1 = new Thread(new ProducerTask(sharedResources), "Producer-1");
        Thread consumerThread1 = new Thread(new ConsumerTask(sharedResources), "Consumer-1");

        producerThread1.start();
        consumerThread1.start();
    }
}
