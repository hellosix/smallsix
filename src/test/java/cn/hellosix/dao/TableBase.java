package cn.hellosix.dao;

import cn.hellosix.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TableBase {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void getDatabaseTest(){
        List<String> databaseList = new ArrayList<>();
        List<Map<String, Object>> resList = jdbcTemplate.queryForList("show databases");
        for(Map<String, Object> item : resList){
            Object database = item.get("Database");
            databaseList.add((String) database);
        }
        System.out.println( databaseList );
    }

    @Test
    public void getTableTest(){
        String database = "test";
        List<String> tableList = new ArrayList<>();
        jdbcTemplate.execute("use " + database);
        List<Map<String, Object>> resList = jdbcTemplate.queryForList("show tables");
        for(Map<String, Object> item : resList){
            Object table = item.get("Tables_in_" + database);
            tableList.add((String) table);
        }
        System.out.println( tableList );
    }

    @Test
    public void getTableColumnsTest(){
        String database = "test";
        jdbcTemplate.execute("use " + database);
        jdbcTemplate.query("select * from sql_detail limit 1", new ResultSetExtractor<Object>() {
            @Override
            public Object extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                System.out.println( resultSet );
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for(int i = 1; i <= columnCount; i++){
                    String name = metaData.getColumnName( i );
                    String type = metaData.getColumnTypeName( i );
                    String lablel = metaData.getColumnLabel( i );
                    int size = metaData.getColumnDisplaySize( i );
                    String schemaName = metaData.getSchemaName( i );
                    System.out.println( name +  "--" + type + " -- " + lablel + "--size --" + size + "--schema_--" + schemaName);
                }
                return null;
            }
        });
    }

    @Test
    public void selectTest(){
        List<Map<String, Object>> res = jdbcTemplate.queryForList("select * from test.user2");
        System.out.println( res );
    }

    @Test
    public void test1(){
        String sql = "select * from id={id}";
        sql += " ";
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(23));
        param.put("name", "linzhouzhi");
        for(Map.Entry<String, String> item : param.entrySet()){
            String key = item.getKey();
            String value = item.getValue();
            String tempKey = "\\{" + key + "}";
            String[] sqlArr = sql.split( tempKey );
            if(sqlArr.length == 2){
                sql = sqlArr[0] + value + sqlArr[1];
            }
        }
        System.out.println( sql );
    }
}
