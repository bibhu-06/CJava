package org.bibhu.multiThreading.monitorLock;

import org.bibhu.multiThreading.monitorLock.MonitorLock;

public class MonitorLockApplication {
    public static void main(String[] args) {
        MonitorLock monitorLock = new MonitorLock();
        Thread thread1 = new Thread(monitorLock::method1);
        Thread thread2 = new Thread(monitorLock::method2);
        Thread thread3 = new Thread(monitorLock::method3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
