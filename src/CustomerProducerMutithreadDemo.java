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
        // TODO 自动生成的方法存根
        Res2 r = new Res2();

        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);

        Thread t1 = new Thread(pro);
        Thread t3 = new Thread(pro);
        Thread t2 = new Thread(con);
        Thread t4 = new Thread(con);

        t1.start();
        t3.start();
        t2.start();
        t4.start();
    }

}