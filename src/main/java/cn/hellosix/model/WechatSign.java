package cn.hellosix.model;

/**
 * Created by lzz on 2018/11/27.
 */
public class WechatSign {
    private String signature;
    private String appId;
    private String url;
    private String nonceStr;
    private String timestamp;

    public WechatSign(){

    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WechatSign{" +
                "signature='" + signature + '\'' +
                ", appId='" + appId + '\'' +
                ", url='" + url + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
