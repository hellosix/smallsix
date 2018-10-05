package cn.hellosix.service;

import cn.hellosix.dao.IUserDao;
import cn.hellosix.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gl49 on 2018/4/20.
 */
@Service
public class UserService {
    @Autowired
    private IUserDao userDao;

    public User getUser(int id){
        return userDao.getUser( id );
    }

    public User getUser(String username){
        return userDao.getUserByName( username );
    }


    public boolean removeUser(int id){
        boolean res = false;
        try {
            userDao.removeUser( id );
            res = true;
        }catch (Exception e){

        }
        return res;
    }

    public boolean addUser(User user){
        boolean res = false;
        try {
            userDao.addUser( user );
            res = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean updateUser(User user) {
        User userModel = userDao.getUser( user.getId() );
        if( null == userModel ){
            userDao.addUser( user );
        }else{
            userDao.updateUser( user );
        }
        return true;
    }
}
