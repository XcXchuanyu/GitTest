package LambdaAndStream.Lambda;

import org.junit.Test;

import java.util.stream.Stream;

public class Supplier {
    @Test
    public void Supply() {
//      generate代表了Stream接口的静态方法，Suppier<T>是SAM接口
//    Stream<Double> stream = Stream.generate(() -> {return Math.random();});
    Stream<Double> stream = Stream.generate(() ->  Math.random());
    /*
    Consumer<T>消费型接口
    （1）抽象方法
        void accept

     */
    stream.forEach(num -> System.out.println(num));
};

}
