package com.kuang.customerandproducer;

import java.awt.*;

//测试：生产者消费者模型-->利用缓冲区解决：管程法
public class TestPc {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        Producer producer = new Producer(synContainer);
        Customer customer = new Customer(synContainer);
        producer.start();
        customer.start();
    }
}

//生产者
class Producer extends Thread{
   SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    //生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Production(i));
            System.out.println("生产了 "+i+"鸡");
        }
    }
}

//消费者
class Customer extends Thread{
    SynContainer container;

    public Customer(SynContainer container) {
        this.container = container;
    }

    //消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了--->"+container.pop().id+"只");
        }
    }
}

//产品
class Production{
    int id; //产品编号

    public Production(int id) {
        this.id = id;
    }
}

//缓冲区
class SynContainer{
    //需要容器大小
   Production[] productions = new Production[10];
   int count = 0;
   //生产者放入产品
    public synchronized void push(Production production){
        //如果容器满了，就需要等待消费
        if (count ==productions.length){
            //通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有满，我们就需要丢入产品
        productions[count] = production;
        count++;
        //可以通知消费者消费了
        this.notifyAll();

    }
    // 消费者消费产品
    public synchronized Production pop(){
        if(count==0){
            //等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果可以消费
        count--;
        Production production = productions[count];
        //通知生产者生产
        this.notifyAll();
        return production;
    }
}