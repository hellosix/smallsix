package cn.hellosix.service.supper;

import cn.hellosix.core.userapi.UserApiUtil;
import cn.hellosix.dao.ICommonDao;
import cn.hellosix.dao.ISqlModelDao;
import cn.hellosix.model.SqlModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lzz on 2018/10/3.
 */
@Service
public class JSApiService {
    @Value("${smallsix.user.api.path}")
    private String userApiPath;

    private static Map<Integer, String> sqlMap = new ConcurrentHashMap<>();

    @Autowired
    private ISqlModelDao sqlModelDao;
    @Autowired
    private ICommonDao commonDao;

    public Object runSql(SqlModel sqlModel) throws IOException {
        String sql = sqlModel.getSqlRunSql();
        Object res = runSqlStr(sql);
        return res;
    }

    public Object runSqlStr(String sql){
        Object res;
        if( sql.indexOf("delete") > -1 ){
            res = commonDao.delete( sql );
        }else if( sql.indexOf("update") > -1 ){
            res = commonDao.update( sql );
        }else if( sql.indexOf("insert") > -1 ){
            res = commonDao.insert( sql );
        }else{
            res = commonDao.select( sql );
        }
        return res;
    }

    public boolean removeSql(int id) {
        sqlModelDao.removeSqlModel(id);
        return true;
    }

    public Boolean saveSql(SqlModel sqlModel) {
        SqlModel res = sqlModelDao.getSqlModel(sqlModel.getApiName());
        if( null == res ){
            sqlModelDao.addSqlModel( sqlModel );
        }else{
            sqlModelDao.updateSqlModel( sqlModel );
        }
        return true;
    }

    public List<SqlModel> getSqlModelList(String database, String table) {
        return sqlModelDao.getSqlModelList(database, table);
    }

    public String createJsSDK(String database, String table) {
        List<SqlModel> list = getSqlModelList(database, table);
        String apiStr = "var apiurl=''; \nvar " + table + "= {\n";
        for(SqlModel sqlModel : list){
            apiStr += sqlModel.getApiName() + ": function(data){\n";
            apiStr += "    ajax.post(data, apiurl + '/" +  + sqlModel.getId() + "' callback);";
            apiStr += "\n},\n";
        }
        apiStr += "\n}";
        UserApiUtil.writeStringToFile(userApiPath + table + ".js", apiStr);
        return apiStr;
    }

    public Object apiRestful(Integer id, Map<String, Object> param) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String paramStr = objectMapper.writeValueAsString(param);
        String sql = sqlMap.get(id);
        if( StringUtils.isBlank( sql )){
            SqlModel sqlModel = sqlModelDao.getSqlModelById(id);
            sql = sqlModel.getSqlDetail();
            sqlMap.put(id, sql);
        }
        SqlModel sqlModel = new SqlModel();
        sqlModel.setParam( paramStr );
        sqlModel.setSqlDetail( sql );
        String runSql = sqlModel.getSqlRunSql();
        Object res = runSqlStr( runSql );
        return res;
    }


}
