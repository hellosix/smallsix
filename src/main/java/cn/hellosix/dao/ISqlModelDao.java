package cn.hellosix.dao;

import cn.hellosix.model.SqlModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lzz on 2018/10/2.
 */
@Repository
public interface ISqlModelDao {
    SqlModel getSqlModelById(@Param("id")Integer id);
    SqlModel getSqlModel(@Param("apiName")String apiName);
    List<SqlModel> getSqlModelList(@Param("database")String database, @Param("table")String table);
    void addSqlModel(SqlModel sqlModel);
    void removeSqlModel(int id);
    void updateSqlModel(SqlModel sqlModel);
}
