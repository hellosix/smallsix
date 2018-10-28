package cn.hellosix.dao;

import cn.hellosix.model.Column;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/1.
 */
@Repository
public interface ICommonDao {
    List<String> getDatabases();
    List<String> getTables(String database);
    List<Column> getColumns(String database, String table);
    List<Map<String, Object>> select(String sql);
    int update(String sql);
    int insert(String sql);
    Long getCount(String sql);
    void execute(String sql);
    Object delete(String sql);
}
