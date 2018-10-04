package cn.hellosix.controller;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.ColumnExtend;
import cn.hellosix.model.FieldExtend;
import cn.hellosix.model.Response;
import cn.hellosix.model.TableExtend;
import cn.hellosix.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getColumnExtendList", method = RequestMethod.GET)
    @ResponseBody
    public Response getColumnExtendList(@RequestParam String database, @RequestParam String table){
        List<ColumnExtend> columnList = service.getColumnExtendList(database, table);
        return Response.Result(0, columnList);
    }

    @RequestMapping(value = "/updateFieldExtend", method = RequestMethod.POST)
    @ResponseBody
    public Response updateFieldExtend(@RequestBody FieldExtend fieldExtend){
        boolean res = service.updateFieldExtend( fieldExtend );
        if( res ){
            return Response.Info("success");
        }else{
            return Response.Error("error");
        }
    }

    @RequestMapping(value = "/updateTableExtend", method = RequestMethod.POST)
    @ResponseBody
    public Response updateTableExtend(@RequestBody TableExtend tableExtend){
        boolean res = service.updateTableExtend( tableExtend );
        if( res ){
            return Response.Info("success");
        }else{
            return Response.Error("error");
        }
    }

    @RequestMapping(value = "/getTableExtendDetail", method = RequestMethod.GET)
    @ResponseBody
    public Response getTableExtendDetail(@RequestParam String database, @RequestParam String table){
        TableExtend tableExtend = service.getTableExtendDetail(database, table);
        return Response.Result(0, tableExtend);
    }


}
