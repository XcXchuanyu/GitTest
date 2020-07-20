package 多线程Mutithread;

import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class newProducer implements Runnable{
    private  volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;//内存缓冲区
    private static AtomicInteger count =new AtomicInteger();//总数，原子操作
    private static final  int SLEEPTIME = 1000;

    public newProducer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        PCData data = null;
        Random r = new Random();

        System.out.println("Start producer id="+Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println(data+"has put into queue");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("failed:"+data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    public void stop(){
        isRunning = false;
    }
}

final class PCData{
    private final int intData;
    public PCData(int d){
        intData = d;
    }
    public int getData() {
        return intData;
    }
    @Override
    public String toString() {
        return "data:"+intData;
    }
}


class newConsumer implements Runnable{
    private BlockingQueue<PCData> queue;//内存缓冲区
    private static final  int SLEEPTIME = 1000;

    public newConsumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        Random r = new Random();

        System.out.println("Start Consumer id="+Thread.currentThread().getId());
        try {
            while (true) {
                PCData data = queue.take();
                if (null != data) {
                    int re = data.getData() * data.getData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}", data.getData(), data.getData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}


public class CustomerProducerMutithread2Demo {
    public static  void main(String[] args) throws InterruptedException{
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<PCData>(10);
        newProducer p1 = new newProducer(queue);
        newProducer p2 = new newProducer(queue);
        newProducer p3 = new newProducer(queue);
        newConsumer c1 = new newConsumer(queue);
        newConsumer c2 = new newConsumer(queue);
        newConsumer c3 = new newConsumer(queue);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);

        Thread.sleep(10*1000);

        p1.stop();
        p2.stop();
        p3.stop();
        Thread.sleep(3000);
        service.shutdown();
    }
}
