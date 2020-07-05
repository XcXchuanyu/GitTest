package 集合collection;
/*
设计泛型类
 */
class Car extends TransportUtilities{
    Car(int i){
        this.setObject(i);
    }
}

class TransportUtilities<QQ>{
    private QQ q;
    public void setObject(QQ q){
        this.q = q;
    }
    public QQ getObject(){
        return q;
    }

}
//泛型类的好处：省去了强制向下转型，让定义更强
public class GenericTest2 {
    public static void main(String[] args){
    TransportUtilities<Car> tu = new TransportUtilities<Car>();//下面只能传入car对象
    tu.setObject(new Car(1));
    Car c = tu.getObject();
    }
}
