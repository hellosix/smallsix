package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;

/**
 * Created by lzz on 2018/10/3.
 */
@MysqlTable( name = "search_form", autoCreate = true)
public class SearchForm {
    @MysqlField(isPrimaryKey = true, field = "id", type = "int")
    private Integer id;
    @MysqlField(field = "table_name", type = "varchar(15)")
    private String tableName;
    @MysqlField(field = "database_name", type = "varchar(15)")
    private String databaseName;
    @MysqlField(field = "html_content", type = "text")
    private String htmlContent;
    @MysqlField(field = "active", type = "tinyint")
    private Integer active = 0;

    public SearchForm(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SearchForm{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", active=" + active +
                '}';
    }
}
