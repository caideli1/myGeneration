package com.caideli.springBoot.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/28 0028 16:18
 * 描述：
 */
public class CountDownLatchDemo implements Runnable {
    //表示要有10个线程完成任务
    public static CountDownLatch end=new CountDownLatch(5);
    static CountDownLatchDemo cdld=new CountDownLatchDemo();
    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10)*1000);
            if (end.getCount()>0){
                System.out.println(Thread.currentThread().getName()+"检测安全");
                end.countDown();//每有一个线程过来完成任务，计数器减1
            }else {
                System.out.println("已经发射，回去重新集结："+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec=Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++)
        {
            exec.submit(cdld);
        }
        end.await();//主线程等待，等待在end上的线程完成任务，主线程才开始执行下一步
        System.out.println("————点火发射————");
        exec.shutdown();
    }
}
