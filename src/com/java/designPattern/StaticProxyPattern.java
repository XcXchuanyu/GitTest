package com.java.designPattern;

import java.util.Calendar;
import java.util.GregorianCalendar;

interface AbstractUSERDAO2 {
    public Boolean findUserById(String userId);
}

class BossDao implements AbstractUSERDAO2 {
    @Override
    public Boolean findUserById(String userId) {
        System.out.println("登陆成功");
        return true;
    }
}

class EmployeeDao implements AbstractUSERDAO2 {
    @Override
    public Boolean findUserById(String userId) {
        System.out.println("登陆失败");
        return true;
    }
}
class Daoproxy implements AbstractUSERDAO2{
    private AbstractUSERDAO2 dao;

    Daoproxy(AbstractUSERDAO2 dao) {
        this.dao = dao;
    }

    @Override
    public Boolean findUserById(String userId) {
        beforeinvoke();
        dao.findUserById(userId);
        return null;

    }
    private void beforeinvoke() {
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String time = hour + ":" + minute + ":" + second;
        System.out.println("调用时间" + time);
    }
}



public class StaticProxyPattern {
    public static void main(String[] args) {
        AbstractUSERDAO2 dao = new Daoproxy(new BossDao());
        dao.findUserById("a");
        AbstractUSERDAO2 dao2;
        dao2 = new Daoproxy(new EmployeeDao());
        dao2.findUserById("a");
    }

}
