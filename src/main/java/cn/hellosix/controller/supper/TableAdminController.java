package cn.hellosix.controller.supper;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Response;
import cn.hellosix.model.TableExtend;
import cn.hellosix.service.supper.TableAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by lzz on 2018/10/28.
 */
@Controller
@RequestMapping("/super")
@UserAccess
public class TableAdminController {
    @Autowired
    private TableAdminService tableAdminService;

    @RequestMapping(value = "/getTableExtendByDatabase", method = RequestMethod.GET)
    @ResponseBody
    public Response getTableExtendByDatabase(@RequestParam String database){
        List<TableExtend> tableExtendList = tableAdminService.getTableExtendList(database);
        return Response.Result(0, tableExtendList);
    }

    @RequestMapping(value = "/addTableAndExtend", method = RequestMethod.POST)
    @ResponseBody
    public Response addTableAndExtend(@RequestBody TableExtend tableExtend) throws IOException {
        Boolean res = tableAdminService.addTableAndExtend(tableExtend);
        if(res){
            return Response.Info("success");
        }else{
            return Response.Error("fail");
        }
    }

    @RequestMapping(value = "/deleteTableAndExtend", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteTableAndExtend(@RequestParam String database,@RequestParam String table){
        boolean res = tableAdminService.deleteTableAndExtend(database, table);
        return Response.Result(0, res);
    }

}
