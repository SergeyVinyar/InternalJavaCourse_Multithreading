package ru.vinyarsky.multithreading;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
//        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
//            System.out.println("No crashes please at " + thread + ": " + throwable);
//        });

        Thread thread1 = new MyThread("Thread 1");
        Thread thread2 = new MyThread("Thread 2");
        Thread thread3 = new MyThread("Thread 3");
        Thread thread4 = new MyThread("Thread 4");
        Thread thread5 = new MyThread("Thread 5");

        System.out.println("thread1 start");
        thread1.start();

        System.out.println("thread2 start");
        //thread2.setPriority(10);
        thread2.start();

        System.out.println("thread3 start");
        thread3.start();

        System.out.println("thread4 start");
        thread4.start();

        System.out.println("thread5 start");
        thread5.start();

//        thread2.interrupt();
//        System.out.println("thread2 interrupted");

//        thread1.join();
//        thread2.join();
//        thread3.join();
//        thread4.join();
//        thread5.join();

        System.out.println("main ended");
    }
}

class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
        //setDaemon(true);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (isInterrupted()) {
                break;
            }
            System.out.println(getName() + ": i = " + i);
            //throw new RuntimeException(getName() + ": Yeah, it happens sometimes...");
        }
    }
}
