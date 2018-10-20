package cn.hellosix.controller.supper;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Response;
import cn.hellosix.model.SystemAdvise;
import cn.hellosix.service.supper.SystemAdviseService;
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
@RequestMapping("/systemAdvise")
@UserAccess
public class SystemAdviseController {
    @Autowired
    SystemAdviseService service;


    @RequestMapping("/super")
    public String superSystemAdvise(Model model){
        return "super/systemAdvise";
    }

    @RequestMapping("/admin")
    public String adminUser(Model model){
        return "admin/systemAdvise";
    }


    @RequestMapping(value = "/getSystemAdviseList", method = RequestMethod.GET)
    @ResponseBody
    public Response getSystemAdviseList(){
        List<SystemAdvise> systemAdvises = service.getSystemAdviseList();
        if( null == systemAdvises ){
            systemAdvises = new ArrayList<>();
        }
        return Response.Result(0, systemAdvises);
    }

    @RequestMapping(value = "/getSystemAdvise", method = RequestMethod.GET)
    @ResponseBody
    public Response getSystemAdvise(@RequestParam int id){
        SystemAdvise systemAdvise = service.getSystemAdvise( id );
        if( null == systemAdvise ){
            systemAdvise = new SystemAdvise();
        }
        return Response.Result(0, systemAdvise);
    }

    @RequestMapping(value = "/removeSystemAdvise", method = RequestMethod.GET)
    @ResponseBody
    public Response removeSystemAdvise(@RequestParam int id){
        service.removeSystemAdvise( id );
        return Response.Success();
    }

    @RequestMapping(value = "/updateSystemAdvise", method = RequestMethod.POST)
    @ResponseBody
    public Response updateSystemAdvise(@RequestBody SystemAdvise systemAdvise){
        Response response = Response.Error("fail update systemAdvise");
        try {
            service.updateSystemAdvise(systemAdvise);
            response = Response.Info("success");
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @RequestMapping(value = "/addSystemAdvise", method = RequestMethod.POST)
    @ResponseBody
    public Response addSystemAdvise(@RequestBody SystemAdvise systemAdvise){
        Response response = Response.Error("fail add systemAdvise");
        try {
            service.addSystemAdvise(systemAdvise);
            response = Response.Info("success");
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }
}
