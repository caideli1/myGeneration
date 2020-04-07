package com.caideli.springBoot.designModel.decorate;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/7 14:06
 * 描述：
 */
public class Tshirts extends Finery {
    @Override
    public void show(){
        System.out.println("大 T 上衣");
        super.show();
    }
}
