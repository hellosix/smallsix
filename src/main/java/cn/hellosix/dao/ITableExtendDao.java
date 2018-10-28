package cn.hellosix.dao;

import cn.hellosix.model.TableExtend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lzz on 2018/10/2.
 */
@Repository
public interface ITableExtendDao {
    List<TableExtend> getTableExtendList();
    List<TableExtend> getTableExtendByDatabase(String database);
    void addTableExtend(TableExtend tableExtend);
    void updateTableExtend(TableExtend tableExtend);
    void deleteTableExtend(@Param("databaseName")String databaseName, @Param("tableName")String tableName);
    TableExtend getTableExtendDetail(@Param("database")String database, @Param("table")String table);
}
