package cn.hellosix.service.supper;

import cn.hellosix.dao.ICommonDao;
import cn.hellosix.dao.IFieldExtendDao;
import cn.hellosix.dao.ITableExtendDao;
import cn.hellosix.model.Column;
import cn.hellosix.model.ColumnExtend;
import cn.hellosix.model.FieldExtend;
import cn.hellosix.model.TableExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/10/1.
 */
@Service
public class SuperService {
    @Autowired
    private ICommonDao commonDao;
    @Autowired
    private IFieldExtendDao fieldExtendDao;
    @Autowired
    private ITableExtendDao tableExtendDao;

    public List<String> getMenu() {
        return commonDao.getDatabases();
    }

    public List<String> getSubMenu(String database) {
        return commonDao.getTables(database);
    }

    public List<ColumnExtend> getColumnExtendList(String database, String table) {
        List<ColumnExtend> resList = new ArrayList<>();
        List<FieldExtend> fields = fieldExtendDao.getFieldExtendList(database, table);
        List<Column> columns = commonDao.getColumns(database, table);
        for(Column column : columns){
            ColumnExtend columnExtend = new ColumnExtend();
            columnExtend.setColumn( column );
            String name = column.getName();
            for(FieldExtend fieldExtend : fields){
                if( name.equals( fieldExtend.getFieldName()) ){
                    columnExtend.setFieldExtend( fieldExtend );
                    break;
                }
            }
            resList.add( columnExtend );
        }
        return resList;
    }

    public Boolean updateFieldExtend(FieldExtend fieldExtend){
        int id = fieldExtend.getId();
        if( id == 0 ){
            fieldExtendDao.addFieldExtend( fieldExtend );
        }else{
            fieldExtendDao.updateFieldExtend( fieldExtend );
        }
        return true;
    }

    public boolean updateTableExtend(TableExtend tableExtend) {
        TableExtend tableExtendDetail = tableExtendDao.getTableExtendDetail(tableExtend.getDatabaseName(), tableExtend.getTableName());
        if( null == tableExtendDetail ){
            tableExtendDao.addTableExtend( tableExtend );
        }else{
            tableExtendDao.updateTableExtend( tableExtend );
        }
        return true;
    }

    public TableExtend getTableExtendDetail(String database, String table) {
        TableExtend tableDetail = tableExtendDao.getTableExtendDetail(database, table);
        if( null == tableDetail ){
            tableDetail = new TableExtend();
        }
        return tableDetail;
    }
}
