/******************************** cn.hellosix.controller.supper.SystemAdviseController ********************************/
/**
 * @type GET
 */
function  getSystemAdviseList(callback){
   ajax.async_get("/systemAdvise/getSystemAdviseList",callback);
}
/**
 * @type GET 
 * @param  int
 */
function  getSystemAdvise(id,callback){
   ajax.async_get("/systemAdvise/getSystemAdvise?id="+id+"",callback);
}
/**
 * @type GET 
 * @param  int
 */
function  removeSystemAdvise(id,callback){
   ajax.async_get("/systemAdvise/removeSystemAdvise?id="+id+"",callback);
}
/**
 * @type POST 
 * @param  cn.hellosix.model.SystemAdvise@4ed9f7b1
 */
function  updateSystemAdvise(systemAdvise,callback){
   ajax.async_post("/systemAdvise/updateSystemAdvise",systemAdvise,callback);
}
/**
 * @type POST 
 * @param  cn.hellosix.model.SystemAdvise@57e388c3
 */
function  addSystemAdvise(systemAdvise,callback){
   ajax.async_post("/systemAdvise/addSystemAdvise",systemAdvise,callback);
}
/******************************** cn.hellosix.controller.supper.SearchFormController ********************************/
/**
 * @type POST 
 * @param  SearchForm{id=null, tableName='null', databaseName='null', htmlContent='null', active=0}
 */
