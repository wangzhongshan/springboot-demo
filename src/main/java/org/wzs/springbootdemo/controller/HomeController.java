package org.wzs.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    /**
     * 首页
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "hello,wzs!";
    }
}
