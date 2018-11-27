package cn.hellosix.util;

import cn.hellosix.model.WechatSign;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 微信分享签名
 *
 */
public class WechatUtil {
    private static String url = "http://smurf7.com";
    private static String corpid = "wx18a1a4265b89b855";
    private static String corpsecret = "rIBc9ZnLRa40MRsnA6_WHLNgey4wIoUqp_Vq4lmwTrM";
    public static void main(String[] args) {
        WechatSign res = sign( url );
        System.out.println( res );
    }


    private static String getToken(){
        String accessToken = "";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
        RestTemplate HttpClient = new RestTemplate();
        Map res = HttpClient.getForObject(url, Map.class);
        accessToken = String.valueOf(res.get("access_token"));
        return accessToken;
    }

    private static String getTicket(){
        RestTemplate HttpClient = new RestTemplate();
        String ticket = "";
        String url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=" + getToken();
        Map res = HttpClient.getForObject(url, Map.class);
        ticket = String.valueOf(res.get("ticket"));
        return ticket;
    }

    public static WechatSign sign(String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + getTicket() +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        WechatSign wechatSign = new WechatSign();
        wechatSign.setUrl(url);
        wechatSign.setAppId(corpid);
        wechatSign.setNonceStr(nonce_str);
        wechatSign.setSignature(signature);
        wechatSign.setTimestamp(timestamp);
        return wechatSign;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}