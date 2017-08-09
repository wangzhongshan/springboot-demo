package org.wzs.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tab")
public class TabController {

    /**
     * tab详情
     * @param tab
     * @return
     */
    @GetMapping("{tab}")
    public String tab(@PathVariable String tab) {
        return tab;
    }
}
