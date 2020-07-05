package 集合collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.*;

/*
Map
 --HashTable:底层是哈希表数据结构，不可以存入null键与null值，该集合线程同步，jdk1.0，效率低
 --HashMap：底层时哈希表数据结构，可以存入null键与null值，线程不同步，jdk1.2，效率高
 --TreeMap：底层是二叉树，线程不同步，可以用于给map集合中的键进行排序。

 Set的底层也是使用Map

 判断containskey()、containsvalue()
 获取get()--可以通过get方法返回值判断键是否存在
 values()
 put()存入数据，并可以返回这个键对应的原来的值

Map取出数据的方法
 keySet:将map中的所有键存入到Set集合中。使用迭代器将set中的元素取出
 entrySet:将map集合中的映射关系存入到set集合中，而这个关系的数据关系就是map.entry
 */
public class MapTest {
    public static void main(String[] args){
        Map<String,String> map = new HashMap<String,String>();

        map.put("02","asan");
        map.put("03","asan01");
        map.put("04","asan02");

        Set<String> keySet = map.keySet();
        //获取所有键组成的Set
        Iterator<String> it = keySet.iterator();

        while(it.hasNext()){
            String key = it.next();
            String value = map.get(key);
            System.out.println("key:"+key+"--value:"+value);
        }

    }
}
