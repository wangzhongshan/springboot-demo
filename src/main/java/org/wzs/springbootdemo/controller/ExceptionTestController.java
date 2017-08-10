package org.wzs.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wzs.springbootdemo.exception.MyException;

@Controller
@RequestMapping("/ex")
public class ExceptionTestController {

    @GetMapping("default")
    public String defaultException() throws Exception {
        throw new Exception("默认的异常");
    }

    @GetMapping("my")
    public String myException() {
        throw new MyException(1111, "自定义异常");
    }

}
