package org.creationalpattern;

public class SingletonLazilyLoaded {
    private static SingletonLazilyLoaded instance;

    private SingletonLazilyLoaded() {

    }

    public static SingletonLazilyLoaded getInstance() {
        /* Substantial performance improvement */
        if(instance == null) {
            instance = new SingletonLazilyLoaded();
        }

        return instance;
    }
}
