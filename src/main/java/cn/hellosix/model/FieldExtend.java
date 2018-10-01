package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;

/**
 * Created by lzz on 2018/10/1.
 */
@MysqlTable( name = "field_extend", autoCreate = true)
public class FieldExtend {
    @MysqlField(isPrimaryKey = true, field = "id", type = "int")
    private int id;
    @MysqlField(field = "field_name", type = "varchar(15)")
    private String fieldName;
    @MysqlField(field = "table_name", type = "varchar(15)")
    private String tableName;
    @MysqlField(field = "database_name", type = "varchar(15)")
    private String databaseName;
    @MysqlField(field = "note", type = "varchar(15)")
    private String note;
    @MysqlField(field = "type", type = "varchar(15)")
    private String type;
    @MysqlField(field = "active", type = "tinyint")
    private int active;
    @MysqlField(field = "valitate", type = "varchar(255)")
    private String valitate;

    public FieldExtend(){

    }

    public FieldExtend(int id, String fieldName, String tableName, String databaseName, String note, String type, int active, String valitate) {
        this.id = id;
        this.fieldName = fieldName;
        this.tableName = tableName;
        this.databaseName = databaseName;
        this.note = note;
        this.type = type;
        this.active = active;
        this.valitate = valitate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getValitate() {
        return valitate;
    }

    public void setValitate(String valitate) {
        this.valitate = valitate;
    }

    @Override
    public String toString() {
        return "FieldExtend{" +
                "id=" + id +
                ", fieldName='" + fieldName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", note='" + note + '\'' +
                ", type='" + type + '\'' +
                ", active=" + active +
                ", valitate='" + valitate + '\'' +
                '}';
    }
}
