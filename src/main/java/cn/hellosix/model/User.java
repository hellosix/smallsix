package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;
import cn.hellosix.util.TimeUtil;

/**
 * Created by lzz on 2018/10/4.
 */
@MysqlTable( name = "user", autoCreate = true)
public class User {
    @MysqlField(isPrimaryKey = true, field = "id", type = "int")
    private Integer id;
    @MysqlField(field = "username", type = "varchar(64)", notNull = true)
    private String username;
    @MysqlField(field = "password", type = "varchar(64)", notNull = true)
    private String password;
    @MysqlField(field = "head", type = "varchar(255)")
    private String head;
    @MysqlField(field = "phone", type = "varchar(15)")
    private String phone;
    @MysqlField(field = "wechat", type = "varchar(25)")
    private String wechat;
    @MysqlField(field = "email", type = "varchar(60)")
    private String email;
    @MysqlField(field = "service_time", type = "int")
    private Integer serviceTime;
    @MysqlField(field = "database_name", type = "varchar(255)")
    private String databaseName;
    @MysqlField(field = "menu", type = "text")
    private String menu;
    @MysqlField(field = "add_time", type = "int")
    private Integer addTime = TimeUtil.timeStamp();

    public User(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", head='" + head + '\'' +
                ", phone='" + phone + '\'' +
                ", wechat='" + wechat + '\'' +
                ", email='" + email + '\'' +
                ", serviceTime=" + serviceTime +
                ", databaseName='" + databaseName + '\'' +
                ", menu='" + menu + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}
