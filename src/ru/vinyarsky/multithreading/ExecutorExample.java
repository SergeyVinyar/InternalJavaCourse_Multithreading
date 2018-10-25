package ru.vinyarsky.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorExample {

    private static ExecutorService mExecutorService;

    public static void main(String[] args) throws  InterruptedException, ExecutionException {
        mExecutorService = Executors.newSingleThreadExecutor();
//        mExecutorService = Executors.newFixedThreadPool(2);
//        mExecutorService = Executors.newFixedThreadPool(5);
//        mExecutorService = Executors.newCachedThreadPool();

        mExecutorService.submit(new MyRunnable("runnable1"));
        mExecutorService.submit(new MyRunnable("runnable2"));
        mExecutorService.submit(new MyRunnable("runnable3"));
        mExecutorService.submit(new MyRunnable("runnable4"));
        mExecutorService.submit(new MyRunnable("runnable5"));

//        Future<?> future1 = mExecutorService.submit(new MyRunnable("runnable1"));
//        Future<?> future2 = mExecutorService.submit(new MyRunnable("runnable2"));

//        mExecutorService.shutdownNow();
//        mExecutorService.shutdown();
//        future1.cancel(true);
//        future2.get();

//        mExecutorService.submit(new MyRunnable("runnable6"));
    }
}

class MyRunnable implements Runnable {

    private final String mName;

    MyRunnable(String name) {
        mName = name;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " (" + mName + "): interrupted");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " (" + mName + "): i = " + i);
        }
    }
}
