package com.java.designPattern;
/*
动态代理模式

DAO(Data Access Object)是一个数据访问接口，数据访问：顾名思义就是与数据库打交道。夹在业务逻辑与数据库资源中间。
DAO模式是标准的J2EE设计模式之一.开发人员使用这个模式把底层的数据访问操作和上层的商务逻辑分开.一个典型的DAO实现有下列几个组件：
1. 一个DAO工厂类；
2. 一个DAO接口；
3. 一个实现DAO接口的具体类；
4. 数据传递对象（有些时候叫做值对象）.
具体的DAO类包含了从特定的数据源访问数据的逻辑。在下面的这段中你将学到设计和实现数据访问对象的技术。
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Calendar;
import java.util.GregorianCalendar;

interface AbstractUSERDAO {
    public Boolean findUserById(String userId);
}

interface AbstarctDocumentDAO {
    public Boolean deleteById(String DocId);
}

class UserDao implements AbstractUSERDAO {
    @Override
    public Boolean findUserById(String userId) {
        System.out.println("登陆成功");
        return true;
    }
}

class DocDao implements AbstarctDocumentDAO {

    @Override
    public Boolean deleteById(String DocId) {
        System.out.println("删除成功");
        return true;
    }
}

//DAOLogHandler就是我们的代理，用来帮助前面两个类增加记录时间的功能。
class DAOLogHandler implements InvocationHandler {
    private Calendar calendar;
    private Object object;

    public DAOLogHandler() {
    }

    public DAOLogHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeinvoke();
        Object result = method.invoke(object, args);
        afterinvoke();
        return null;
    }

    private void afterinvoke() {
        System.out.println("调用结束");
    }

    //记录时间
    private void beforeinvoke() {
        calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String time = hour + ":" + minute + ":" + second;
        System.out.println("调用时间" + time);
    }
}

public class DynamicProxyPattern {
    public static void main(String[] args) {
        InvocationHandler handler = null;

        AbstractUSERDAO useDao = new UserDao();
        handler = new DAOLogHandler(useDao);

        AbstractUSERDAO proxy = (AbstractUSERDAO) Proxy.newProxyInstance(AbstractUSERDAO.class.getClassLoader(), new Class[]{AbstractUSERDAO.class}, handler);

        proxy.findUserById("xia");
        System.out.println("----------------------------------------------------------------");
        AbstarctDocumentDAO docDao = new DocDao();
        handler = new DAOLogHandler(docDao);

        AbstarctDocumentDAO proxy_2 = (AbstarctDocumentDAO) Proxy.newProxyInstance(AbstarctDocumentDAO.class.getClassLoader(), new Class[]{AbstarctDocumentDAO.class}, handler);
        proxy_2.deleteById("11");

    }
}
