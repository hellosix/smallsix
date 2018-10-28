package cn.hellosix.dao;

import cn.hellosix.model.FieldExtend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by lzz on 2018/10/1.
 */
@Repository
public interface IFieldExtendDao {
    List<FieldExtend> getFieldExtendList(@Param("database")String database, @Param("table")String table);
    FieldExtend getFieldExtendDetail(@Param("database")String database, @Param("table")String table, @Param("field")String field);
    void addFieldExtend(FieldExtend fieldExtend);
    void removeFieldExtend(@Param("database")String database, @Param("table")String table, @Param("field")String field);
    void updateFieldExtend(FieldExtend fieldExtend);
    void removeFieldExtendByid(int id);
}

