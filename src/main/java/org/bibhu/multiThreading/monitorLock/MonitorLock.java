package org.bibhu.multiThreading.monitorLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MonitorLock {
    //Monitor lock is per object, so if multiple threads are trying to access synchronized methods of the same object,
    // they will be blocked until the lock is released.
    public synchronized void method1() {
        log.info("Inside Method 1 started by " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            log.error("Thread interrupted: " + e.getMessage(), e);
        }
        log.info("Inside Method 1 ended by " + Thread.currentThread().getName());
    }

    public void method2() {
        log.info("Inside Method 2 before synchronized started by " + Thread.currentThread().getName());
        synchronized (this) {
            log.info("Inside Method 2 after synchronized ended by " + Thread.currentThread().getName());
        }
        log.info("Inside Method 2 ended by " + Thread.currentThread().getName());
    }

    public void method3() {
        log.info("Inside Method 3 started by " + Thread.currentThread().getName());
    }
}
