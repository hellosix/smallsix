package cn.hellosix.service.supper;

import cn.hellosix.dao.IUserDao;
import cn.hellosix.model.User;
import cn.hellosix.util.FileUtil;
import cn.hellosix.util.TimeUtil;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by gl49 on 2018/4/20.
 */
@Service
public class UserService {
    @Value("${smallsix.package.path.tmp}")
    private String resourceTmpPath;
    @Value("${smallsix.package.path.pack}")
    private String resourcePackPath;

    @Autowired
    private IUserDao userDao;

    public List<User> getUserList(){
        return userDao.getUserList();
    }

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

    public boolean updateUser(User user) throws IOException {
        //移动用户资源到package目录下
        movePackage(user);

        if( null == user.getId() ){
            userDao.addUser( user );
            return true;
        }
        User userModel = userDao.getUser( user.getId() );
        if( null == userModel ){
            userDao.addUser( user );
        }else{
            userDao.updateUser( user );
        }
        return true;
    }

    private void movePackage(User user) throws IOException {
        String head = user.getHead();
        if(StringUtils.isBlank(head)){
            return;
        }
        String database = user.getDatabaseName();
        String tmpPath = resourceTmpPath + database + "/";
        String packPath = resourcePackPath + database + "/";
        FileUtil.checkOrMkdirDir(tmpPath);
        FileUtil.checkOrMkdirDir(packPath);
        File fileFrom = new File(tmpPath + head);
        String rename = "user-head-" + TimeUtil.timeStamp() + "-" + head;
        user.setHead(rename);
        File fileTo = new File(packPath + rename);
        Files.move(fileFrom, fileTo);
    }
}
