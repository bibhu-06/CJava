package org.bibhu.multiThreading.pcThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerTask implements Runnable {
    private final SharedResources sharedResources;

    public ProducerTask(SharedResources sharedResources) {
        this.sharedResources = sharedResources;
    }

    @Override
    public void run() {
        log.info("Producer thread started: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sharedResources.addItem();
    }
}
