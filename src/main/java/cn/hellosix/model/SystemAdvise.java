package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;
import cn.hellosix.util.TimeUtil;

/**
 * Created by lzz on 2018/10/20.
 */
@MysqlTable( name = "system_advise", autoCreate = true)
public class SystemAdvise {
    @MysqlField(isPrimaryKey = true, field = "id", type = "int")
    private Integer id;
    @MysqlField(field = "title", type = "varchar(50)")
    private String title;
    @MysqlField(field = "content", type = "text")
    private String content;
    @MysqlField(field = "publish", type = "int")
    private Integer publish;
    @MysqlField(field = "update_time", type = "int")
    private Integer updateTime;
    @MysqlField(field = "add_time", type = "int")
    private Integer addTime = TimeUtil.timeStamp();

    public SystemAdvise(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPublish() {
        return publish;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }
}
