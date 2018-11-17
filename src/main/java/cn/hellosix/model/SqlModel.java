package cn.hellosix.model;

import cn.hellosix.core.mysql.MysqlField;
import cn.hellosix.core.mysql.MysqlTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/3.
 */
@MysqlTable( name = "sql_model", autoCreate = true)
public class SqlModel {
    @MysqlField(isPrimaryKey = true, field = "id", type = "int")
    protected int id;
    @MysqlField(field = "table_name", type = "varchar(15)")
    protected String tableName;
    @MysqlField(field = "database_name", type = "varchar(15)")
    protected String databaseName;
    @MysqlField(field = "note", type = "varchar(30)")
    protected String note;
    @MysqlField(field = "api_name", type = "varchar(25)")
    protected String apiName;
    @MysqlField(field = "sql_detail", type = "varchar(255)")
    protected String sqlDetail;
    @MysqlField(field = "param", type = "varchar(100)")
    protected String param;

    public SqlModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getSqlDetail() {
        return sqlDetail;
    }

    public void setSqlDetail(String sqlDetail) {
        this.sqlDetail = sqlDetail;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSqlRunSql() throws IOException {
        String sql = this.getSqlDetail() + " ";
        if( null == this.param || this.param.isEmpty() ){
            return sql;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> paramMap = objectMapper.readValue(this.param, Map.class);
        for(Map.Entry<String, Object> item : paramMap.entrySet()){
            String key = item.getKey();
            String value = String.valueOf(item.getValue());
            String tempKey = "\\{" + key + "}";
            String[] sqlArr = sql.split( tempKey );
            if( sqlArr.length < 2 ) continue;
            List<String> tmpList = new ArrayList();
            for(int i = 0; i < sqlArr.length - 1; i++){
                tmpList.add(sqlArr[i]);
                tmpList.add(value);
            }
            tmpList.add(sqlArr[sqlArr.length -1]);
            sql = Joiner.on("").join(tmpList);
        }
        return sql.trim();
    }

    @Override
    public String toString() {
        return "SqlModel{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", note='" + note + '\'' +
                ", apiName='" + apiName + '\'' +
                ", sqlDetail='" + sqlDetail + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
