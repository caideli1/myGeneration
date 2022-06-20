package com.caideli.springBoot.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2022/6/17 0017 17:09
 * 描述：
 */
public class SemapDemo2 {
    private static final int SEM_MAX = 7;

    public static void main(String[]args) {
        Semaphore sem = new Semaphore(SEM_MAX);
        //创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //在线程池中执行任务
        threadPool.execute(new MyThread(sem, 7));
        threadPool.execute(new MyThread(sem, 8));
        threadPool.execute(new MyThread(sem, 5));
        //关闭池
        threadPool.shutdown();
    }
}
        class MyThread extends Thread {
            private volatile Semaphore sem;
            //信号量
            private int count;

            //申请信号量的大小
            MyThread(Semaphore sem, int count) {
                this.sem = sem;
                this.count = count;
            }
                @Override
                public void run(){
                    try {
                        //从信号量中获取count-个许可
                        sem.acquire(count);
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + "acquire:"+count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    //释放给定数目的许可，将其返回到信号量
                        sem.release(count);
                        System.out.println(Thread.currentThread().getName() + "release:"+count);
                    }
                }
            }