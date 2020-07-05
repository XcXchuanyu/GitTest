package 集合collection;
/*
泛型定义在接口上
 */
interface Inter<T>{
    void show(T t);
}

class InterUtils<T> implements Inter<T>{
    public void show(T t){
        System.out.println("show.."+t);
    }
}
public class GenericTest4 {
    public static void main(String[] args){
        InterUtils<Integer> iu = new InterUtils<Integer>();
        iu.show(4);
    }
}
