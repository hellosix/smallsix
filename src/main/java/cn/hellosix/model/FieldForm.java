package cn.hellosix.model;

import cn.hellosix.util.TimeUtil;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/3.
 */
public class FieldForm {
    private String database;
    private String table;
    private List<Column> columns;
    private Map<String, Object> fieldMap;

    public FieldForm(){

    }

    public FieldForm(String database, String table, List<Column> columns, Map<String, Object> fieldMap) {
        this.database = database;
        this.table = table;
        this.columns = columns;
        this.fieldMap = fieldMap;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public String insertSql(){
        String sql = "insert into " + this.database + "." + this.table;
        List<String> fields = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for(Map.Entry<String, Object> item : fieldMap.entrySet()){
            String field = item.getKey();
            fields.add( field );
            Object value = item.getValue();
            values.add( getObjectStr(field, value) );
        }
        sql += "(" + Joiner.on(",").join(fields) + ")" + "values(" + Joiner.on(",").join(values) + ")";
        return sql;
    }

    public String getSql(){
        String sql = "select * from "+ this.database + "." + this.table + " where id=" + this.fieldMap.get("id");
        return sql;
    }

    public String updateSql(){
        String sql = "update "+ this.database + "." + this.table + " set ";
        List<String> modifyList = new ArrayList<>();
        for(Map.Entry<String, Object> item : fieldMap.entrySet()){
            String key = item.getKey();
            Object value = item.getValue();
            modifyList.add( key + "=" + getObjectStr(key, value) );
        }
        sql += Joiner.on(",").join(modifyList) + " where id=" + this.fieldMap.get("id");
        System.out.println( sql );
        return sql;
    }

    public String getObjectStr(String key, Object object){
        String res = "";
        for(Column column : this.columns){
            String value = String.valueOf(object);
            if( column.getName().equals(key) ){
                if( column.getType().contains("CHAR") ){
                    res = "'" + value + "'";
                }else if( column.getType().contains("DATETIME") ){
                    String time = TimeUtil.timeStamp2Date(value, "yyyy-MM-dd HH:mm:ss");
                    res = "'" + time + "'";
                }else{
                    res = value;
                }
            }
        }
        return res;
    }
}
