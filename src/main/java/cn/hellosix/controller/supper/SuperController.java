package cn.hellosix.controller.supper;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.*;
import cn.hellosix.service.supper.SuperService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/createApp")
    public String createApp(Model model){
        return "super/createApp";
    }

    @RequestMapping("/adminTable")
    public String adminTable(Model model){
        return "super/adminTable";
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

    @RequestMapping(value = "/getColumnExtendDetail", method = RequestMethod.GET)
    @ResponseBody
    public Response getColumnExtendDetail(@RequestParam String database, @RequestParam String table,@RequestParam String field){
        ColumnExtend columnExtend = service.getColumnExtendDetail(database, table, field);
        List<String> columns = service.columns(database, table);
        Map<String, Object> map = new HashMap();
        map.put("detail", columnExtend);
        map.put("columns", columns);
        return Response.Result(0, map);
    }



    @RequestMapping(value = "/getColumnExtendInit", method = RequestMethod.GET)
    @ResponseBody
    public Response getColumnExtendInit(@RequestParam String database, @RequestParam String table){
        ColumnExtend columnExtend = new ColumnExtend(new Column(), new FieldExtend());
        List<String> columns = service.columns(database, table);
        Map<String, Object> map = new HashMap();
        map.put("detail", columnExtend);
        map.put("columns", Lists.reverse(columns));
        return Response.Result(0, map);
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

    @RequestMapping(value = "/addFieldExtend", method = RequestMethod.POST)
    @ResponseBody
    public Response addFieldExtend(@RequestBody FieldExtend fieldExtend){
        boolean res = service.addFieldExtend( fieldExtend );
        if( res ){
            return Response.Info("success");
        }else{
            return Response.Error("error");
        }
    }

    @RequestMapping(value = "/deleteFiedldExtendById", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteFiedldExtendById(@RequestParam int id){
        boolean res = true;
        if( id != 0 ){
            res = service.deleteFiedldExtendById(id);
        }

        if( res ){
            return Response.Info("success");
        }else{
            return Response.Error("error");
        }
    }

    @RequestMapping(value = "/updateColumn", method = RequestMethod.POST)
    @ResponseBody
    public Response updateColumn(@RequestBody Column column){
        boolean res = service.updateColumn( column );
        if( res ){
            return Response.Info("success");
        }else{
            return Response.Error("error");
        }
    }

    @RequestMapping(value = "/addColumn", method = RequestMethod.POST)
    @ResponseBody
    public Response addColumn(@RequestBody Column column){
        boolean res = service.addColumn( column );
        if( res ){
            return Response.Info("success");
        }else{
            return Response.Error("error");
        }
    }

    @RequestMapping(value = "/removeColumn", method = RequestMethod.POST)
    @ResponseBody
    public Response removeColumn(@RequestBody Column column){
        boolean res = service.dropColumn( column );
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
