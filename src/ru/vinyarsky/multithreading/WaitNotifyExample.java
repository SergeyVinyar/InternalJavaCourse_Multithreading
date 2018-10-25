package ru.vinyarsky.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyExample {

    final Queue<Integer> mQueue = new LinkedList<>();
    final Random mRandom = new Random();

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyExample waitNotifyExample = new WaitNotifyExample();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(waitNotifyExample::supplier);
        executorService.submit(waitNotifyExample::consumer);
        executorService.submit(waitNotifyExample::consumer);

        Thread.sleep(100);
        //Thread.sleep(10000);
        executorService.shutdownNow();
    }

    synchronized private void supplier() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                while (!mQueue.isEmpty()) {
                    System.out.println("supplier: queue is not empty, waiting...\n");
                    wait();
                }
                System.out.println("supplier: adding element to queue");
                mQueue.add(mRandom.nextInt());
                System.out.println("supplier: queue size is now " + mQueue.size());
                System.out.println("supplier: notifyAll\n");
                notifyAll();
//                System.out.println("supplier: notify\n");
//                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized private void consumer() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                while (mQueue.isEmpty()) {
                    System.out.println("consumer: queue is empty, waiting...\n");
                    wait();
                }
                System.out.println("consumer: polling element from queue");
                mQueue.poll();
                System.out.println("consumer: queue size is now " + mQueue.size());
                System.out.println("consumer: notifyAll\n");
                notifyAll();
//                System.out.println("consumer: notify\n");
//                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
