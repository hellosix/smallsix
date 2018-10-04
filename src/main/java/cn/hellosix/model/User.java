package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;

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

    public User(){

    }

    public User(Integer id, String username, String password, String head) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.head = head;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", head='" + head + '\'' +
                '}';
    }
}
