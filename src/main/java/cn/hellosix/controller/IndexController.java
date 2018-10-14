package cn.hellosix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lzz on 2018/10/14.
 */
@Controller
public class IndexController {
    @RequestMapping( value = {"","/","/index"} )
    public String index(Model model){
        return "index";
    }
}

