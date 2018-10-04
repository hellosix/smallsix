package cn.hellosix.dao;

import cn.hellosix.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by lzz on 2018/10/4.
 */
@Repository
public interface IUserDao {
    User getUser(int id);
    void removeUser(int id);
    void addUser(User user);
    User getUserByName(String username);
}