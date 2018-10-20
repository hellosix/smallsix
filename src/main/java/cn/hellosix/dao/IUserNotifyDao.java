package cn.hellosix.dao;

import cn.hellosix.model.UserNotify;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lzz on 2018/10/2.
 */
@Repository
public interface IUserNotifyDao {
    List<UserNotify> getUserNotifyList();
    void addUserNotify(UserNotify userNotify);
    void updateUserNotify(UserNotify userNotify);
    UserNotify getUserNotify(@Param("id") int id);
    void removeUserNotify(int id);
}
