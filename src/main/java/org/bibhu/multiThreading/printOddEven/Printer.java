package org.bibhu.multiThreading.printOddEven;

public class Printer {
    private final Object lock = new Object();
    private int count = 1;
    private final int MAX = 10;

    public void printEven() {
        synchronized (lock) {
            while(count < MAX) {
                if(count%2 == 0) {
                    System.out.println(count + " ");
                    count++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void printOdd() {
        synchronized (lock) {
            while(count < MAX) {
                if(count%2 == 1) {
                    System.out.println(count + " ");
                    count++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread t1 = new Thread(printer::printOdd);
        Thread t2 = new Thread(printer::printEven);

        t1.start();
        t2.start();
    }
}
