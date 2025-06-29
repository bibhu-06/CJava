package org.bibhu;

import lombok.extern.slf4j.Slf4j;
import org.bibhu.multiThreading.MultiThreading;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Thread started: " + Thread.currentThread().getName());
        MultiThreading multiThreading = new MultiThreading();
        Thread thread = new Thread(multiThreading);
        thread.start();
    }
}