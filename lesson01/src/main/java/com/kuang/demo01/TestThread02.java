package com.kuang.demo01;

import com.kuang.demo02.TestCallable;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//练习Thread, 实现多线程同步下载图片
public class TestThread02 extends Thread{
    private String url;
    private String name;

   public TestThread02(String url, String name) {
      this.url = url;
      this.name = name;
   }

   @Override
   public void run() {
     WebDownloader webDownloader = new WebDownloader();
     webDownloader.downloader(url,name);
       System.out.println("下载了文件名为: " + name);
   }

    public static void main(String[] args) {
        TestThread02 result1 = new TestThread02("https://img-blog.csdnimg.cn/20190820172404863.png?x-oss-process=image" +
                "/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01pcmFjbGVfR2FhcmFs," +
                "size_16,color_FFFFFF,t_70", "1.jpg");

        TestThread02 result2 = new TestThread02("https://user-gold-cdn.xitu.io/2018/10/23/1669e48d8273e459?" +
                "imageView2/0/w/1280/h/960/format/webp/ignore-error/1", "2.jpg");
        result1.start();
        result2.start();
    }
}

//下载器
class WebDownloader{
   public void downloader(String url, String name) {
      try {
         FileUtils.copyURLToFile(new URL(url), new File(name));
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("IO异常, downloader方法出现问题");
      }
   }
}
