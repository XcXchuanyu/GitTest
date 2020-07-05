package 集合collection;
/*
泛型方法+泛型静态方法
 */

class PrintDemo<T>{
    //此时泛型类仅能作用到show方法上不能作用到print方法上
    public void show(T t){
        System.out.println("show:"+t);}
    public <Q> void print(Q q){
        System.out.println("print:"+q);
        }

    public static <T> void method(T t){
        System.out.println("print:"+t);
    }
    }

public class GenericTest3 {
    public static void main(String[] args){
        PrintDemo<String> pd = new PrintDemo<String>();
        pd.show("haha");
        pd.print(new Integer(4));//泛型定义在方法上时，数据类型可以跟着泛型方法走

        PrintDemo.method("heihei");
    }
}
