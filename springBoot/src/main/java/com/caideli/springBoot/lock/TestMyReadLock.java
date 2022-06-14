package com.caideli.springBoot.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2022/6/14 0014 15:35
 * 描述：验证读锁是可以同时获取的，不互斥
 */
public class TestMyReadLock {
    private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static Lock readLock=readWriteLock.readLock();//读锁
    private static Lock writeLock=readWriteLock.writeLock();//写锁

    public void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " 获取读锁");
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
            readLock.unlock();
        }
    }

    public static void main1(String[] args) throws Exception{

        TestMyReadLock testMyReadWriteLock = new TestMyReadLock();

        new Thread("A") {
            @Override
            public void run() {
                testMyReadWriteLock.read();
            }
        }.start();

        Thread.sleep(1000L);

        new Thread("B") {
            @Override
            public void run() {
                testMyReadWriteLock.read();
            }
        }.start();

        Thread.sleep(1000L);

        new Thread("C"){
            @Override
            public void run() {
                testMyReadWriteLock.read();
            }
        }.start();

    }


    public static void main(String[] args) {
        int a = 1>>>16;
        int b = 2>>>16;
        int c = 1<<16;
        int d = 65536>>>16;
        int e = 65537>>>16;
        int f = 65538>>>16;
        int g = 65535>>>16;
        System.out.printf(a+"\n");
        System.out.printf(b+"\n");
        System.out.printf(c+"\n");
        System.out.printf(d+"\n");
        System.out.printf(e+"\n");
        System.out.printf(f+"\n");
        System.out.printf(g+"\n");




    }
}
