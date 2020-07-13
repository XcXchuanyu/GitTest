package 多线程Mutithread;
//第一是lambda表达式，()->{}
public class NewFeaturesinJava8 {
    public static void main(String[] args){
        Foo foo = (x,y)->{
          return x+y;
        };

        double result = foo.div(3,5);
        System.out.println(result);
    }
}
interface Foo{
    public int add(int x, int y);
//Default返回默认函数方法
    default double div(int x, int y){
        return (x+0.0)/y;
    }

    public static int sub(int x, int y){
        return x-y;
    }
}