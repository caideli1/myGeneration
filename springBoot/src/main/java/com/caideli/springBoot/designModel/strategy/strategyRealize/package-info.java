/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/2 13:53
 * 描述：策略模式和工厂的结合，我个人感觉，就是将判断层再次封装了一个类出来，中间类。这样子就在只要管中间类和工厂类的关系就可以了，调用方永远调用的都是中间类的同一个方式，让不同的工厂去是实现产生不同对象即可。
 * 应用上，只要在不同时间应用不同业务规则都可以用大策略模式
 */
package com.caideli.springBoot.designModel.strategy.strategyRealize;