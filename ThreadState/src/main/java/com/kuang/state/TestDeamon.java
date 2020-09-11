package com.kuang.state;

//守护线程
public class TestDeamon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        Thread thread = new Thread(god);
        thread.setDaemon(true);//默认是false表示是用户线程， 正常的线程都是用户线程.
        // true 表示是守护线程，守护线程会伴随着用户线程关闭, 通过jvm关闭
        thread.start();

        new Thread(you).start();// you 用户线程启用
    }

}

class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("上帝保佑你");
        }
    }
}




class You implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 35600; i++) {
            System.out.println("你医生都开心的或者");
        }

        System.out.println("=====goodbye world");
    }
}