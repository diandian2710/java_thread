package com.kuang.syn;

import java.util.concurrent.locks.ReentrantLock;

//测试Lock锁
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();
        new Thread(testLock2, "1").start();
        new Thread(testLock2, "2").start();
        new Thread(testLock2, "3").start();
//        Thread thread1 = new Thread(testLock2, "1");
//        Thread thread2 = new Thread(testLock2, "2");
//        Thread thread3 = new Thread(testLock2, "3");
//        thread1.setPriority(1);
//        thread2.setPriority(4);
//        thread3.setPriority(8);
//        System.out.println(thread1.getName()+" "+thread1.getPriority());
//        System.out.println(thread2.getName()+" "+thread2.getPriority());
//        System.out.println(thread3.getName()+" "+thread3.getPriority());
//        thread1.start();
//        thread2.start();
//        thread3.start();
    }
}


 class TestLock2 implements Runnable{

    int tickerNumbs = 10;
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName());
        while (true){
           try {
               //Thread.Sleep(0)的作用，就是“触发操作系统立刻重新进行一次CPU竞争，重新计算优先级”。
               // 竞争的结果也许是当前线程仍然获得CPU控制权，也许会换成别的线程获得CPU控制权
               Thread.sleep(0);
               lock.lock();//加锁
               System.out.println("Thread "+Thread.currentThread().getName()+" is locked");
               if(tickerNumbs>0){
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println(Thread.currentThread().getName()+"+get the ticket "+ tickerNumbs--);
               }else {
                   break;
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               lock.unlock();
               System.out.println("锁 "+Thread.currentThread().getName()+" 释放");
           }
       }
    }
}
