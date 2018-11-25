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