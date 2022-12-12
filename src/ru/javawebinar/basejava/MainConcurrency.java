package ru.javawebinar.basejava;

import sun.nio.ch.SelectorImpl;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private volatile int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();

    //private static final Object lock = new Object();
    private static final Lock lock = new ReentrantLock();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

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
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();

        //List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            //Thread thread = new Thread(() -> {
            Future<Integer> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
                return 5;
            });
            //thread.start();
            //threads.add(thread);
        }
        //threads.forEach(t -> {
        //    try {
        //        t.join();
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //});
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        //System.out.println(mainConcurrency.counter);
        System.out.println(mainConcurrency.atomicCounter.get());
    }

    private void inc() {
        atomicCounter.incrementAndGet();
        //lock.lock();
        //try {
        //    counter++;
        //} finally {
        //    lock.unlock();
        //}
        ////double a = Math.sin(13.);
        //synchronized (this) {
        //    counter++;
        //    //wait();
        //}
    }
}
