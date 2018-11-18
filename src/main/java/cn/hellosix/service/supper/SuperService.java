package cn.hellosix.service.supper;

import cn.hellosix.dao.ICommonDao;
import cn.hellosix.dao.IFieldExtendDao;
import cn.hellosix.dao.ITableExtendDao;
import cn.hellosix.model.Column;
import cn.hellosix.model.ColumnExtend;
import cn.hellosix.model.FieldExtend;
import cn.hellosix.model.TableExtend;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/10/1.
 */
@Service
public class SuperService {
    @Value("${smallsix.mysql.database.prex}")
    private String databasePrex;

    @Autowired
    private ICommonDao commonDao;
    @Autowired
    private IFieldExtendDao fieldExtendDao;
    @Autowired
    private ITableExtendDao tableExtendDao;

    public List<String> getMenu() {
        List<String> resultList = new ArrayList<>();
        List<String> databases = commonDao.getDatabases();
        for(String database : databases){
            if( database.indexOf(databasePrex) == 0 ){
                resultList.add( database );
            }
        }
        return resultList;

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
            String name = column.getCname();
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

    public List<String> columns(String database, String table){
        List<String> list = new ArrayList<>();
        List<Column> columns = commonDao.getColumns(database, table);
        for(Column column : columns){
            list.add( column.getCname() );
        }
        return list;
    }
    public ColumnExtend getColumnExtendDetail(String database, String table, String fieldName) {
        ColumnExtend columnExtend = new ColumnExtend();
        FieldExtend field = fieldExtendDao.getFieldExtendDetail(database, table, fieldName);
        if( null == field ){
            field = new FieldExtend();
        }
        List<Column> columns = commonDao.getColumns(database, table);
        for(Column column : columns){
            columnExtend.setColumn( column );
            String name = column.getCname();
            if( name.equals( fieldName) ){
                columnExtend.setFieldExtend( field );
                break;
            }
        }
        return columnExtend;
    }

    public Boolean updateFieldExtend(FieldExtend fieldExtend){
        FieldExtend extendObj = fieldExtendDao.getFieldExtendDetail(fieldExtend.getDatabaseName(), fieldExtend.getTableName(), fieldExtend.getFieldName());
        if( null == extendObj ){
            //删除旧的
            fieldExtendDao.removeFieldExtend(fieldExtend.getDatabaseName(), fieldExtend.getTableName(), fieldExtend.getFieldName());
            fieldExtend.setFieldName( fieldExtend.getReName() );
            addFieldExtend(fieldExtend);
        }else{
            fieldExtendDao.updateFieldExtend( fieldExtend );
        }
        return true;
    }

    public Boolean addFieldExtend(FieldExtend fieldExtend){
        fieldExtendDao.addFieldExtend( fieldExtend );
        return true;
    }

    public Boolean deleteFiedldExtendById(int id){
        fieldExtendDao.removeFieldExtendByid(id);
        return true;
    }

    public Boolean addColumn(Column column){
        String sql = "alter table " + column.getDatabase() + "." + column.getTable() + " add " + column.getCname() + " " + column.getCtype();
        if( !column.getCtype().toLowerCase().contains("text") && !column.getCtype().toLowerCase().contains("time") ){
            sql +=  "(" + column.getSize() + ")";
        }
        if( !StringUtils.isBlank(column.getAfterField()) && !column.getAfterField().equals("latest") ){
            sql += " after " + column.getAfterField();
        }
        commonDao.execute(sql);
        return true;
    }

    public Boolean updateColumn(Column column){
        String rename = column.getReName();
        if( StringUtils.isBlank(rename) ){
            rename = column.getCname();
        }
        if( column.getCname().equals("id") ){
            return true;
        }
        String sql = "alter table " + column.getDatabase() + "." + column.getTable() + " change " + column.getCname() + " " + rename + " " + column.getCtype();
        if( !column.getCtype().toLowerCase().contains("text") && !column.getCtype().toLowerCase().contains("time") ){
            sql +=  "(" + column.getSize() + ")";
        }
        if( !column.getCname().equals(column.getAfterField()) ){
            if( !StringUtils.isBlank(column.getAfterField()) && !column.getAfterField().equals("latest") ){
                sql += " after " + column.getAfterField();
            }
        }
        System.out.println( sql );
        commonDao.execute(sql);
        return true;
    }

    public Boolean dropColumn(Column column){
        String sql = "alter table " + column.getDatabase() + "." + column.getTable()    + " drop " + column.getCname();
        commonDao.execute( sql );
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
