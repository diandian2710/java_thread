package com.kuang.demo01;

import com.kuang.demo02.TestCallable;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
//练习Thread, 实现多线程同步下载图片
public class TestThread04 implements Runnable{
    private String url;
    private String name;

   public TestThread04(String url, String name) {
      this.url = url;
      this.name = name;
   }

   public void run() {
     WebDownloader webDownloader = new WebDownloader();
     webDownloader.downloader(url,name);
       System.out.println("下载了文件名为: " + name);
   }

    public static void main(String[] args) {
        TestThread04 result1 = new TestThread04("https://img-blog.csdnimg.cn/20190820172404863.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01pcmFjbGVfR2FhcmFs,size_16,color_FFFFFF,t_70", "1.jpg");
        TestThread04 result2 = new TestThread04("https://user-gold-cdn.xitu.io/2018/10/23/1669e48d8273e459?" +
                "imageView2/0/w/1280/h/960/format/webp/ignore-error/1", "2.jpg");
        new Thread(result1).start();
        new Thread(result2).start();

    }
}


