package com.kuang.customerandproducer;

//测试生产者消费者问题2： 信号灯法，标志位解决
public class TestPc2 {
    public static void main(String[] args) {
        Tv tv = new Tv();
        new Actor(tv).start();
        new Audience(tv).start();
    }
}

//生产者-->演员
class Actor extends Thread{
   Tv tv;

    public Actor(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i%2==0){
               this.tv.play("China Movie");
            }else {
                this.tv.play("advertisement");
            }
        }
    }
}
//消费者-->观众
class Audience extends Thread {
    Tv tv;
    public Audience(Tv tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            this.tv.watch();
        }
    }
}
//产品-->节目
class Tv{
   //演员表演的时候,观众等待 T
   //观众观看的时候，演员等待 F
    String TvShow; //表演的节目
    boolean flag = true; //标志位

    //表演
    public synchronized  void play(String TvShow){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了"+TvShow);
        //通知观众观看
        this.notifyAll(); //通知唤醒
        this.TvShow = TvShow;
        this.flag = !this.flag;
    }

    //观看
    public synchronized void watch(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了"+ TvShow);
        //通知演员表演
        this.notifyAll();
        this.flag = !this.flag;
    }
}