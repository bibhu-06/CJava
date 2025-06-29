package org.bibhu.multiThreading.pcqThread;

public class PcqThreadApplication {

    public static void main(String[] args) {
        SharedResources sharedResources = new SharedResources(3);

        Thread producerThread1 = new Thread(() -> {
            try {
                for(int i =1; i <= 6; i++) {
                    System.out.println("Producer thread started: " + Thread.currentThread().getName() + " - producing item " + i);
                    sharedResources.produceItem(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread consumerThread1 = new Thread(() -> {
            try {
                for(int i =1; i <= 6; i++) {
                    System.out.println("Consumer thread started: " + Thread.currentThread().getName() + " - consuming item " + i);
                    sharedResources.consumeItem();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        producerThread1.start();
        consumerThread1.start();
    }
}
