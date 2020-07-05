package 集合collection;
/*
自建了比较器，并且使用自定义比较器来完成二分查找
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class binarySearchDemo {
    public static void main(String[] args){
        List<String> ls = new ArrayList<String>();
        ls.add("cccc");
        ls.add("aa");
        ls.add("acbasdfsa");
        ls.add("bbb");

        StrLengthComparetor slc = new StrLengthComparetor();
        Collections.sort(ls,slc);
        System.out.println(ls);
        System.out.println(binarySearchDemo.halfsearch(ls,"ccc",slc));
    }
    public static int halfsearch(List<String> list, String key, Comparator<String> cmp){
        int max,min,mid;
        max = list.size()-1;
        min = 0;

        while(min<max){
            mid =(max+min)>>1;
            String str = list.get(mid);

            int num = cmp.compare(str,key);
            if (num>0)
                max = mid-1;
            else if(num<0)
                min = mid+1;
            else
                return mid;
        }

        return -min-1;
    }
}
class StrLengthComparetor implements Comparator<String>{
    public int compare(String s1,String s2){
        return s1.length()-s2.length();
    }
}
