package com.caideli.springBoot.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/7/19 0019 13:32
 * 描述：
 */
public class SemapDemo implements Runnable{
    //指定最大许可数5,并且是公平锁
    public static Semaphore sema=new Semaphore(5,true);
    @Override
    public void run() {
        try {
            sema.acquireUninterruptibly();
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+" done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            sema.release();
        }
    }
    public static void main(String[] args) {
        //妖用线程池创建10个线程
        ExecutorService exec = Executors.newFixedThreadPool(10);
        SemapDemo fd=new SemapDemo();
        for(int i=0;i<10;i++){
            exec.submit(fd);
        }

        exec.shutdown();
    }
}

