package 集合collection;
import java.util.*;
/*
HashMap综合运用，并且介绍了keySet和entrySet两种取出数据的方式
 */

public class HashMapTest {
    public static void main(String[] args){
        HashMap<Worker,String> hm=  new HashMap<Worker,String>();

        hm.put(new Worker("xiaoli",20),"shanghai");
        hm.put(new Worker("xiaohua",1),"tokyo");
        hm.put(new Worker("xiaoshen",222),"bankok");
        hm.put(new Worker("xiaotang",123),"singapore");

        //第一种取出方式，keyset
        Set<Worker> keyset = hm.keySet();
        Iterator<Worker> it = keyset.iterator();
        while (it.hasNext()){
            Worker worker = it.next();
            String adress = hm.get(worker);
            System.out.println(worker.getAge()+"..."+worker.getName()+"..."+adress);
        }
        //第二种取出方式，entrySet
        Set<Map.Entry<Worker,String>> entrySet = hm.entrySet();
        Iterator<Map.Entry<Worker,String>> iter = entrySet.iterator();
        while (iter.hasNext()){
            Map.Entry<Worker,String> me = iter.next();
            Worker worker = me.getKey();
            String adress = me.getValue();
            System.out.println(worker.getAge()+"+++"+worker.getName()+"+++"+adress);
        }


    }
}

class Worker implements Comparable<Worker>{
    private String name;
    private int age;

    Worker(String name,int age){
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Worker w){
        int num = new Integer(this.age).compareTo(new Integer(w.age));
        if (num == 0)
            return this.name.compareTo(w.name);
        return num;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker))
            throw new ClassCastException("类型不匹配");
        Worker worker = (Worker) o;
        return age == worker.age &&
                Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return age;
    }
}