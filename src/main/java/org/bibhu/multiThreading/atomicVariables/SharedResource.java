package org.bibhu.multiThreading.atomicVariables;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResource {
    // CAS - Compare and Swap
    // Atomic Variables are mutable and thread-safe
    // Atomic Variables use low-level atomic CPU instructions
    // Atomic Variables are used in high-concurrency scenarios
    // Lock-free and non-blocking algorithms

    private AtomicInteger counter = new AtomicInteger();

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Runnable task1 = () -> {
            for (int i = 0; i < 200; i++) {
                sharedResource.increment();
            }
        };
        Runnable task2 = () -> {
            for (int i = 0; i < 200; i++) {
                sharedResource.increment();
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + sharedResource.getCounter());
    }
}
