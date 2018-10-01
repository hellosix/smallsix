package cn.hellosix.controller;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Response;
import cn.hellosix.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lzz on 2018/10/1.
 */
@Controller
@RequestMapping("/super")
@UserAccess
public class SuperController {
    @Autowired
    private SuperService service;

    @RequestMapping("/admin")
    public String admin(Model model){
        return "super/admin";
    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    @ResponseBody
    public Response getMenu(){
        List<String> menuList = service.getMenu();
        return Response.Result(0, menuList);
    }

    @RequestMapping(value = "/getSubMenu", method = RequestMethod.GET)
    @ResponseBody
    public Response getSubMenu(@RequestParam String database){
        List<String> menuList = service.getSubMenu(database);
        return Response.Result(0, menuList);
    }

    @RequestMapping(value = "/getApiList", method = RequestMethod.GET)
    @ResponseBody
    public Response getApiList(@RequestParam String database, @RequestParam String table){
        return Response.Result(0, null);
    }


}
