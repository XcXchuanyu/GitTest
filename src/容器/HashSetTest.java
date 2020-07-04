package 容器;

import java.util.HashSet;
import java.util.Iterator;
/*
* HashSet是通过hashCode()与equals()来保证元素唯一性的
* 如果元素的hashCode值相同，才会调用equals判断是否为true
*
* */


public class HashSetTest {
    public static void main(String[] arg){
        HashSet hs = new HashSet();

        hs.add(new Person("xiaohua",11));
        hs.add(new Person("xiaowu",12));
        hs.add(new Person("xiaoli",13));
        hs.add(new Person("xiaohua",11));

        Iterator it = hs.iterator();

        while(it.hasNext()) {
            Person p = (Person) it.next();
            System.out.println(p.getName() + "...." + p.getAge());
        }
    }
}

class Person{
    private String name;
    private int age;
    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }

//    如果要让HashSet检查重复元素，需要重写hashcode方法与equals方法
    public int hashCode(){
        return name.hashCode()+age;
    }

    public boolean equals(Object obj){
        if (!(obj instanceof Person))
            return false;

        Person p = (Person) obj;
        return this.name.equals(p.name) && this.age == p.age;
    }

}