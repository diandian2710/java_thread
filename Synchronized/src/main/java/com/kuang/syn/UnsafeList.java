package com.kuang.syn;

import java.util.ArrayList;
import java.util.List;

public class UnsafeList {
    public static void main(String[] args) {
        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < 3000; i++) {
           new Thread(()->{
               synchronized (list){
               list.add(Thread.currentThread().getName());
               }
           }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
