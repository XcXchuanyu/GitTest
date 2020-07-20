package 多线程Mutithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
c1结束唤醒c2，c2结束唤醒c3。
c1控制打印5次，c2控制打印10次，c3控制打印控制15次

 */
class ShareResource {
    private int number = 1;//1:A;2:b;3:C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int totalloopnumber) {
        lock.lock();
        try{
            while (number != 1) {
                c1.await();
            }
            for (int i = 1;i <= 5;i++){
                System.out.println(Thread.currentThread().getName()+"...."+i+"...."+totalloopnumber);
            }
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int totalloopnumber) {
        lock.lock();
        try{
            while (number != 2) {
                c2.await();
            }
            for (int i = 1;i <= 10;i++){
                System.out.println(Thread.currentThread().getName()+"...."+i+"...."+totalloopnumber);
            }
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int totalloopnumber) {
        lock.lock();
        try{
            while (number != 3) {
                c3.await();
            }
            for (int i = 1;i <= 15;i++){
                System.out.println(Thread.currentThread().getName()+"...."+i+"...."+totalloopnumber);
            }
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}



public class MultiThreadCommuniationDemo {
    public static void main(String[] args){
        ShareResource sr = new ShareResource();

        new Thread(() -> {
            for (int i = 1;i <= 10;i++){
                sr.print5(i);
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1;i <= 10;i++){
                sr.print10(i);
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 1;i <= 10;i++){
                sr.print15(i);
            }
        },"CC").start();
    }
}
