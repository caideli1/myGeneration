package com.caideli.springBoot.java;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/7/19 0019 13:48
 * 描述：
 */
public class intToInteger {
    public static void diffInter(){
        Integer i1 = 127;
        Integer i2 =127;
        Integer i3 =128;
        Integer i4 =128;
        Long a = 1L;
        Integer c = 1;
        String b = "1";
        String b1 = "1";
        System.out.println(i1==i2);
        System.out.println(i3==i4);
        System.out.println(i3.equals(i4));
        System.out.println(b==b1);
        System.out.println(b.equals(b1));
        Object object = new Object();
        Object object1 = new Object();
        object.equals(object1);
        System.out.println(object.hashCode());
        System.out.println(object.toString());
        System.out.println(object1.toString());
        a.hashCode();
        Map map = new HashMap<>();
        map.put(null,1);
        map.put(1,null);
        System.out.println(map.get(null));
        System.out.println(map.get(1));
    }

    /*public static void main(String[] args) {
        diffInter();
    }*/
    public static void main(String[] args) {
        List<Integer> list= Collections.emptyList();
        for (Integer i:list){

        }
        System.out.printf("12345234534");
    }
}
