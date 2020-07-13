package 多线程Mutithread;

public class TreadStateDemo {
    public static void main(String[] args){
        Calculate c = new Calculate();
        Thread t = new Thread(c);

        System.out.println(t.getState());
        //New

        t.start();
        System.out.println(t.getState());
        //Runnable

        try {
            t.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(t.getState());
        //Terminated
    }
}

class Calculate implements Runnable{
    private int i = 0;
    public void run(){
        System.out.println(i++);
    }
}
