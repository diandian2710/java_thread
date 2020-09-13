package com.kuang.syn;

public class Test {
    public static void main(String[] args) {
        new Example1().run();
    }
}

class Example1{
    int i =10;
    public void run() {
        while (true) {
            try {
                System.out.println("pass try");
                if (i > 0) {
                    System.out.println(i);
                    i--;
                } else {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}