/*
这个例子中有三个主要对象：Res2（产品）、Producer（生产者）、Consumer（顾客）
例子原理：给Producer分配两个线程，用同步方法保证一次只有一个线程进入set方法，生产一个Res2；
同理，Consumer分配两个线程，一次只能消费一个Res2
flag作为是否有Res2的标志，当有Res2时，生产者的线程wait，消费者线程争夺资源，结束后notifyall唤醒所有线程；反之亦然。
 */
class Res2
{
    private String name;
    private int count = 1;
    private boolean flag;

    public synchronized void set(String name)
    {
        while(flag)
            try {wait();}catch(Exception e) {}
        this.name = name+"..."+count++;
        System.out.println(Thread.currentThread().getName()+"..生产者.."+this.name);
        flag = true;
        this.notifyAll();
    }

    public synchronized void out()
    {
        while(!flag)
            try {wait();}catch(Exception e) {}
        System.out.println(Thread.currentThread().getName()+"..消费者.."+this.name);
        flag = false;
        this.notifyAll();
    }
}

class Producer implements Runnable
{
    private Res2 r;
    Producer(Res2 r)
    {
        this.r = r;
    }
    public void run()
    {
        while(true)
        {
            r.set("thing1");

        }
    }
}

class Consumer implements Runnable
{
    private Res2 r;
    Consumer(Res2 r)
    {
        this.r = r;
    }
    public void run()
    {
        while(true)
        {
            r.out();
        }
    }
}


public class CustomerProducerMutithreadDemo{

    public static void main(String[] args) {
        Res2 r = new Res2();

        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);

        Thread t3 = new Thread(pro);
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(con);
        Thread t4 = new Thread(con);

        t1.start();
        t3.start();
        t2.start();
        t4.start();
    }

}