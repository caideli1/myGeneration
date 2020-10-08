package com.caideli.springBoot.concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/10/8 0008 14:01
 * 描述：
 */
public class OnlyMain {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for (ThreadInfo threadInfo:threadInfos){
            System.out.println(threadInfo.getThreadId()+" : "+threadInfo.getThreadName());
        }
    }
}
