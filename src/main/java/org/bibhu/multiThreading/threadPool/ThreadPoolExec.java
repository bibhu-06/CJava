package org.bibhu.multiThreading.threadPool;

import java.util.concurrent.*;

public class ThreadPoolExec {

    public void playWithThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        for(int i=0;i<5;i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(5000); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed on thread " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }

    public void playWithThreadPool1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        Future<?> futureObj = executor.submit(() -> {
            try {
                Thread.sleep(5000); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Task completed on thread " + Thread.currentThread().getName());
        });

        System.out.println("Is task done? " + futureObj.isDone());

        executor.shutdown();

        try {
            // Wait for the task to complete and get the result (null for Runnable)
            futureObj.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Is task done? " + futureObj.isDone());
        System.out.println("Is task cancelled? " + futureObj.isCancelled());
    }

    public void playWithThreadPool2() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "Task completed on thread " + Thread.currentThread().getName();
        }, executor).thenApply((String result) -> {
            return result + " - processed";
        }).thenCompose((String value) -> {
            return CompletableFuture.supplyAsync(() -> value + " - further processed", executor);
        }).thenAcceptAsync(System.out::println, executor);

        System.out.println("Is task done? " + completableFuture.isDone());

        executor.shutdown();

        try {
            // Wait for the task to complete and get the result (null for Runnable)
            completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Is task done? " + completableFuture.isDone());
    }

    public void playWithThreadPool3() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            return "Task1 completed on thread " + Thread.currentThread().getName();
        }, executor);

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            return "Task2 completed on thread " + Thread.currentThread().getName();
        }, executor);

        CompletableFuture<String> completableFuture = completableFuture1.thenCombineAsync(completableFuture2, (result1, result2) -> {
            return result1 + " & " + result2 + " - combined";
        }, executor);

        executor.shutdown();

        try {
            // Wait for the task to complete and get the result (null for Runnable)
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Is task done? " + completableFuture.isDone());
    }

    public static void main(String[] args) {
        ThreadPoolExec threadPoolExec = new ThreadPoolExec();
        threadPoolExec.playWithThreadPool3();
    }
}
