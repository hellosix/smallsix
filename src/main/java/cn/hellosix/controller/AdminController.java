package cn.hellosix.controller;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.*;
import cn.hellosix.service.AdminService;
import cn.hellosix.service.SearchFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping( value = "/index" )
    public String index(Model model){
        return "admin/index";
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
                    if( key.equals( column.getName() ) ){
                        columns.add( column );
                        break;
                    }
                }
            }
        }
        List<FieldExtend> fieldExtends = service.getFieldExtendList(database, table);
        Long tableCount = service.getTableCount(queryModel);
        Integer pageCount = (new Double( Math.ceil(tableCount/pageLength) )).intValue();
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

    @RequestMapping(value = "/updateFieldForm", method = RequestMethod.POST)
    @ResponseBody
    public Response updateFieldForm(@RequestBody FieldForm fieldForm){
        Boolean res = service.updateFieldForm(fieldForm);
        return Response.Info("success");
    }
}
