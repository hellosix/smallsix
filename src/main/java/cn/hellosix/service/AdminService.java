package cn.hellosix.service;

import cn.hellosix.dao.ICommonDao;
import cn.hellosix.dao.IFieldExtendDao;
import cn.hellosix.dao.ITableExtendDao;
import cn.hellosix.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/1.
 */
@Service
public class AdminService {
    @Autowired
    private ICommonDao commonDao;
    @Autowired
    private ITableExtendDao tableExtendDao;
    @Autowired
    private IFieldExtendDao fieldExtendDao;

    public List<TableExtend> getMenu(String database) {
        List<TableExtend> tableExtendList = new ArrayList<>();
        List<TableExtend> tableExtends = tableExtendDao.getTableExtendList();
        List<String> tables = commonDao.getTables( database );
        for(String table : tables){
            TableExtend tableExtend = new TableExtend();
            tableExtend.setTableName( table );
            for(TableExtend tableExtendItem : tableExtends){
                if( table.equals( tableExtendItem.getTableName() ) ){
                    tableExtend = tableExtendItem;
                    break;
                }
            }
            tableExtendList.add( tableExtend );
        }
        return tableExtendList;
    }

    public List<Map<String, Object>> getTableRowList(QueryModel queryModel) {
        String sql = queryModel.querySql();
        System.out.println(sql);
        return commonDao.select(sql);
    }

    public List<Column> getTableColumns(String database, String table) {
        List<Column> list = commonDao.getColumns(database, table);
        return list;
    }

    public List<FieldExtend> getFieldExtendList(String database, String table) {
        return fieldExtendDao.getFieldExtendList(database, table);
    }

    public Long getTableCount(QueryModel queryModel) {
        String sql = queryModel.countSql();
        return commonDao.getCount(sql);
    }

    public Boolean updateFieldForm(FieldForm fieldForm) {
        if( null != fieldForm.getFieldMap().get("id") ){
            String sql = fieldForm.updateSql();
            commonDao.update(sql);
        }else{
            String insertSql = fieldForm.insertSql();
            commonDao.insert( insertSql );
        }
        return true;
    }
}
