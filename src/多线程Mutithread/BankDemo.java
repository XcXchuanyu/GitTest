package 多线程Mutithread;
/*
Synchronized关键字、Synchronized()是指同步锁的意思，它可以用任意对象作为锁

*/


/*sleep()与wait()的不同
    1.sleep在Thread类中，wait在Object中
    2.sleep不释放lock，wait会释放
    3.sleep不依赖同步器，wait需要synchronized
    4.sleep不需要被唤醒，wait需要

 */
class Bank{
    private int sum;
    Object obj = new Object();
    public void add(int n) {
        synchronized(obj) {
            //最方便做法可以是synchronized(this)，锁定当前对象
            //对于静态方法，synchronized是this.class文件
            sum = sum+n;
            try{Thread.sleep(10);}catch(Exception e){};
            System.out.println("sum"+sum);
        }
    }
}

class Cus implements Runnable
{
    private Bank b = new Bank();
    public void run() {
        for (int x = 0;x<3;x++) {
            b.add(100);
        }
    }
}

public class BankDemo {

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        Cus c = new Cus();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }

}
