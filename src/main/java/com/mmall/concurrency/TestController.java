package com.mmall.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Zhihao Chen
 * @Date: Created in 17:10 2018/5/13
 * @Description
 */

@Controller
@Slf4j
public class TestController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "test";
    }
}
