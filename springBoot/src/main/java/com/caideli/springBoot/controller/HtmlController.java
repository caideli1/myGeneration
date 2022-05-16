package com.caideli.springBoot.controller;

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

    @GetMapping("/index")
    public ModelAndView execute(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");///templates/hello.html
        mav.getModel().put("msg", "你好，梁锐倩");
        return mav;
    }
}
