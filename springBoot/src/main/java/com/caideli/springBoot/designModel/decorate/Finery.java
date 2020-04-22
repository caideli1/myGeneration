package com.caideli.springBoot.designModel.decorate;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/7 14:02
 * 描述：
 * 1、服饰形象展示类，这个类专门用来装饰它的父类person，重写装饰操作。装饰实例化进来的被装饰者。
 * 2、每一个子装饰都继承这个装饰类，为子类。子类也都重写装饰操作。这样子类都具备了装饰属性。继承于父类。
 */
public class Finery extends Person {
    protected Person component;

    /**
     * 装扮
     */
    public void decorate(Person component){
        this.component = component;
    }
    @Override
    public void show(){
        if (component != null){
            component.show();
        }
    }
}
