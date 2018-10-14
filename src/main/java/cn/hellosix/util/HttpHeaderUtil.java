package cn.hellosix.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lzz on 2018/10/10.
 */
public class HttpHeaderUtil {
    /**
     * 判断 移动端/PC端
     * @Title: isMobile
     * @param request
     * @return
     * @return: boolean
     */
    public static boolean isMobile(HttpServletRequest request) {
        List<String> mobileAgents = Arrays.asList("ipad", "iphone os", "rv:1.2.3.4", "ucweb", "android", "windows ce", "windows mobile");
        String ua = request.getHeader("User-Agent").toLowerCase();
        for (String sua : mobileAgents) {
            if (ua.indexOf(sua) > -1) {
                return true;//手机端
            }
        }
        return false;//PC端
    }

    /**
     * 是否微信浏览器
     * @Title: isWechat
     * @param request
     * @return
     * @return: boolean
     */
    public static boolean isWechat(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent").toLowerCase();
        if (ua.indexOf("micromessenger") > -1) {
            return true;//微信
        }
        return false;//非微信手机浏览器

    }

    public static String getClientIp(HttpServletRequest request){
        String clientIp = request.getHeader("x-forwarded-for");
        return clientIp;
    }

    public static String getCookieValue(HttpServletRequest request, String key){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if( key.equals(cookieName) ){
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    public static String getHelloSixid(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if( "hellosixid".equals(cookieName) ){
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    public static String getDeviceType(HttpServletRequest request){
        if( isWechat(request) ){
            return "wechat";
        }else if( isMobile(request) ){
            return "mobile";
        }else{
            return "web";
        }
    }

}
