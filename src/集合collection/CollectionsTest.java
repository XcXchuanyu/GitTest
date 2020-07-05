package 集合collection;
/*
collections是Collection中的工具类，本例中尝试实现了sort/max/binarySearch使用，在包中创建binarySearchDemo方法
reverseorder可以逆序输出，reverse可以反转，replaceAll可以全部替换掉一个元素
 */
import java.util.ArrayList;
import java.util.Collections;

public class CollectionsTest {
    public static void main(String[] args){
           CollectionsTest ct = new CollectionsTest();
           ct.sortDemo();
    }
    public void sortDemo(){
     //使用collections中的Sort方法
        ArrayList<String> al = new ArrayList<String>();

        al.add("eee");
        al.add("abc");
        al.add("asdsd");
        al.add("aasd");
        al.add("aqwfg");
        al.add("agggg");
        al.add("asdsd");

        Collections.sort(al);
        Collections.reverse(al);
//        String max = Collections.max(al);max方法返回列表中的最大值
        int index = Collections.binarySearch(al,"abc");
//        二分查找：值的所在位置
        System.out.println(al);
        System.out.println(index);
    }

}
