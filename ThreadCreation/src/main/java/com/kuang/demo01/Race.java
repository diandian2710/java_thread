package com.kuang.demo01;

//模拟龟兔赛跑, 看谁先跑了100步
public class Race implements Runnable{

    private static String winner;
    public void run() {
        for ( int i= 1; i <= 100; i++) {
            //模拟兔子休息
            if(Thread.currentThread().getName().equals("rabbit") && i%10==0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("rabbit didn't sleep");
                }
            }
            //判断比赛是否结束
            boolean flag = gameOver(i);
            //如果比赛结束就，就停止程序
            if(flag){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

    private boolean gameOver(int steps){
       if (winner!=null){
           return true;
       }{
           if (steps==100){
               winner = Thread.currentThread().getName();
               System.out.println("the winnner is "+winner);
               return true;
           }
        }
       return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"rabbit").start();
        new Thread(race,"turtle").start();
    }
}
