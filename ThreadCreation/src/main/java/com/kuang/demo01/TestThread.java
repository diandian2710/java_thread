package com.kuang.demo01;

//创建线程方式一:继承Thread类，充血run()方法，调用start开启线程
//总结：注意，线程开启不一定立即执行，由cpu调度执行
public class TestThread extends Thread{
    @Override
    public void run() {
       //Run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码----");
        }
    }

    public static void main(String[] args) {

       //main线程，住线程
        //创建一个线程对象
        TestThread testThread = new TestThread();
        //调用start()方法开启线程
        testThread.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程" + i);
        }
    }
}
