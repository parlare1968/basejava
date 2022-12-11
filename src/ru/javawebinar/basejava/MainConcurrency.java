package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private volatile int counter;

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);

        // реализация deadlock
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {

                }
            }
            System.out.println(Thread.currentThread().getName() + " finish");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock2) {
                synchronized (lock1) {

                }
            }
            System.out.println(Thread.currentThread().getName() + " finish");
        });
        thread1.start();
        thread2.start();
    }

    private synchronized void inc() {
        counter++;
        ////double a = Math.sin(13.);
        //synchronized (this) {
        //    counter++;
        //    //wait();
        //}
    }
}
