package 多线程Mutithread;

import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
写一个字并由100个线程轮流读取，期间线程锁保证了正常读取
 */
class MyQueue{
    private Object obj;
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void readObj(){
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"...read..."+obj);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
          rwLock.readLock().unlock();
        }
    }

    public void writeObj(Object obj){
        rwLock.writeLock().lock();
        try {
            this.obj = obj;
            System.out.println(Thread.currentThread().getName()+"...write..."+obj);
            } catch (Exception e) {
            e.printStackTrace();
            } finally {
            rwLock.writeLock().unlock();
            }
        }
}

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args){
        MyQueue mq = new MyQueue();

        new Thread( () -> {
            mq.writeObj("Class1122");
        },"AA").start();

        for (int i = 1;i <= 100;i++) {
            new Thread( () -> {
                mq.readObj();
            },String.valueOf(i)).start();
        }
    }
}
