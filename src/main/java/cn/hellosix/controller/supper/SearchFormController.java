package cn.hellosix.controller.supper;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Response;
import cn.hellosix.model.SearchForm;
import cn.hellosix.service.supper.SearchFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lzz on 2018/10/3.
 */
@Controller
@RequestMapping("/super")
@UserAccess
public class SearchFormController {
    @Autowired
    private SearchFormService service;

    @RequestMapping(value = "/getSearchForm", method = RequestMethod.GET)
    @ResponseBody
    public Response getSearchForm(@RequestParam String database, @RequestParam String table){
        SearchForm searchForm = service.getSearchForm(database, table);
        return Response.Result(0, searchForm);
    }

    @RequestMapping(value = "/updateSearchForm", method = RequestMethod.POST)
    @ResponseBody
    public Response updateSearchForm(@RequestBody SearchForm searchForm){
        boolean res = service.updateSearchForm( searchForm );
        if( res ){
            return Response.Info("success");
        }else{
            return Response.Error("error");
        }
    }
}
