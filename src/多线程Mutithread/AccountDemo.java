package 多线程Mutithread;
/*
展示脏读问题（DirtyRead）
 */
/*
synchronized可不可重入的问题，synchronized是可以重入的
synchronized关键字调用的就是synchronized(this)
JDK1.2之前，使用的是重量级锁（经过操作系统管理），效率极低，后面慢慢升级为轻量级锁

程序出现异常时，线程会自动释放锁。
 */
import java.util.concurrent.TimeUnit;

public class AccountDemo {
    public static void main(String[] args){
        Account a = new Account();
        new Thread(()->a.set("zhangsan",100)).start();
        //？不理解这里TimeUnit。Second.Sleep是什么作用
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //getBalance是没有锁的，因此不需要等set方法执行完毕
        System.out.println(a.getBalance("zhangsan"));

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));
    }
}

class Account{
    String name;
    double balance;

    public synchronized void set(String name,double balance){
        this.name = name;
//      先设置账户名称
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
//      两秒钟后设置账户的余额
        this.balance = balance;
    }
    public double getBalance(String name){return this.balance;}
}