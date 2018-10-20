package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;
import cn.hellosix.util.TimeUtil;

/**
 * Created by lzz on 2018/10/20.
 */
@MysqlTable( name = "user_notify", autoCreate = true)
public class UserNotify {
    @MysqlField(isPrimaryKey = true, field = "id", type = "int")
    private Integer id;
    @MysqlField(field = "message", type = "text")
    private String message;
    @MysqlField(field = "publish", type = "int")
    private Integer publish;
    @MysqlField(field = "read_over", type = "int")
    private Integer readOver;
    @MysqlField(field = "add_time", type = "int")
    private Integer addTime = TimeUtil.timeStamp();

    public UserNotify(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public Integer getReadOver() {
        return readOver;
    }

    public void setReadOver(Integer readOver) {
        this.readOver = readOver;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }
}
