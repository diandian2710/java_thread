package com.kuang.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;
//线程创建方式3： 实现Callable 接口
//callable 好处
//1.可以定义返回值
//2. 可以抛出异常
public class TestCallable implements Callable<Boolean> {
    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为: " + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://img-blog.csdnimg.cn" +
                "/20190820172404863.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk," +
                "shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01pcmFjbGVfR2FhcmFs,size_16,color_FFFFFF,t_70", "1.jpg");
        TestCallable t2 = new TestCallable("https://user-gold-cdn.xitu.io/2018/10/23/1669e48d8273e459?" +
                "imageView2/0/w/1280/h/960/format/webp/ignore-error/1", "2.jpg");
        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(2);
        //提交执行
        Future<Boolean> result1 = ser.submit(t1);
        Future<Boolean> result2 = ser.submit(t2);
        //获取结果

        Boolean rs1 = result1.get();
        boolean rs2 = result2.get();

        System.out.println(rs1);
        System.out.println(rs2);

        //关闭服务
        ser.shutdown();





    }

    //下载器
    class WebDownloader {
        public void downloader(String url, String name) {
            try {
                FileUtils.copyURLToFile(new URL(url), new File(name));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO异常, downloader方法出现问题");
            }
        }
    }
}
