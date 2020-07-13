package 多线程Mutithread;

public class ThreeMethodStartThread {
    public static void main(String[] args){
        Thread t1 = new Thread(new MyThread());
        t1.start();
//方法二：匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
//方法三：lambda
        new Thread(()->{
        }).start();
    }
}

//方法一：runnable
class MyThread implements Runnable
{
    @Override
    public void run() {
    }
}
