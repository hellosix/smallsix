package cn.hellosix.controller.supper;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Response;
import cn.hellosix.model.SqlModel;
import cn.hellosix.service.supper.JSApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/3.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/super")
@UserAccess
public class JSApiController {
    @Autowired
    private JSApiService service;
    @RequestMapping(value = "/run-sql", method = RequestMethod.POST)
    @ResponseBody
    public Response runSql(@RequestBody SqlModel sqlModel){
        Object res;
        try {
            res = service.runSql(sqlModel);
        }catch (Exception e){
            res = e.getMessage();
        }
        return Response.Result(0, res);
    }

    @RequestMapping(value = "/save-sql", method = RequestMethod.POST)
    @ResponseBody
    public Response saveSql(@RequestBody SqlModel sqlModel){
        Boolean res = service.saveSql(sqlModel);
        if(res){
            return Response.Info("success");
        }else{
            return Response.Error("fail");
        }
    }

    @RequestMapping(value = "/removeSql", method = RequestMethod.GET)
    @ResponseBody
    public Response removeSql(@RequestParam int id){
        boolean res = service.removeSql(id);
        if( res ){
            return Response.Info("success");
        }
        return Response.Error("fail");
    }

    @RequestMapping(value = "/getApiList", method = RequestMethod.GET)
    @ResponseBody
    public Response getApiList(@RequestParam String database, @RequestParam String table){
        return Response.Result(0, null);
    }

    @RequestMapping(value = "/createJsSDK", method = RequestMethod.GET)
    @ResponseBody
    public Response createJsSDK(@RequestParam String database, @RequestParam String table){
        String apiUrl = service.createJsSDK(database, table);
        return Response.Result(0, apiUrl);
    }

    @RequestMapping(value = "/getSqlModelList", method = RequestMethod.GET)
    @ResponseBody
    public Response getSqlModelList(@RequestParam String database, @RequestParam String table){
        List<SqlModel> sqlModels = service.getSqlModelList(database, table);
        return Response.Result(0, sqlModels);
    }

    @RequestMapping(value = "/getSqlModel", method = RequestMethod.GET)
    @ResponseBody
    public Response getSqlModel(@RequestParam Integer id){
        SqlModel sqlModel = service.getSqlModel(id);
        return Response.Result(0, sqlModel);
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/api/restful/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Response apiRestful(@PathVariable Integer id, @RequestBody Map<String, Object> param, HttpServletRequest httpServletRequest){
        Object res;
        try {
            res = service.apiRestful(id, param, httpServletRequest);
        }catch (Exception e){
            res = e;
        }
        return Response.Result(0, res);
    }


}
