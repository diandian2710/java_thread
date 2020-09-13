package com.kuang.syn;

public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(1000, "marriage funding");

        Drawing you = new Drawing(account, 50,"you" );
        Drawing girlfriend = new Drawing(account, 100,"girlfriend" );
//        Drawing parents = new Drawing(account, 10,"parents" );

        you.start();
        girlfriend.start();
//        parents.start();
    }
}


class Account {
    int money;//余额
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行:模拟取款
class  Drawing extends Thread{
    Account account;
    //取了多少钱
    int drawingMoney;
    //现在手里多少钱
    int nowMoney;


    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        //锁的对象就是变化的量(也就是共享资源因为共享资源一次只能有一个对象操作)，需要增删改查,
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够,取不了");
                return;
            }
            //sleep放大问题的发生性
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额 = 余额-取的钱
            account.money -= drawingMoney;
            //手里的钱
            nowMoney += drawingMoney;
            System.out.println(account.name + "余额为: " + account.money);
            //        Thread.currentThread().getName() = this.getName()
            System.out.println(this.getName() + "手里的钱" + nowMoney);
        }
    }
}
