package cn.hellosix.dao;

import cn.hellosix.Application;
import cn.hellosix.model.FieldExtend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lzz on 2018/10/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class IFieldExtendDaoTest {
    @Autowired
    IFieldExtendDao fieldExtendDao;

    @Test
    public void getFieldExtendListTest(){
        List list = fieldExtendDao.getFieldExtendList();
        System.out.println( list );
    }

    @Test
    public void addFieldExtendTest(){
        FieldExtend fieldExtend = new FieldExtend();
        fieldExtend.setActive(1);
        fieldExtend.setTableName("test");
        fieldExtend.setType("ttt");
        fieldExtend.setFieldName("f1");
        fieldExtendDao.addFieldExtend(fieldExtend);
    }

    @Test
    public void updateTest(){
        FieldExtend fieldExtend = new FieldExtend();
        fieldExtend.setId(1);
        fieldExtend.setTableName("test2");
        fieldExtendDao.updateFieldExtend(fieldExtend);
    }

    @Test
    public void removeTest(){
        fieldExtendDao.removeFieldExtend(1);
    }
}
