package 容器;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest2 {
    public static void main(String[] arg){
        TreeSet ts = new TreeSet(new MyComparetor());

        ts.add(new Student("xiaoli05",4));
        ts.add(new Student("xiaoli02",4));
        ts.add(new Student("xiaoli02",24));

        Iterator it = ts.iterator();
        while(it.hasNext()){
            Student stu = (Student)it.next();
            System.out.println(stu.getName()+"..."+stu.getAge());
        }

    }
}

class MyComparetor implements Comparator{
//    先根据姓名比较再根据年龄排序
    public int compare(Object obj1,Object obj2){
        Student s1 = (Student) obj1;
        Student s2 = (Student) obj2;

        int num = s1.getName().compareTo(s2.getName());
        if(num == 0)
            return new Integer(s1.getAge()).compareTo(new Integer(s2.getAge()));
        return num;
    }
}