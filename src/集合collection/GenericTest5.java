package 集合collection;
/*
泛型限定
 */

import java.util.ArrayList;
import java.util.Iterator;

public class GenericTest5 {
    public static void main(String[] args){
        ArrayList<Car> al = new ArrayList<Car>();

        al.add(new Car(233));

/*
        ArrayList<Integer> al1 = new ArrayList<Integer>();

        al1.add(1);
        al1.add(2);
        al1.add(3);
 */
        printCollection(al);

    }
    public static void printCollection(ArrayList<? extends TransportUtilities> al){
        //问号是指不明确类型，作为占位符，如果这里用T来代替，可以对T对象进行操作
        //? extends E 可以接受E类型或E的子类，上限定
        //？ super E 可以接受E类型或E的父类，下限定
        Iterator<? extends TransportUtilities> it = al.iterator();
        while(it.hasNext()){
            System.out.println(it.next().getObject().toString());
        }
    }
}
