package cn.hellosix.dao;

import cn.hellosix.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lzz on 2018/10/4.
 */
@Repository
public interface IUserDao {
    List<User> getUserList();
    User getUser(int id);
    void removeUser(int id);
    void addUser(User user);
    void updateUser(User user);
    User getUserByName(String username);
}