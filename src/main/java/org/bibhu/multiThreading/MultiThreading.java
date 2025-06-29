package org.bibhu.multiThreading;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

@Slf4j
public class MultiThreading implements Runnable {

//    private static final Logger logger = Logger.getLogger(MultiThreading.class.getName());
    @Override
    public void run() {
        log.info("Thread started: " + Thread.currentThread().getName());
    }
}
