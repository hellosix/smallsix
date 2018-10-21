package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;

/**
 * Created by lzz on 2018/10/1.
 */
@MysqlTable( name = "field_extend", autoCreate = true)
public class FieldExtend extends Column{
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
    @MysqlField(field = "type", type = "varchar(255)")
    private String type;
    @MysqlField(field = "active", type = "tinyint")
    private int active = 1;
    @MysqlField(field = "valitate", type = "varchar(255)")
    private String valitate;
    @MysqlField(field = "style", type = "varchar(255)")
    private String style;
    @MysqlField(field = "value_style", type = "varchar(255)")
    private String valueStyle;
    @MysqlField(field = "value_init", type = "varchar(255)")
    private String valueInit;


    public FieldExtend(){

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

    @Override
    public String getType() {
        return type;
    }

    @Override
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getValueStyle() {
        return valueStyle;
    }

    public void setValueStyle(String valueStyle) {
        this.valueStyle = valueStyle;
    }

    public String getValueInit() {
        return valueInit;
    }

    public void setValueInit(String valueInit) {
        this.valueInit = valueInit;
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
                ", style='" + style + '\'' +
                ", valueStyle='" + valueStyle + '\'' +
                ", valueInit='" + valueInit + '\'' +
                '}';
    }
}
