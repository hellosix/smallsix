window.owner= {
  /**
   * 自我介绍
   * 
   */
  ownerContent: function(data, callback, errorback){
    ajax.async_post(window.apiurl + "22" ,data ,callback,errorback);
  },

}