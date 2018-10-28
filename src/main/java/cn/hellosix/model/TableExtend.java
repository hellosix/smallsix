package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;

/**
 * Created by lzz on 2018/10/2.
 */
@MysqlTable( name = "table_extend", autoCreate = true)
public class TableExtend {
    @MysqlField(isPrimaryKey = true, field = "id", type = "int")
    private int id;
    @MysqlField(field = "database_name", type = "varchar(15)")
    private String databaseName;
    @MysqlField(field = "table_name", type = "varchar(15)")
    private String tableName;
    @MysqlField(field = "note", type = "varchar(15)")
    private String note;
    @MysqlField(field = "options", type = "varchar(250)")
    private String options;
    @MysqlField(field = "style", type = "varchar(255)")
    private String style;

    public TableExtend(){

    }

    public TableExtend(String databaseName, String tableName, String note){
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "TableExtend{" +
                "id=" + id +
                ", databaseName='" + databaseName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", note='" + note + '\'' +
                ", options='" + options + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
