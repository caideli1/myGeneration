package com.caideli.springBoot.concurrent;

import java.util.concurrent.*;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/10/8 0008 14:12
 * 描述：
 */
public class NewThread {
    public static class UserRun implements Runnable{

        @Override
        public void run() {
            System.out.println("I am runnable thread");
        }
    }

    public static class  UserCall implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println("I am callable thread");
            return "callableResult";
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new UserRun()).start();

        FutureTask futureTask = new FutureTask(new UserCall());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        //ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    }
}
