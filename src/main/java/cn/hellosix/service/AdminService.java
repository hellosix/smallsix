package cn.hellosix.service;

import cn.hellosix.dao.ICommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzz on 2018/10/1.
 */
@Service
public class AdminService {
    @Autowired
    private ICommonDao commonDao;

    public List<String> getMenu(String database) {
        return commonDao.getTables( database );
    }
}
