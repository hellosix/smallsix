package cn.hellosix.controller.supper;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Response;
import cn.hellosix.model.UserNotify;
import cn.hellosix.service.supper.UserNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/10/20.
 */
@Controller
@RequestMapping("/userNotify")
@UserAccess
public class UserNotifyController {
    @Autowired
    UserNotifyService service;

    @RequestMapping("/super")
    public String superSystemAdvise(Model model){
        return "super/userNotify";
    }

    @RequestMapping(value = "/getUserNotifyList", method = RequestMethod.GET)
    @ResponseBody
    public Response getUserNotifyList(){
        List<UserNotify> userNotifys = service.getUserNotifyList();
        if( null == userNotifys ){
            userNotifys = new ArrayList<>();
        }
        return Response.Result(0, userNotifys);
    }

    @RequestMapping(value = "/getUserNotify", method = RequestMethod.GET)
    @ResponseBody
    public Response getUserNotify(@RequestParam int id){
        UserNotify userNotify = service.getUserNotify( id );
        if( null == userNotify ){
            userNotify = new UserNotify();
        }
        return Response.Result(0, userNotify);
    }

    @RequestMapping(value = "/removeUserNotify", method = RequestMethod.GET)
    @ResponseBody
    public Response removeUserNotify(@RequestParam int id){
        service.removeUserNotify( id );
        return Response.Success();
    }

    @RequestMapping(value = "/updateUserNotify", method = RequestMethod.POST)
    @ResponseBody
    public Response updateUserNotify(@RequestBody UserNotify userNotify){
        Response response = Response.Error("fail update userNotify");
        try {
            service.updateUserNotify(userNotify);
            response = Response.Info("success");
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @RequestMapping(value = "/addUserNotify", method = RequestMethod.POST)
    @ResponseBody
    public Response addUserNotify(@RequestBody UserNotify userNotify){
        Response response = Response.Error("fail add userNotify");
        try {
            service.addUserNotify(userNotify);
            response = Response.Info("success");
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

}
