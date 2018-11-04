var apiurl="http://127.0.0.1:8182/super/api/restful/"; 
var banner= {
  /**
   * 滚屏页面
   * {"id":1}
   */
  getBannerById: function(data, callback){
    ajax.async_post(apiurl + "7" ,data ,callback);
  },
  /**
   * 滚屏页面
   * {"id":1}
   */
  getBannerById2: function(data, callback){
    ajax.async_post(apiurl + "8" ,data ,callback);
  },

}