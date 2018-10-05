package cn.hellosix.controller;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Common;
import cn.hellosix.model.Response;
import cn.hellosix.model.User;
import cn.hellosix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lzz on 2018/10/4.
 */
@Controller
@RequestMapping("/user")
@UserAccess
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/admin")
    public String adminUser(Model model){
        return "super/user";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "admin/login";
    }

    @RequestMapping(value = "/verifyLogin", method = RequestMethod.GET)
    @ResponseBody
    public Response verifyLogin(@RequestParam String username, @RequestParam String password){
        User user = service.getUser( username );
        if( null != user && user.getPassword().equals( password ) ){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.setAttribute(Common.SESSION_USER_KEY, user);
            return Response.Success();
        }
        return Response.Error("用户名或密码填写有误");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Response logout(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute(Common.SESSION_USER_KEY);
        return Response.Info("logout");
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Response getUser(@RequestParam int id){
        User user = service.getUser( id );
        return Response.Result(0, user);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Response updateUser(@RequestBody User user){
        service.updateUser(user);
        return Response.Info("success");
    }

    @RequestMapping(value = "/autoGetUser", method = RequestMethod.GET)
    @ResponseBody
    public Response autoGetUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Common.SESSION_USER_KEY);
        return Response.Result(0, user);
    }

    @RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
    @ResponseBody
    public Response heartBeat(){
        return Response.Success();
    }
}
