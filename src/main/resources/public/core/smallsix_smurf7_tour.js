window.tour= {
  /**
   * 获取旅游列表
   * {"pageindex":1,"pageSize":3}
   */
  tourList: function(data, callback){
    ajax.async_post(window.apiurl + "17" ,data ,callback);
  },
  /**
   * 获取旅游详情
   * {"id":2}
   */
  tourDetail: function(data, callback){
    ajax.async_post(window.apiurl + "18" ,data ,callback);
  },

}