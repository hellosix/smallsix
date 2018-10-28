package cn.hellosix.model;

/**
 * Created by lzz on 2018/10/28.
 */
public class AppMetaModel {
    private String username;
    private String password;
    private String database;
    private String tableObjStr;

    public AppMetaModel(){

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

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTableObjStr() {
        return tableObjStr;
    }

    public void setTableObjStr(String tableObjStr) {
        this.tableObjStr = tableObjStr;
    }

    @Override
    public String toString() {
        return "AppMetaModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", database='" + database + '\'' +
                ", tableObjStr='" + tableObjStr + '\'' +
                '}';
    }
}
