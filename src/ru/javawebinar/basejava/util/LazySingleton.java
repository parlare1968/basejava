package ru.javawebinar.basejava.util;

public class LazySingleton {
    //int i;
    volatile private static LazySingleton INSTANCE;

    //double sin = Math.sin(13.);

    private LazySingleton() {
    }

    private static class LazySingletonHolder {
        static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
        //if (INSTANCE == null) {
        //    synchronized (LazySingleton.class) {
        //        if (INSTANCE == null) {
        //            INSTANCE = new LazySingleton();
        //        }
        //    }
        //}
        //return INSTANCE;
    }
}
