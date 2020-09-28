package com.caideli.springBoot.lock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/9/28 0028 16:28
 * 描述：
 */
public class CyclicBarrierDemo {
    private final int a = 1;

    public static class Soldier implements Runnable{
        private final CyclicBarrier cyclic;
        private String name;
        public Soldier(CyclicBarrier cyclic, String name) {
            super();
            this.cyclic = cyclic;
            this.name = name;
        }
        @Override
        public void run() {
            try {
                arrive();
                cyclic.await();
               //System.out.println("计数器的值"+cyclic.await());//等到cyclic的计数器达到设定的值往下执行
                doWork();
                //System.out.println(name+"任务回来去集合了");
                cyclic.await();//等到cyclic的计数器达到设定的值往下执行，循环使用cyclic
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void arrive() {
            try {
                //模拟报道
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"报道");
        }

        private void doWork() {
            try {
                //模拟执行任务
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"完成任务");
        }
    }

    /**
     * 用于栅栏打开后触发
     */
    public static class BarrierRun implements Runnable{
        int N;
        Boolean flag;

        public BarrierRun(int n, Boolean flag) {
            super();
            N = n;
            this.flag = flag;
        }
        @Override
        public void run() {
            // 每次cyclic计数完成执行的动作
            if(flag){
                System.out.println("司令："+N+"个士兵集合完毕");
                System.out.println("司令：接下来去执行任务");
                flag=false;
            }else{
                System.out.println("司令："+N+"个士兵完成任务");
            }
        }
    }

    public static void main(String[] args) {
        final int N=5;
        boolean flag=true;
        //5个士兵线程
        Thread [] soliders=new Thread[5];
        CyclicBarrier cyclic=new CyclicBarrier(5,new BarrierRun(N,flag));
        System.out.println("司令：士兵集合");
        for(int i=0;i<N;i++){
            //栅栏传入到执行线程
            soliders[i]=new Thread(new Soldier(cyclic,"士兵"+i));
            soliders[i].start();
        }
    }
}
