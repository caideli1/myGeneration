package com.caideli.springBoot.designModel.decorate;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/7 14:02
 * 描述：服饰形象展示类
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
