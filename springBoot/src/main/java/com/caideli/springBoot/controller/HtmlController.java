package com.caideli.springBoot.controller;

import com.caideli.springBoot.model.AmountPool;
import com.caideli.springBoot.service.AmountPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2022/5/16 0016 17:31
 * 描述：
 */
@Controller
public class HtmlController {

    @Autowired
    private AmountPoolService amountPoolService;

    @GetMapping("/index")
    public ModelAndView execute(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");///templates/hello.html
        mav.getModel().put("msg", "你好，我是标题");
        AmountPool amountPool = amountPoolService.selectAll().get(0);
        mav.getModel().put("img", amountPool.getImg());
        mav.getModel().put("amount", amountPool.getAmount());
        return mav;
    }
}
