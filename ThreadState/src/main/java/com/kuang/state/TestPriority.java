package com.kuang.state;

//测试线程优先级
public class TestPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"---->"+Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        TestPriority testPriority = new TestPriority();
        Thread t1 = new Thread(testPriority);
        Thread t2 = new Thread(testPriority);
        Thread t3 = new Thread(testPriority);
        Thread t4 = new Thread(testPriority);
        Thread t5 = new Thread(testPriority);

        //先设置优先级，再启动
        t1.start();

        t2.setPriority(1);
        t2.start();

        t3.setPriority(4);
        t3.start();

        t4.setPriority(7);
        t4.start();

        t5.setPriority(Thread.MAX_PRIORITY);
        t5.start();
    }

}
//优先级低知识意味着获得调度的概率低， 并不是优先级低就不回被调用了， 这都是看CPU的调度

//问题:性能倒置