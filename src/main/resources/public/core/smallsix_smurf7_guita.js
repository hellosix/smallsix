window.guita= {
  /**
   * 首页滚图
   * 
   */
  homebar: function(data, callback){
    ajax.async_post(window.apiurl + "12" ,data ,callback);
  },
  /**
   * 推荐吉他
   * 
   */
  recommend: function(data, callback){
    ajax.async_post(window.apiurl + "13" ,data ,callback);
  },
  /**
   * 热卖吉他
   * {"pageindex":1,"pageSize":5}
   */
  hotguita: function(data, callback){
    ajax.async_post(window.apiurl + "14" ,data ,callback);
  },

}