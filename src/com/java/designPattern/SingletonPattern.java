package com.java.designPattern;

public class SingletonPattern {

    public static void main(String[] args) {

    }
}

class LazyInitializedSingleton{
    /**
     * 懒汉式在调用get方法时才generate instance
     */
    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton(){}

    public static LazyInitializedSingleton getInstance() {
        if (instance == null) instance = new LazyInitializedSingleton();
        return instance;
    }
}

class EagerInitializedSingleton {
    /**
     * 饿汉式一开始就生成instance
     */
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    //private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton(){}

    public static EagerInitializedSingleton getInstance(){
        return instance;
    }
}

class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){}

    /**
     * 在lazysingleton基础上增加synchronized关键字
     * @return
     */
    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance == null){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}



