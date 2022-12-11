package ru.javawebinar.basejava;

public class DeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        makeDeadLock(lock1, lock2);
        makeDeadLock(lock2, lock1);
    }

    public static void makeDeadLock(Object lock1, Object lock2) {
        new Thread(() -> {
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
        }).start();
    }
}
