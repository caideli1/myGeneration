package com.yq.canal.controller;

import com.yq.canal.base.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2019/9/23 20:11
 * 描述：
 */
@RestController
public class TestController {

    @RequestMapping("/mongoSelectAll")
    public JsonResult esSelectAll() {
        return JsonResult.ok();
    }





}
