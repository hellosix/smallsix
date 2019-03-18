# smallsix

1 监控模块可以通过 mysqlmanager 定时去自动创建，scan database create
2 监控的dao层直接用xml 


mysqldump -u root -p smallsix_smurf7 > smallsix_smurf7.sql

root
39.105.114.48
Lzz36321**

scp smallsix_smurf7.sql  root@39.105.114.48:/root/work/super-admin/

CREATE DATABASE IF NOT EXISTS smallsix_smurf7 DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;
use smallsix_smurf7;


CREATE DATABASE IF NOT EXISTS smallsix DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;
use smallsix;


用户目录配置
```
<li class="sub-menu menu-defult active">
    <a class="load-iframe" href="monitor">
        <i class="icon-bar-chart"></i>
        <span>系统分析</span>
    </a>
</li>
<li class="sub-menu menu-defult">
    <a class="load-iframe" href="javascript:;" data-src="user">
        <i class=" icon-user"></i>
        <span>我的信息</span>
    </a>
</li>
<li class="sub-menu menu-defult">
    <a class="load-iframe" href="javascript:;" data-type="browser" data-src="http://localhost:8081/smurf6.github.io/">
        <i class="icon-desktop"></i>
        <span>快速编辑</span>
    </a>
</li>
```

