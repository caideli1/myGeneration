package com.caideli.springBoot.designModel.decorate;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/7 14:17
 * 描述：
 */
public class Sneakers extends Finery{
    @Override
    public void show(){
        System.out.println("运动鞋");
        super.show();
    }
}
