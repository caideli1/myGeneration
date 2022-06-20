package com.caideli.springBoot.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2022/6/17 0017 16:00
 * 描述：
 */
@Slf4j
public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static boolean hasSmoke = false;
    private static boolean hasSnacks = false;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 5, 1,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));
        executor.setCorePoolSize(20);
        Condition smoke = lock.newCondition();
        Condition snacks = lock.newCondition();
        executor.submit(()->{
            lock.lock();
            try {
                while (!hasSmoke) {
                    log.info(Thread.currentThread().getName() + "我是boy-one,没有烟,干活没动力");
                    try {
                        smoke.await();
                    } catch (InterruptedException e) {
                    }
                }
                log.info("我是boy-one,有烟了,嘬一口.");
            } finally {
                lock.unlock();
            }
        });
        executor.submit(()->{
            lock.lock();
            try {
                while (!hasSmoke) {
                    log.info(Thread.currentThread().getName() + "我是boy-two,没有烟,干活没动力");
                    try {
                        smoke.await();
                    } catch (InterruptedException e) {
                    }
                }
                log.info("我是boy-two,有烟了,嘬一口.");
            } finally {
                lock.unlock();
            }
        });
        executor.submit(()->{
            lock.lock();
            try {
                while (!hasSnacks) {
                    log.info(Thread.currentThread().getName() + "我是girl-one,没有零食吃,干活没力气");
                    try {
                        snacks.await();
                    } catch (InterruptedException e) {
                    }
                }
                log.info("我是girl-one, 有零食了,吃一口.");
            } finally {
                lock.unlock();
            }
        });
        executor.submit(()->{
            lock.lock();
            try {
                while (!hasSnacks) {
                    log.info(Thread.currentThread().getName() + "我是girl-two,没有零食吃,干活没力气");
                    try {
                        snacks.await();
                    } catch (InterruptedException e) {
                    }
                }
                log.info("我是girl-two, 有零食了,吃一口.");
            } finally {
                lock.unlock();
            }
        });
        executor.submit(()->{
            lock.lock();
            try {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                hasSmoke = true;
                log.info("烟来喽");
                smoke.signalAll();
                hasSnacks = true;
                log.info("零食来喽");
                snacks.signalAll();
            }finally {
                lock.unlock();
            }
        });
        executor.shutdown();
    }
}
