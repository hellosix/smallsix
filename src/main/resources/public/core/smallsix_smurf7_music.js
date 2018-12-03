window.music= {
  /**
   * 音乐列表
   * 
   */
  musicList: function(data, callback, errorback){
    ajax.async_post(window.apiurl + "19" ,data ,callback,errorback);
  },

}