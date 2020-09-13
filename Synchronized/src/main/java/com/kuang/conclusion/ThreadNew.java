package com.kuang.conclusion;

import java.util.concurrent.*;

//回顾总线程的创建
public class ThreadNew {
    public static void main(String[] args) {
        //方法1 继承Thread
        new MyThread1().start();

        //方法2 Runnable接口
        new Thread(new MyThread1()).start();

        //方法3 callable接口
        //3.1 用runnable 实现
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread3());
//        new Thread(futureTask).start();

        //3.2 通过线程池实现
        MyThread3 myThread3 = new MyThread3();
        ExecutorService ser = Executors.newFixedThreadPool(2);
        Future<Integer> result = ser.submit(myThread3);
        try {
//            Integer integer = futureTask.get();
            Integer integer = result.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            ser.shutdown();//关闭服务
        }
    }
}

//1.继承Thread类
class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("Mythread1");
    }
}

//2.实现Runnable接口
class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Mythread2");

    }
}

//3.显示callable接口
class MyThread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("MyThread3");
        return 100;
    }
}