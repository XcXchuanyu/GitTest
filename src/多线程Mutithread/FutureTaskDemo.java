package 多线程Mutithread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) throws Exception{
        FutureTask<Integer> ft = new FutureTask<Integer>(new MyThread3());
        new Thread(ft,"First").start();

        System.out.println(Thread.currentThread().getName()+"...main");

        Integer result = ft.get();//如果计算没有完成，就会阻塞get方法

        System.out.println(result+"....result");
    }
}

class MyThread3 implements Callable<Integer>{
    public Integer call() throws Exception{
        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+"......");
        return 200;
    }
}

