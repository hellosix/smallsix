package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;

/**
 * Created by lzz on 2018/10/8.
 */
@MysqlTable( name = "rest_monitor", autoCreate = false, engine="MyISAM")
public class RestMonitorModel {
    @MysqlField(isPrimaryKey = true, field = "id", type = "int")
    private int id;
    @MysqlField(field = "ip", type = "varchar(25)")
    private String ip;
    @MysqlField(field = "uid", type = "varchar(255)")
    private String uid;
    @MysqlField(field = "msg_type", type = "varchar(255)")
    private String msgType;
    @MysqlField(field = "url", type = "varchar(255)")
    private String url;
    @MysqlField(field = "note", type = "varchar(255)")
    private String note;
    @MysqlField(field = "params", type = "varchar(255)")
    private String params;
    @MysqlField(field = "user_agent", type = "varchar(255)")
    private String userAgent;
    @MysqlField(field = "day", type = "int")
    private int day;
    @MysqlField(field = "hour", type = "tinyint")
    private int hour;
    @MysqlField(field = "minute", type = "tinyint")
    private int minute;
    @MysqlField(field = "add_time", type = "int")
    private int addTime;


    public RestMonitorModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "RestMonitorModel{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", uid='" + uid + '\'' +
                ", msgType='" + msgType + '\'' +
                ", url='" + url + '\'' +
                ", note='" + note + '\'' +
                ", params='" + params + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", addTime=" + addTime +
                '}';
    }
}
