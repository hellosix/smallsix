window.video= {
  /**
   * 视频列表
   * {"pageindex":0,"pageSize":3}
   */
  videoList: function(data, callback){
    ajax.async_post(window.apiurl + "16" ,data ,callback);
  },

}