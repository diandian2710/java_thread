package com.kuang.syn;

//synchronized方法控制对"对象"的访问， 每个对象对应一把锁
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "haha").start();
        new Thread(station, "xiheng").start();
        new Thread(station, "dagegege").start();
    }
}


class BuyTicket implements Runnable{

    private int ticketNums =10;
    boolean flag = true; //外部停止方式
    public void run() {
       //买票
        while (flag){
            //synchronized 释放锁以后需要再分配进入的线程
            //Thread.Sleep(0)的作用，就是“触发操作系统立刻重新进行一次CPU竞争，重新计算优先级”。
            // 竞争的结果也许是当前线程仍然获得CPU控制权，也许会换成别的线程获得CPU控制权
            try {
                Thread.sleep(0); //重新分配进入的线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
               buy();
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        }
        System.out.println(Thread.currentThread().getName()+"卖完了");
    }

    //synchronized 同步方法, 所得是this
    public synchronized void buy() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("线程 " + Thread.currentThread().getName());
        if(ticketNums<=0){
            flag=false;
            return;
        }
        Thread.sleep(100);
        //买票
        System.out.println(Thread.currentThread().getName()+"拿到"+ticketNums--);
    }
}
