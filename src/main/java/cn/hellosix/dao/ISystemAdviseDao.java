package cn.hellosix.dao;

import cn.hellosix.model.SystemAdvise;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lzz on 2018/10/2.
 */
@Repository
public interface ISystemAdviseDao {
    List<SystemAdvise> getSystemAdviseList();
    void addSystemAdvise(SystemAdvise systemAdvise);
    void updateSystemAdvise(SystemAdvise systemAdvise);
    SystemAdvise getSystemAdvise(@Param("id") int id);
    void removeSystemAdvise(int id);
}
