package cn.hellosix.controller;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Response;
import cn.hellosix.service.AdminService;
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
@RequestMapping("/admin")
@UserAccess
public class AdminController {
    @Autowired
    private AdminService service;

    @RequestMapping("/index")
    public String index(Model model){
        return "admin/index";
    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    @ResponseBody
    public Response getMenu(@RequestParam String database){
        List<String> menuList = service.getMenu(database);
        return Response.Result(0, menuList);
    }
}
