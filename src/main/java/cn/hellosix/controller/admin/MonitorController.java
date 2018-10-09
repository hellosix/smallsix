package cn.hellosix.controller.admin;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.Response;
import cn.hellosix.service.admin.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/9.
 */
@Controller
@RequestMapping("/admin/monitor")
@UserAccess
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    @RequestMapping(value = "/getGroupByUserAgent", method = RequestMethod.GET)
    @ResponseBody
    public Response getGroupByUserAgent(@RequestParam String database,@RequestParam int startTime, @RequestParam int endTime){
        Map res = monitorService.getGroupByUserAgent(database, startTime, endTime);
        return Response.Result(0, res);
    }

    @RequestMapping(value = "/getGroupByCountUid", method = RequestMethod.GET)
    @ResponseBody
    public Response getGroupByCountUid(@RequestParam String database,@RequestParam int startTime, @RequestParam int endTime, @RequestParam String date){
        List res = monitorService.getGroupByCountUid(database, startTime, endTime, date);
        return Response.Result(0, res);
    }

    @RequestMapping(value = "/getGroupByCountTotal", method = RequestMethod.GET)
    @ResponseBody
    public Response getGroupByCountTotal(@RequestParam String database,@RequestParam int startTime, @RequestParam int endTime, @RequestParam String date){
        List res = monitorService.getGroupByCountTotal(database, startTime, endTime, date);
        return Response.Result(0, res);
    }

    @RequestMapping(value = "/getTotalCount", method = RequestMethod.GET)
    @ResponseBody
    public Response getTotalCount(@RequestParam String database,@RequestParam int startTime, @RequestParam int endTime){
        Map res = monitorService.getTotalCount(database, startTime, endTime);
        return Response.Result(0, res);
    }

    @RequestMapping(value = "/getMonitorDetail", method = RequestMethod.GET)
    @ResponseBody
    public Response getMonitorDetail(@RequestParam String database){
        List res = monitorService.getMonitorDetail(database);
        return Response.Result(0, res);
    }
}
