package 多线程Mutithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
功能：创建ShareData,内置increment和decrement方法，将sharedata中的数字加一或减一

 */
public class LockandConditionDemo {
    public static void main(String[] args) {
        ShareData sd = new ShareData();
//通过lambda表达书创建四个线程
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class ShareData {
    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {    //判断
            while (number != 0) {
                condition.await();
            }
            //干活
            ++number;
            System.out.println(Thread.currentThread().getName() + '\t' + number);
            //通知
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            --number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/*
lock、condition的使用
 */
class OfficialAPI {
    final Lock lock = new ReentrantLock();//重入锁
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
                //让生产线程等待
            }
            items[putptr] = x;
            //putptr应该是代表存放的指针
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
            //唤醒消费线程
        } finally {
            lock.unlock();
        }
    }
}