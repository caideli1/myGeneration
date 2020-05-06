package com.caideli.springBootNettyClient.controller;

import com.caideli.springBootCommon.base.JsonResult;
import com.caideli.springBootNettyClient.handler.EchoClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/30 15:08
 * 描述：
 */
@RestController
public class TestController {

    @Autowired
    private EchoClientHandler echoClientHandler;

    @RequestMapping("/sendServerMsg")
    public JsonResult sendClientMsg() {
        //echoClientHandler.send();
        return JsonResult.ok();
    }
}
