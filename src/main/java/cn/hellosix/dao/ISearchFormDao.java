package cn.hellosix.dao;

import cn.hellosix.model.SearchForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lzz on 2018/10/3.
 */
@Repository
public interface ISearchFormDao {
    SearchForm getSearchForm(@Param("database")String database, @Param("table")String table);
    void addSearchForm(SearchForm searchForm);
    void removeSearchForm(int id);
    void updateSearchForm(SearchForm searchForm);
}
