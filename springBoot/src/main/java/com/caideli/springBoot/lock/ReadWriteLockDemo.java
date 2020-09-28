package com.caideli.springBoot.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Random;
/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/28 0028 15:09
 * 描述：
 */
public class ReadWriteLockDemo {
    private static Lock lock=new ReentrantLock(); //重入锁
    private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static Lock readlock=readWriteLock.readLock();//读锁
    private static Lock writelock=readWriteLock.writeLock();//写锁
    private int value;
    private long start;
    private long end;

    //读锁控制
    public Object handleRead(Lock lock) throws InterruptedException{
        this.start=System.currentTimeMillis();
        try {
            System.out.printf("读锁锁前："+Thread.currentThread().getName()+"\n");
            lock.lock();
            Thread.sleep(2000);//模拟读操作，读操作越耗时，读写锁的优势越明显
            System.out.printf("读完后，value的值："+value+"  "+Thread.currentThread().getName()+"\n");
            return value;
        }finally{
            lock.unlock();
        }
    }
    //写锁控制
    public void handleWrite(Lock lock,int value) throws InterruptedException{
        try {
            System.out.printf("------写锁锁前："+Thread.currentThread().getName()+"\n");
            lock.lock();
            Thread.sleep(1000);//模拟写操作
            System.out.printf("------写完后，value的值："+value+"  "+Thread.currentThread().getName()+"\n");
            this.value=value;
        }finally{
            this.end=System.currentTimeMillis();
            System.out.println("读写锁分离18次写一次用时："+(end-start)+"ms");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ReadWriteLockDemo demo=new ReadWriteLockDemo();
        Runnable readRunnable=new Runnable(){
            @Override
            public void run() {
                long start=System.currentTimeMillis();
                try {
                    //demo.handleRead(lock);//使用重入锁
                    demo.handleRead(readlock);//使用读锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }//获得锁
            }
        };

        Runnable writeRunnable=new Runnable(){
            @Override
            public void run() {
                try {
                    demo.handleWrite(writelock,new Random().nextInt());//获得写锁
                    //demo.handleWrite(lock,new Random().nextInt());//获得重入锁锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }//获得读锁
            }
        };

        for(int i=0;i<18;i++){
            new Thread(readRunnable).start();
        }
        Thread.sleep(1000);
        for(int i=10;i<11;i++){
            new Thread(writeRunnable).start();
        }
    }

}
