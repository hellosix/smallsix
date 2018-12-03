window.course= {
  /**
   * 课程列表
   * 
   */
  courseList: function(data, callback, errorback){
    ajax.async_post(window.apiurl + "21" ,data ,callback,errorback);
  },

}