package cn.hellosix.service.supper;

import cn.hellosix.component.MonitorManager;
import cn.hellosix.core.userapi.UserApiUtil;
import cn.hellosix.dao.ICommonDao;
import cn.hellosix.dao.IRestMonitorDao;
import cn.hellosix.dao.ISqlModelDao;
import cn.hellosix.model.RestMonitorModel;
import cn.hellosix.model.SqlModel;
import cn.hellosix.util.HttpHeaderUtil;
import cn.hellosix.util.TimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    @Value("${smallsix.user.api}")
    private String userApi;

    private static Map<Integer, SqlModel> sqlMap = new ConcurrentHashMap<>();

    @Autowired
    private ISqlModelDao sqlModelDao;
    @Autowired
    private ICommonDao commonDao;
    @Autowired
    private IRestMonitorDao restMonitorDao;

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

    public SqlModel getSqlModel(Integer id){
        return sqlModelDao.getSqlModelById(id);
    }

    public List<SqlModel> getSqlModelList(String database, String table) {
        return sqlModelDao.getSqlModelList(database, table);
    }

    public String createJsSDK(String database, String table) {
        List<SqlModel> list = getSqlModelList(database, table);
        String apiStr = "window." + table + "= {\n";
        for(SqlModel sqlModel : list){
            apiStr += "  /**\n";
            apiStr += "   * " + sqlModel.getNote() + "\n";
            apiStr += "   * " + sqlModel.getParam() + "\n";
            apiStr += "   */\n";
            apiStr += "  " + sqlModel.getApiName() + ": function(data, callback){\n";
            apiStr += "    ajax.async_post(window.apiurl + \"" +  + sqlModel.getId() + "\" ,data ,callback);";
            apiStr += "\n  },\n";
        }
        apiStr += "\n}";
        UserApiUtil.writeStringToFile(userApiPath + database + "_" +  table + ".js", apiStr);
        return apiStr;
    }

    public Object apiRestful(Integer id, Map<String, Object> param, HttpServletRequest httpServletRequest) throws IOException {
        Object res = null;
        RestMonitorModel restMonitorModel = new RestMonitorModel();
        String msgType = "ERROR";
        String table = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String paramStr = objectMapper.writeValueAsString(param);
            restMonitorModel.setParams( paramStr );
            SqlModel sqlModel = sqlMap.get(id);
            if( null == sqlModel ){
                sqlModel = sqlModelDao.getSqlModelById(id);
                sqlMap.put(id, sqlModel);
            }
            sqlModel.setParam(paramStr);
            table = sqlModel.getDatabaseName() + ".monitor";
            restMonitorModel.setNote(sqlModel.getNote());
            String runSql = sqlModel.getSqlRunSql();
            res = runSqlStr( runSql );
            msgType = "INFO";

        }catch (Exception e){

        }finally {
            restMonitorModel.setApiId(id);
            restMonitorModel.setMsgType(msgType);
            restMonitorModel.setParams( param.toString() );
            restMonitorModel.setUserAgent(HttpHeaderUtil.getDeviceType(httpServletRequest) );
            restMonitorModel.setIp( HttpHeaderUtil.getClientIp(httpServletRequest));
            restMonitorModel.setUid( HttpHeaderUtil.getHelloSixid(httpServletRequest));
            restMonitorModel.setHour(TimeUtil.getHour());
            restMonitorModel.setDay(TimeUtil.getDay());
            restMonitorModel.setMinute(TimeUtil.getMinute());
            restMonitorModel.setAddTime(TimeUtil.timeStamp());
            restMonitorModel.setTable(table);
            boolean isSuccess = MonitorManager.addQueue( restMonitorModel );
            if( !isSuccess ) {
                restMonitorDao.addRestMonitorModel(table, restMonitorModel);
            }
        }
        return res;
    }


}
