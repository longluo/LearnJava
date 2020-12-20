package com.callbacks.homework;

public class Student {

    public void doHomeWork(String homeWork) {
        System.out.println("作业本");
        if ("1+1=?".equals(homeWork)) {
            System.out.println("作业：" + homeWork + " 答案：" + "2");
        } else {
            System.out.println("作业：" + homeWork + " 答案：" + "不知道~~");
        }
    }

    public static void main(String[] args) {
        Student student = new Student();

        String aHomeWork = "1+1=?";
        student.doHomeWork(aHomeWork);
    }
}