package com.caideli.springBoot.designModel.decorate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/7 13:50
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    private String name;

    public void show(){
        System.out.println("装扮的"+name);
    }
}
