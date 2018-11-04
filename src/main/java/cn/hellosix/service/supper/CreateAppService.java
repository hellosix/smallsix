package cn.hellosix.service.supper;

import cn.hellosix.core.mysql.MysqlUtil;
import cn.hellosix.dao.ICommonDao;
import cn.hellosix.dao.ITableExtendDao;
import cn.hellosix.dao.IUserDao;
import cn.hellosix.model.AppMetaModel;
import cn.hellosix.model.TableExtend;
import cn.hellosix.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lzz on 2018/10/28.
 */
@Service
public class CreateAppService {
    @Value("${smallsix.mysql.database.prex}")
    private String databasePrex;

    @Autowired
    private ICommonDao commonDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ITableExtendDao tableExtendDao;

    public boolean createApp(AppMetaModel appMetaModel) throws IOException {
        String database = databasePrex+appMetaModel.getDatabase().trim();
        // add user
        userDao.addUser(new User(appMetaModel.getUsername().trim(), appMetaModel.getPassword().trim(), database));
        // create database
        commonDao.execute("CREATE DATABASE IF NOT EXISTS " + database + " DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci");
        String createMonitorTableSql = MysqlUtil.createTableSql( cn.hellosix.model.RestMonitorModel.class, database + ".monitor");
        commonDao.execute( createMonitorTableSql );
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> tableMap = objectMapper.readValue(appMetaModel.getTableObjStr().trim(), Map.class);
        for(Map.Entry<String, String> tableObj : tableMap.entrySet()){
            String table = tableObj.getKey().trim();
            String note = tableObj.getValue().trim();
            // create table
            commonDao.execute("CREATE TABLE IF NOT EXISTS " + database + "." +  table +" (id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT '" + note + "'");
            // add table_extend
            TableExtend tableExtend = new TableExtend(database, table, note);
            tableExtendDao.addTableExtend(tableExtend);
        }
        return true;
    }
}
