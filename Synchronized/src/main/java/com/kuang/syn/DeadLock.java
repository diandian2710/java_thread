package com.kuang.syn;

//死锁：多个线程互相抱着对方需要的资源，然后形成僵持
public class DeadLock {
    public static void main(String[] args) {
        Makeup makeup1 = new Makeup(0,"xiaohei");
        Makeup makeup2 = new Makeup(1,"hahaha");
        makeup1.start();
        makeup2.start();
    }
}

class Lipstick{

}

class Mirror{

}

class Makeup extends Thread{
    //需要的资源只有一份，用static来保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice; //选择
    String girlName; //使用化妆品的人

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
   public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //化妆
   }

   //化妆，互相持有对方的锁，就是需要拿到对方的资源
   private void makeup() throws InterruptedException {
       if (choice == 0) {
           synchronized (lipstick) { //获得口红的锁
               System.out.println("获得口红的锁" + this.girlName);
               Thread.sleep(1000);
           }
           synchronized (mirror) { //一秒钟后获得镜子的锁
               System.out.println("获得镜子的锁" + this.girlName);
           }
       } else {
           synchronized (mirror) { //一秒钟后获得镜子的锁
               System.out.println("获得镜子的锁" + this.girlName);
               Thread.sleep(1000);
           }
           synchronized (lipstick) { //获得口红的锁
               System.out.println("获得口红的锁" + this.girlName);
           }
       }
   }
}