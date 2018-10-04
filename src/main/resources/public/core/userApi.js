/******************************** cn.hellosix.controller.JSApiController ********************************/
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
 */
function  apiRestful(id,param,callback){
   ajax.async_post("/super/api/restful/{id}",id,param,callback);
}
/******************************** cn.hellosix.controller.SuperController ********************************/
/**
 * @type GET
 */
function  getMenu(callback){
   ajax.async_get("/super/getMenu",callback);
}
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
 * @param  FieldExtend{id=0, fieldName='null', tableName='null', databaseName='null', note='null', type='null', active=1, valitate='null', style='null'}
 */
function  updateFieldExtend(fieldExtend,callback){
   ajax.async_post("/super/updateFieldExtend",fieldExtend,callback);
}
/**
 * @type POST 
 * @param  TableExtend{id=0, databaseName='null', tableName='null', note='null', style='null'}
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
/******************************** cn.hellosix.controller.AdminController ********************************/
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
 * @type POST 
 * @param  cn.hellosix.model.FieldForm@69afa141
 */
function  updateFieldForm(fieldForm,callback){
   ajax.async_post("/admin/updateFieldForm",fieldForm,callback);
}
/******************************** cn.hellosix.controller.SearchFormController ********************************/
/**
 * @type GET 
 * @param  String 
 * @param  String
 */
function  getSearchForm(database,table,callback){
   ajax.async_get("/super/getSearchForm?database="+database+"&table="+table+"",callback);
}
/**
 * @type POST 
 * @param  SearchForm{id=null, tableName='null', databaseName='null', htmlContent='null', active=0}
 */
function  updateSearchForm(searchForm,callback){
   ajax.async_post("/super/updateSearchForm",searchForm,callback);
}
