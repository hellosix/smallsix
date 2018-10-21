package cn.hellosix.component;

import cn.hellosix.core.mysql.MysqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzz on 2018/10/1.
 */
@Component
public class MysqlManager implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${smallsix.mysql.scan.package}")
    private String mysqlScanTable;
    @Value("${smallsix.mysql.database.name}")
    private String database;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initMysqlTable();
    }
    /**
     * 初始化 mysql 表
     */
    public void initMysqlTable(){
        List<String> packages = new ArrayList();
        packages.add( mysqlScanTable );
        List<String> sqlList = MysqlUtil.initMysqlTable( packages );
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + database + " default character set utf8mb4 COLLATE utf8mb4_unicode_ci");
        jdbcTemplate.execute("use " + database);
        for( String createSql : sqlList ){
            jdbcTemplate.execute( createSql );
        }
    }
}
