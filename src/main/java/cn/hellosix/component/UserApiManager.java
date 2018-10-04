package cn.hellosix.component;

import cn.hellosix.core.userapi.UserApiUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/10/2.
 */
@Component
public class UserApiManager implements ApplicationListener<ContextRefreshedEvent>{
    @Value("${smallsix.user.api.path}")
    private String userApiPath;
    @Value("${smallsix.userapi.scan.package}")
    private String scanPackage;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initUserApi();
    }

    /**
     * 初始化用户 api
     */
    public void initUserApi(){
        List<String> packages = new ArrayList<>();
        packages.add(scanPackage);
        String file = userApiPath + "userApi.js";
        UserApiUtil.autoGeneriesAllApi( packages, file);
    }
}
