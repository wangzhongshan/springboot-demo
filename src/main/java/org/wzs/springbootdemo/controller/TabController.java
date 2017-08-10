package org.wzs.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tab")
public class TabController {

    /**
     * tab详情
     *
     * @param tab
     * @return
     */
    @GetMapping("{tab}")
    public String tab(@PathVariable String tab, ModelMap modelMap) {
        modelMap.addAttribute("tabName", tab);
        return tab;
    }
}
