package cn.hellosix.service.admin;

import cn.hellosix.dao.ICommonDao;
import cn.hellosix.dao.IFieldExtendDao;
import cn.hellosix.dao.ITableExtendDao;
import cn.hellosix.model.*;
import cn.hellosix.util.FileUtil;
import cn.hellosix.util.TimeUtil;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/1.
 */
@Service
public class AdminService {
    @Value("${smallsix.package.path.tmp}")
    private String resourceTmpPath;
    @Value("${smallsix.package.path.pack}")
    private String resourcePackPath;

    @Autowired
    private ICommonDao commonDao;
    @Autowired
    private ITableExtendDao tableExtendDao;
    @Autowired
    private IFieldExtendDao fieldExtendDao;

    public TableExtend getTableExtend(String database, String table) {
        return tableExtendDao.getTableExtendDetail(database, table);
    }

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

    public Map<String, Object> getTableRowDetail(String database, String table, int id){
        List<Map<String, Object>> list = commonDao.select( "select * from " + database + "." + table + " where id=" + id);
        if( null == list || list.isEmpty() ){
            return null;
        }
        return list.get(0);
    }

    public Map<String, Object> getTableRowDetail(String database, String table){
        List<Map<String, Object>> list = commonDao.select( "select * from " + database + "." + table + " limit 1");
        if( null == list || list.isEmpty() ){
            return null;
        }
        return list.get(0);
    }

    public boolean deleteRow(String database, String table, int id){
        commonDao.delete("delete from "+ database + "." + table + " where id=" + id);
        return true;
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

    public Boolean updateFieldForm(FieldForm fieldForm) throws IOException {
        movePackage(fieldForm);
        if( null != fieldForm.getFieldMap().get("id") ){
            String sql = fieldForm.updateSql();
            commonDao.update(sql);
        }else{
            String insertSql = fieldForm.insertSql();
            commonDao.insert( insertSql );
        }
        return true;
    }

    public String multiUpload(User user, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String dir = resourceTmpPath + user.getDatabaseName() + "/";
        FileUtil.checkOrMkdirDir(dir);
        File dest = new File( dir + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }

    private void movePackage(FieldForm fieldForm) throws IOException {
        String database = fieldForm.getDatabase();
        String table = fieldForm.getTable();
        Map<String, Object> fields = fieldForm.getFieldMap();
        String tmpPath = resourceTmpPath + database + "/";
        String packPath = resourcePackPath + database + "/";
        FileUtil.checkOrMkdirDir(tmpPath);
        FileUtil.checkOrMkdirDir(packPath);
        // 看临时目录有多少上传到文件
        List<String> files = FileUtil.getFiles(tmpPath);
        for(Map.Entry<String, Object> field : fields.entrySet()){
            String valueStr = String.valueOf(field.getValue());
            String key = field.getKey();
            //有可能多个上传
            String[] values = valueStr.split(",");
            List<String> renameList = new ArrayList<>();
            boolean isFile = false;
            for(String file : files) {
                for(String value : values){
                    String tmpValue = value;
                    if( file.equals( value) ){
                        try {
                            isFile = true;
                            File fileFrom = new File(tmpPath + file);
                            // 重命名避免文件被覆盖
                            String rename = table + "-" + key + "-" + TimeUtil.timeStamp() + "-" + value;
                            tmpValue = rename;
                            File fileTo = new File(packPath + rename);
                            Files.move(fileFrom, fileTo);
                            FileUtil.deleteFile( packPath + value ); //删除原有到图片
                            renameList.add( tmpValue );
                        }catch (Exception ignore){

                        }
                    }
                }
            }
            //命名后重改原来到值
            if( !renameList.isEmpty() && isFile ){
                field.setValue(Joiner.on(",").join(renameList) );
            }
        }
    }
}
