package org.creationalpattern;

public class SingletonThreadSafe {

    /* notice the volatile for thread safety */
    private static volatile SingletonThreadSafe instance;

    private SingletonThreadSafe() {
        /* protection vs reflection class */
        if(instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    public static SingletonThreadSafe getInstance() {
        if (instance == null) {
            /* place synchronized block here instead of whole method */
            synchronized (SingletonThreadSafe.class) {
                if(instance == null) {
                    instance = new SingletonThreadSafe();
                }
            }
        }

        return instance;
    }
}
