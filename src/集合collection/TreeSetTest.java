package 集合collection;
/*
    TreeSet可以对元素进行排序。
    底层数据结构是二叉树（也叫红黑树）
    当compareTo（）return0时，元素将不会加入容器中

    TreeSet排序第一种方法：让需要储存的对象实现Compareable接口，overwrite compareTo()

    TreeSet排序第二种方法：当元素自身没有比较性时，创建一个对象继承Comparator对象，在比较器中return值
 */

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args){
        TreeSet ts = new TreeSet();

        ts.add(new Student("xiaohua",113));
        ts.add(new Student("xiaoli",20));
        ts.add(new Student("xiaoxia",4));

        Iterator it = ts.iterator();
        while(it.hasNext()){
            Student stu = (Student)it.next();
            System.out.println(stu.getName()+"...."+stu.getAge());
        }
    }
}
//继承Comparable接口，TreeSet需要要让学生对象拥有比较性
class Student implements Comparable{
    private String name;
    private int age;
    Student(String name, int age){
        this.name = name;
        this.age = age;
    }
    public int compareTo(Object obj){
        if (!(obj instanceof Student))
            throw new RuntimeException("不是Student");
        Student stu = (Student) obj;

        return this.age-stu.age;
        /*
        正数代表大于，负数代表小于，compareTo方法要求返回int数值
        等于0会被TreeSet当作同一个对象，不被记录
         */

    }

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }

    }