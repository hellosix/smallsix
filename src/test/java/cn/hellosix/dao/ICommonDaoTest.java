package cn.hellosix.dao;

import cn.hellosix.Application;
import cn.hellosix.model.Column;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import java.util.Map;

/**
 * Created by lzz on 2018/10/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ICommonDaoTest {
    @Autowired
    private ICommonDao commonDao;

    @Test
    public void testDatabases(){
        List<String> list = commonDao.getDatabases();
        System.out.println( list );
    }

    @Test
    public void testGetTables(){
        List<String> list = commonDao.getTables( "test" );
        System.out.println( list );
    }

    @Test
    public void testGetColumn(){
        List<Column> list = commonDao.getColumns("test", "user");
        System.out.println( list );
    }

    @Test
    public void testSelect(){
        List<Map<String, Object>> res = commonDao.select("select * from test.user");
        System.out.println( res );
    }

    @Test
    public void testInsert(){
        commonDao.insert("insert into test.user(id, _c1)  values(1, 'linzhouzhi')");
        commonDao.insert("insert into test.user(id, _c1)  values(2, 'linzhouzhi2')");
    }

    @Test
    public void testUpdate(){
        commonDao.update("update test.user set _c1='cccc' where id=1");
    }
}
