package com.caideli.springBoot.designModel.decorate;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/7 14:14
 * 描述：
 */
public class MainTest {
    public static void main(String[] args) {
        Person person = new Person("小明");

        Tshirts tshirts = new Tshirts();
        BigTrouser bigTrouser = new BigTrouser();
        Sneakers sneakers = new Sneakers();

        System.out.println("\n第一种装扮：");
        tshirts.decorate(person);
        bigTrouser.decorate(tshirts);
        bigTrouser.show();

        System.out.println("\n第二种装扮：");
        tshirts.decorate(person);
        sneakers.decorate(tshirts);
        sneakers.show();
    }
}
