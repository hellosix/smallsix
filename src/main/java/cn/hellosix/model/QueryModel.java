package cn.hellosix.model;

import com.google.common.base.Joiner;

import java.util.List;

/**
 * Created by lzz on 2018/10/3.
 */
public class QueryModel {
    private String database;
    private  String table;
    private int page;
    private int pageLength = 15;
    private List<String> conditions;
    private List<String> sortList;

    public QueryModel(){

    }


    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public List<String> getSortList() {
        return sortList;
    }

    public void setSortList(List<String> sortList) {
        this.sortList = sortList;
    }

    public String querySql(){
        String sql = "select * from " + this.database + "." + this.table;
        if( null != this.conditions && !this.conditions.isEmpty() ){
            sql += " where " + Joiner.on(" AND ").join(this.conditions);
        }
        if( null != this.sortList && !this.sortList.isEmpty() ){
            sql += " order by " + Joiner.on(",").join(this.sortList);
        }else{
            sql += " order by id desc ";
        }
        sql += " limit " + (this.page-1)*this.pageLength +","+ this.pageLength;
        return sql;
    }

    public String countSql(){
        String sql = "select count(*) as c from " + this.database + "." + this.table;
        if( null != this.conditions && !this.conditions.isEmpty() ){
            sql += " where " + Joiner.on(" AND ").join(this.conditions);
        }
        return sql;
    }

    @Override
    public String toString() {
        return "QueryModel{" +
                "database='" + database + '\'' +
                ", table='" + table + '\'' +
                ", page=" + page +
                ", pageLength=" + pageLength +
                ", conditions=" + conditions +
                ", sortList=" + sortList +
                '}';
    }
}