function  updateSearchForm(searchForm,callback){
   ajax.async_post("/super/updateSearchForm",searchForm,callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  getSearchForm(database,table,callback){
   ajax.async_get("/super/getSearchForm?database="+database+"&table="+table+"",callback);
}
/******************************** cn.hellosix.controller.supper.SuperController ********************************/
/**
 * @type GET 
 * @param  String
 */
function  getSubMenu(database,callback){
   ajax.async_get("/super/getSubMenu?database="+database+"",callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  getColumnExtendList(database,table,callback){
   ajax.async_get("/super/getColumnExtendList?database="+database+"&table="+table+"",callback);
}
/**
 * @type POST 
 * @param  FieldExtend{id=0, fieldName='null', tableName='null', databaseName='null', note='null', type='null', active=1, valitate='null', style='null', valueStyle='null', valueInit='null'}
 */
function  updateFieldExtend(fieldExtend,callback){
   ajax.async_post("/super/updateFieldExtend",fieldExtend,callback);
}
/**
 * @type POST 
 * @param  TableExtend{id=0, databaseName='null', tableName='null', note='null', options='null', style='null'}
 */
function  updateTableExtend(tableExtend,callback){
   ajax.async_post("/super/updateTableExtend",tableExtend,callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  getTableExtendDetail(database,table,callback){
   ajax.async_get("/super/getTableExtendDetail?database="+database+"&table="+table+"",callback);
}
/**
 * @type GET
 */
function  getMenu(callback){
   ajax.async_get("/super/getMenu",callback);
}
/******************************** cn.hellosix.controller.supper.UserNotifyController ********************************/
/**
 * @type GET
 */
function  getUserNotifyList(callback){
   ajax.async_get("/userNotify/getUserNotifyList",callback);
}
/**
 * @type GET 
 * @param  int
 */
function  getUserNotify(id,callback){
   ajax.async_get("/userNotify/getUserNotify?id="+id+"",callback);
}
/**
 * @type GET 
 * @param  int
 */
function  removeUserNotify(id,callback){
   ajax.async_get("/userNotify/removeUserNotify?id="+id+"",callback);
}
/**
 * @type POST 
 * @param  cn.hellosix.model.UserNotify@4091b9c3
 */
function  updateUserNotify(userNotify,callback){
   ajax.async_post("/userNotify/updateUserNotify",userNotify,callback);
}
/**
 * @type POST 
 * @param  cn.hellosix.model.UserNotify@3610f277
 */
function  addUserNotify(userNotify,callback){
   ajax.async_post("/userNotify/addUserNotify",userNotify,callback);
}
/******************************** cn.hellosix.controller.supper.UserController ********************************/
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  verifyLogin(username,password,callback){
   ajax.async_get("/user/verifyLogin?username="+username+"&password="+password+"",callback);
}
/**
 * @type GET
 */
function  logout(callback){
   ajax.async_get("/user/logout",callback);
}
/**
 * @type GET
 */
function  getUserList(callback){
   ajax.async_get("/user/getUserList",callback);
}
/**
 * @type GET 
 * @param  int
 */
function  getUser(id,callback){
   ajax.async_get("/user/getUser?id="+id+"",callback);
}
/**
 * @type GET 
 * @param  int
 */
function  removeUser(id,callback){
   ajax.async_get("/user/removeUser?id="+id+"",callback);
}
/**
 * @type POST 
 * @param  User{id=null, username='null', password='null', head='null', phone='null', wechat='null', email='null', serviceTime=null, databaseName='null', menu='null', addTime=1540129773}
 */
function  updateUser(user,callback){
   ajax.async_post("/user/updateUser",user,callback);
}
/**
 * @type GET
 */
function  autoGetUser(callback){
   ajax.async_get("/user/autoGetUser",callback);
}
/**
 * @type GET
 */
function  heartBeat(callback){
   ajax.async_get("/user/heartbeat",callback);
}
/******************************** cn.hellosix.controller.supper.JSApiController ********************************/
/**
 * @type POST 
 * @param  SqlModel{id=0, tableName='null', databaseName='null', note='null', apiName='null', sqlDetail='null', param='null'}
 */
function  runSql(sqlModel,callback){
   ajax.async_post("/super/run-sql",sqlModel,callback);
}
/**
 * @type POST 
 * @param  SqlModel{id=0, tableName='null', databaseName='null', note='null', apiName='null', sqlDetail='null', param='null'}
 */
function  saveSql(sqlModel,callback){
   ajax.async_post("/super/save-sql",sqlModel,callback);
}
/**
 * @type GET 
 * @param  int
 */
function  removeSql(id,callback){
   ajax.async_get("/super/removeSql?id="+id+"",callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  getApiList(database,table,callback){
   ajax.async_get("/super/getApiList?database="+database+"&table="+table+"",callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  createJsSDK(database,table,callback){
   ajax.async_get("/super/createJsSDK?database="+database+"&table="+table+"",callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  getSqlModelList(database,table,callback){
   ajax.async_get("/super/getSqlModelList?database="+database+"&table="+table+"",callback);
}
/**
 * @type POST 
 * @param  java.lang.Integer 
 * @param  java.util.Map 
 * @param  javax.servlet.http.HttpServletRequest
 */
function  apiRestful(id,param,httpServletRequest,callback){
   ajax.async_post("/super/api/restful/{id}",id,param,httpServletRequest,callback);
}
/******************************** cn.hellosix.controller.admin.MonitorController ********************************/
/**
 * @type GET 
 * @param  String 
 * @param  int 
 * @param  int 
 * @param  String
 */
function  getGroupByCountUid(database,startTime,endTime,date,callback){
   ajax.async_get("/admin/monitor/getGroupByCountUid?database="+database+"&startTime="+startTime+"&endTime="+endTime+"&date="+date+"",callback);
}
/**
 * @type GET 
 * @param  String
 */
function  getGroupByUserAgent(database,callback){
   ajax.async_get("/admin/monitor/getGroupByUserAgent?database="+database+"",callback);
}
/**
 * @type GET 
 * @param  String
 */
function  getGroupByCountTotal(database,callback){
   ajax.async_get("/admin/monitor/getGroupByCountTotal?database="+database+"",callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  int
 */
function  getTotalCount(database,beforeDay,callback){
   ajax.async_get("/admin/monitor/getTotalCount?database="+database+"&beforeDay="+beforeDay+"",callback);
}
/**
 * @type GET 
 * @param  String
 */
function  getMonitorDetail(database,callback){
   ajax.async_get("/admin/monitor/getMonitorDetail?database="+database+"",callback);
}
/******************************** cn.hellosix.controller.admin.AdminController ********************************/
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  getTableExtend(database,table,callback){
   ajax.async_get("/admin/getTableExtend?database="+database+"&table="+table+"",callback);
}
/**
 * @type GET 
 * @param  String
 */
function  getMenu(database,callback){
   ajax.async_get("/admin/getMenu?database="+database+"",callback);
}
/**
 * @type POST 
 * @param  QueryModel{database='null', table='null', page=0, pageLength=15, conditions=null, sortList=null}
 */
function  getTableRowList(queryModel,callback){
   ajax.async_post("/admin/getTableRowList",queryModel,callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String 
 * @param  int
 */
function  getTableRowDetail(database,table,id,callback){
   ajax.async_get("/admin/getTableRowDetail?database="+database+"&table="+table+"&id="+id+"",callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String 
 * @param  int
 */
function  deleteRow(database,table,id,callback){
   ajax.async_get("/admin/deleteRow?database="+database+"&table="+table+"&id="+id+"",callback);
}
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  getInitFieldForm(database,table,callback){
   ajax.async_get("/admin/getInitFieldForm?database="+database+"&table="+table+"",callback);
}
/**
 * @type POST 
 * @param  cn.hellosix.model.FieldForm@76536c53
 */
function  updateFieldForm(fieldForm,callback){
   ajax.async_post("/admin/updateFieldForm",fieldForm,callback);
}
