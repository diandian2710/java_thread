package com.kuang.demo01;

//The other way to create a thread is to declare a class that implements the Runnable interface. That class then implements the run method.
// An instance of the class can then be allocated, passed as an argument when creating Thread, and started
public class TestThread03 implements Runnable{
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("我在看代码");
        }
    }

    public static void main(String[] args) {
//        TestThread03 testThread03 = new TestThread03();
//        new Thread(testThread03).start();
        new Thread(new TestThread03()).start();
        for (int i = 0; i < 400; i++) {
            System.out.println("我在学习多线程h");
        }
    }
}
