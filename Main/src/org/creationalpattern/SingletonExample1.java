package org.creationalpattern;

public class SingletonExample1 {
    private static SingletonExample1 instance;

    private SingletonExample1(){

    }

    public static SingletonExample1 getInstance() {
        if(instance == null) {
            instance = new SingletonExample1();
        }

        return instance;
    }
}
