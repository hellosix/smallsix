package cn.hellosix.service.supper;

import cn.hellosix.dao.ISearchFormDao;
import cn.hellosix.model.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lzz on 2018/10/3.
 */
@Service
public class SearchFormService {
    @Autowired
    private ISearchFormDao searchFormDao;


    public SearchForm getSearchForm(String database, String table) {
        SearchForm searchForm = searchFormDao.getSearchForm(database, table);
        if( null == searchForm ){
            searchForm = new SearchForm();
        }
        return searchForm;
    }

    public boolean updateSearchForm(SearchForm searchForm) {
        if( null == searchForm.getId() ){
            searchFormDao.addSearchForm( searchForm );
        }else{
            searchFormDao.updateSearchForm( searchForm );
        }
        return true;
    }
}
