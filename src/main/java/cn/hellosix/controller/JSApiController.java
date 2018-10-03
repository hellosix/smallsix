package cn.hellosix.controller;

import cn.hellosix.core.userapi.UserAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lzz on 2018/10/3.
 */
@Controller
@RequestMapping("/admin")
@UserAccess
public class JSApiController {
}
