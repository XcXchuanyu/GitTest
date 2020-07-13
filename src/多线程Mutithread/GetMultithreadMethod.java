package 多线程Mutithread;
/*
获得多线程的几种方法
继承Thread类
runnable接口
callable接口
java线程池
 */

import java.util.concurrent.Callable;

public class GetMultithreadMethod {
    public static void main(String[] args){

    }
}

//Callable接口会抛出异常
class MyThread2 implements Callable<Integer>{
    public Integer call() throws Exception {
        return 100;
    }
}


