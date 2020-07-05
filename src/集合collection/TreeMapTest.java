package 集合collection;
import java.util.*;
/*
TreeMap小练习，目标：计算字符串中每一个字母的个数，以指定形式返回a(2)b(3)...
 */
public class TreeMapTest {
    public static void main(String[] args){
        String s = charCount("asfaiuqwnadyahacbcbci");
        System.out.println(s);
    }

    public static String charCount(String str){
        char[] chars = str.toCharArray();
        TreeMap<Character,Integer> tm = new TreeMap<Character, Integer>();

        for (int x=0; x<chars.length; x++){
            if(!(chars[x]>='a'&&chars[x]<='z'||chars[x]>='A'&&chars[x]<='Z'))
                //此处增加一组判断，让不在a~z、A~Z的字符被舍弃
                continue;
            Integer value = tm.get(chars[x]);

            if(value==null)
                tm.put(chars[x],1);
            else {
                value+=1;
                tm.put(chars[x],value);
            }

        }
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Character,Integer>> entryset = tm.entrySet();
        Iterator<Map.Entry<Character,Integer>> it = entryset.iterator();

        while(it.hasNext()){
            Map.Entry<Character,Integer> me = it.next();
            Character ch = me.getKey();
            Integer times = me.getValue();
            sb.append(ch+"("+times+")");
        }
        return sb.toString();
    }
}
