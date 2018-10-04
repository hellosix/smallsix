package cn.hellosix.dao.impl;

import cn.hellosix.dao.ICommonDao;
import cn.hellosix.model.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/1.
 */
@Component
public class CommonDao implements ICommonDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 获取数据库列表
     * @return
     */
    public List<String> getDatabases(){
        List<String> databaseList = new ArrayList<>();
        List<Map<String, Object>> resList = jdbcTemplate.queryForList("show databases");
        for(Map<String, Object> item : resList){
            Object database = item.get("Database");
            databaseList.add((String) database);
        }
        return databaseList;
    }

    /**
     * 获取数据库表名
     * @param database
     * @return
     */
    public List<String> getTables(String database){
        List<String> tableList = new ArrayList<>();
        jdbcTemplate.execute("use " + database);
        List<Map<String, Object>> resList = jdbcTemplate.queryForList("show tables");
        for(Map<String, Object> item : resList){
            Object table = item.get("Tables_in_" + database);
            tableList.add((String) table);
        }
        return tableList;
    }

    /**
     * 获取列
     * @param database
     * @param table
     * @return
     */
    public List<Column> getColumns(String database, String table){
        jdbcTemplate.execute("use " + database);
        return jdbcTemplate.query("select * from " + table + " limit 1", new ResultSetExtractor<List<Column>>() {
            @Override
            public List<Column> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                List<Column> columns = new ArrayList<>();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for(int i = 1; i <= columnCount; i++){
                    String name = metaData.getColumnName( i );
                    String type = metaData.getColumnTypeName( i );
                    int size = metaData.getColumnDisplaySize( i );
                    Column column = new Column(name, type, size);
                    columns.add( column );
                }
                return columns;
            }
        });
    }

    /**
     * 查询
     * @param sql
     * @return
     */
    public List<Map<String, Object>> select(String sql){
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 更新
     * @param sql
     * @return
     */
    public int update(String sql){
        return jdbcTemplate.update( sql );
    }

    /**
     * 插入
     * @param sql
     * @return
     */
    public int insert(String sql){
        return jdbcTemplate.update( sql );
    }

    @Override
    public Long getCount(String sql) {
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        Map<String, Object> item = res.get(0);
        Long count = (Long) item.get( "c" );
        return count;
    }

    @Override
    public Object execute(String sql) {
        return null;
    }

    @Override
    public Object delete(String sql) {
        jdbcTemplate.execute(sql);
        return true;
    }
}
