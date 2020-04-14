package org.creationalpattern;

public class SingletonDemo {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);

        SingletonLazilyLoaded instance2 = SingletonLazilyLoaded.getInstance();
        System.out.println(instance2);
    }
}
