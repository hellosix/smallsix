CREATE DATABASE IF NOT EXISTS smurf7 DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci

use smurf7;

CREATE TABLE IF NOT EXISTS banner (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  image varchar(100),
  intro VARCHAR(255),
  publish tinyint(1) DEFAULT 0 COMMENT '0 不发布, 1 发布',
  add_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT '首页滚图';

CREATE TABLE IF NOT EXISTS guita (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  image varchar(100),
  title VARCHAR (100),
  intro VARCHAR(255),
  publish tinyint(1) DEFAULT 0 COMMENT '0 不发布, 1 发布',
  add_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT '吉他页面';;

CREATE TABLE IF NOT EXISTS music (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  music_file varchar(200),
  title VARCHAR (100),
  intro VARCHAR(255),
  lyrics text COMMENT '歌词',
  publish tinyint(1) DEFAULT 0 COMMENT '0 不发布, 1 发布',
  add_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT '音乐';;

CREATE TABLE IF NOT EXISTS video (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  video_file varchar(200),
  title VARCHAR (100),
  intro VARCHAR(255),
  publish tinyint(1) DEFAULT 0 COMMENT '0 不发布, 1 发布',
  add_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT '视频';;


CREATE TABLE IF NOT EXISTS tour (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR (100) DEFAULT NULL COMMENT '标题',
  intro VARCHAR(255) DEFAULT NULL COMMENT '简介',
  content LONGTEXT DEFAULT NULL COMMENT '正文',
  publish tinyint(1) DEFAULT 0 COMMENT '0 不发布, 1 发布',
  add_time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT '旅游';;



alter table table1 add transactor varchar(10);
alter table 表名称 change 字段原名称 字段新名称 字段类型 [是否允许非空
ALTER TABLE mytable DROP 字段名;

