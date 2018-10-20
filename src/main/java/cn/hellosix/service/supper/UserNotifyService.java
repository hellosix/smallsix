package cn.hellosix.service.supper;

import cn.hellosix.dao.IUserNotifyDao;
import cn.hellosix.model.UserNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzz on 2018/10/20.
 */
@Service
public class UserNotifyService {
    @Autowired
    private IUserNotifyDao userNotifyDao;

    public List<UserNotify> getUserNotifyList(){
        return userNotifyDao.getUserNotifyList();
    }

    public void addUserNotify(UserNotify userNotify){
        userNotifyDao.addUserNotify(userNotify);
    }

    public void updateUserNotify(UserNotify userNotify){
        if( null == userNotify.getId() ){
            userNotifyDao.addUserNotify(userNotify);
            return;
        }
        UserNotify userNotify1 = userNotifyDao.getUserNotify( userNotify.getId() );
        if( null == userNotify1 ){
            userNotifyDao.addUserNotify(userNotify);
        }else{
            userNotifyDao.updateUserNotify(userNotify);
        }
    }

    public UserNotify getUserNotify(int id){
        return userNotifyDao.getUserNotify(id);
    }

    public void removeUserNotify(int id){
        userNotifyDao.removeUserNotify(id);
    }
}
