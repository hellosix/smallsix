package cn.hellosix.service.supper;

import cn.hellosix.dao.ICommonDao;
import cn.hellosix.dao.ITableExtendDao;
import cn.hellosix.model.TableExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/10/28.
 */
@Service
public class TableAdminService {
    @Autowired
    private ICommonDao commonDao;
    @Autowired
    private ITableExtendDao tableExtendDao;

    public List<TableExtend> getTableExtendList(String database){
        List<TableExtend> result = new ArrayList<>();
        List<String> tables = commonDao.getTables(database);
        List<TableExtend> tableExtendList = tableExtendDao.getTableExtendByDatabase(database);
        for(String table : tables){
            TableExtend tableExtendObj = new TableExtend();
            tableExtendObj.setTableName( table );
            tableExtendObj.setDatabaseName(database);
            for(TableExtend tableExtend : tableExtendList){
                if( tableExtend.getTableName().equals(table) ){
                    tableExtendObj = tableExtend;
                    break;
                }
            }
            result.add( tableExtendObj );
        }
        return result;
    }

    public Boolean addTableAndExtend(TableExtend tableExtend) {
        String database = tableExtend.getDatabaseName();
        String table = tableExtend.getTableName();
        String note = tableExtend.getNote();
        String sql = "CREATE TABLE IF NOT EXISTS " + database + "." +  table +" (id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT '" + note + "'";
        commonDao.execute( sql );
        tableExtendDao.addTableExtend( tableExtend );
        return true;
    }

    public boolean deleteTableAndExtend(String database, String table) {
        String dropTable = "drop table " + database + "." + table;
        commonDao.execute( dropTable );
        tableExtendDao.deleteTableExtend(database, table);
        return true;
    }
}
