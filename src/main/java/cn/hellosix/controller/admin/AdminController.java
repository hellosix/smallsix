package cn.hellosix.controller.admin;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.*;
import cn.hellosix.service.admin.AdminService;
import cn.hellosix.service.supper.SearchFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by lzz on 2018/10/1.
 */
@Controller
@RequestMapping("/admin")
@UserAccess
public class AdminController {
    @Autowired
    private AdminService service;
    @Autowired
    private SearchFormService searchFormService;

    @RequestMapping( value = {"", "/","/index","/monitor"} )
    public String index(Model model){
        return "admin/index";
    }

    @RequestMapping("/about")
    public String about(Model model){
        return "admin/about";
    }

    @RequestMapping("/notify")
    public String notify(Model model){
        return "admin/notify";
    }

    @RequestMapping("/user")
    public String user(Model model){
        return "admin/user";
    }

    @RequestMapping(value = "/getWechatSign", method = RequestMethod.GET)
    @ResponseBody
    public Response getWechatSign(){
        WechatSign sign = service.getWechatSign();
        return Response.Result(0, sign);
    }


    @RequestMapping(value = "/getTableExtend", method = RequestMethod.GET)
    @ResponseBody
    public Response getTableExtend(@RequestParam String database, @RequestParam String table){
        TableExtend tableExtend = service.getTableExtend(database, table);
        return Response.Result(0, tableExtend);
    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    @ResponseBody
    public Response getMenu(@RequestParam String database){
        List<TableExtend> menuList = service.getMenu(database);
        return Response.Result(0, menuList);
    }

    @RequestMapping(value = "/getTableRowList", method = RequestMethod.POST)
    @ResponseBody
    public Response getTableRowList(@RequestBody QueryModel queryModel){
        String database = queryModel.getDatabase();
        String table = queryModel.getTable();
        SearchForm searchForm = searchFormService.getSearchForm(database, table);
        int pageLength = 15;
        List<Map<String, Object>> list = service.getTableRowList(queryModel);
        List<Column> columns = new ArrayList<>();
        List<Column> tmpColumns = service.getTableColumns(database, table);
        if( null != list && !list.isEmpty()){
            for(Map.Entry<String, Object> rowItem : list.get(0).entrySet()){
                String key = rowItem.getKey();
                for(Column column : tmpColumns){
                    if( key.equals( column.getCname() ) ){
                        columns.add( column );
                        break;
                    }
                }
            }
        }
        List<FieldExtend> fieldExtends = service.getFieldExtendList(database, table);
        Long tableCount = service.getTableCount(queryModel);
        Integer pageCount = (new Double( Math.ceil(Double.valueOf(tableCount)/Double.valueOf(pageLength)) )).intValue();
        Map<String, Object> resMap = new HashMap();
        resMap.put("list", list);
        resMap.put("columns", columns);
        resMap.put("fieldExtends", fieldExtends);
        resMap.put("page", queryModel.getPage());
        resMap.put("pageCount", pageCount);
        resMap.put("database", database);
        resMap.put("table", table);
        resMap.put("searchForm", searchForm);
        return Response.Result(0, resMap);
    }

    @RequestMapping(value = "/getTableRowDetail", method = RequestMethod.GET)
    @ResponseBody
    public Response getTableRowDetail(@RequestParam String database,@RequestParam String table, @RequestParam int id){
        List<Column> columns = new ArrayList<>();
        List<Column> tmpColumns = service.getTableColumns(database, table);
        Map<String, Object> res = service.getTableRowDetail(database, table, id);
        for(Map.Entry<String, Object> rowItem : res.entrySet()){
            String key = rowItem.getKey();
            for(Column column : tmpColumns){
                if( key.equals( column.getCname() ) ){
                    columns.add( column );
                    break;
                }
            }
        }
        List<FieldExtend> fieldExtends = service.getFieldExtendList(database, table);
        Map<String, Object> resMap = new HashMap();
        resMap.put("detail", res);
        resMap.put("columns", columns);
        resMap.put("fieldExtends", fieldExtends);
        return Response.Result(0, resMap);
    }

    @RequestMapping(value = "/deleteRow", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteRow(@RequestParam String database,@RequestParam String table, @RequestParam int id){
        boolean res = service.deleteRow(database, table, id);
        return Response.Result(0, res);
    }



    @RequestMapping(value = "/getInitFieldForm", method = RequestMethod.GET)
    @ResponseBody
    public Response getInitFieldForm(@RequestParam String database,@RequestParam String table){
        Map<String, Object> fieldMap = new LinkedHashMap<>();

        Map<String, Object> item = service.getTableRowDetail(database, table);
        List<Column> columns = new ArrayList<>();
        List<Column> tmpColumns = service.getTableColumns(database, table);
        if( null != item && !item.isEmpty()){
            for(Map.Entry<String, Object> rowItem : item.entrySet()){
                String key = rowItem.getKey();
                for(Column column : tmpColumns){
                    if( key.equals( column.getCname() ) ){
                        columns.add( column );
                        break;
                    }
                }
            }
        }

        if( columns.isEmpty() ){
            columns = tmpColumns;
        }
        for(Column column : columns){
            fieldMap.put( column.getCname(), "");
        }
        List<FieldExtend> fieldExtends = service.getFieldExtendList(database, table);
        Map<String, Object> res = new HashMap<>();
        res.put("extends", fieldExtends);
        res.put("fields", fieldMap);
        res.put("columns", columns);
        return Response.Result(0, res);
    }

    @RequestMapping(value = "/updateFieldForm", method = RequestMethod.POST)
    @ResponseBody
    public Response updateFieldForm(@RequestBody FieldForm fieldForm){
        Response res = Response.Error("update fail");
        try {
            service.updateFieldForm(fieldForm);
            res = Response.Result(0, "success");
        }catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/addFieldForm", method = RequestMethod.POST)
    @ResponseBody
    public Response addFieldForm(@RequestBody FieldForm fieldForm){
        Response res = Response.Error("add fail");
        try {
            service.addFieldForm(fieldForm);
            res = Response.Info("success");
        }catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping("/multiUpload")
    @ResponseBody
    public Response multiUpload(@RequestParam("file") MultipartFile file) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Common.SESSION_USER_KEY);
        String fileName = service.multiUpload(user, file);
        return new Response(0, null, fileName);

    }

}
