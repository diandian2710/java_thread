package com.kuang.state;

//模拟网络延迟：放大问题的发生性
public class TestSleep implements Runnable {

    private int ticketNums = 10;

    public void run() {
        while (true){
            if (ticketNums<1){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {
        TestSleep ticket = new TestSleep();
        new Thread(ticket, "xiaoming").start();
        new Thread(ticket, "xiaohong").start();
        new Thread(ticket, "xiaohei").start();
    }
}


