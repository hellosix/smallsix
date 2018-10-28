package cn.hellosix.controller.supper;

import cn.hellosix.core.userapi.UserAccess;
import cn.hellosix.model.AppMetaModel;
import cn.hellosix.model.Response;
import cn.hellosix.service.supper.CreateAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by lzz on 2018/10/28.
 */
@Controller
@RequestMapping("/super")
@UserAccess
public class CreateAppController {
    @Autowired
    private CreateAppService createAppService;

    @RequestMapping(value = "/create-app", method = RequestMethod.POST)
    @ResponseBody
    public Response createApp(@RequestBody AppMetaModel appMetaModel) throws IOException {
        Boolean res = createAppService.createApp(appMetaModel);
        if(res){
            return Response.Info("success");
        }else{
            return Response.Error("fail");
        }
    }
}
