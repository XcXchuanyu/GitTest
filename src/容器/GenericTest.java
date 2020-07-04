package 容器;
/*
泛型：jdk1.5后出现的新特性

好处：
1.将运行时期出现问题ClassCastException，转移到了编译时期，方便立即发现问题，保证安全
2.避免强制转换

通常在集合框架中很常见，只要有<>就要定义泛型
 */

import java.util.ArrayList;
import java.util.Iterator;

public class GenericTest {
    public static void main(String[] args){
        ArrayList<String> al = new ArrayList<String>();

        al.add("asd");
        al.add("abc123");
        al.add("abc125");
//        al.add(4);无法通过编译


        Iterator<String> it = al.iterator();
        while(it.hasNext()){
//            String s = (String)it.next();不需要强制转换了
            String s = it.next();
            System.out.println(s+":"+s.length());
        }
    }
}
