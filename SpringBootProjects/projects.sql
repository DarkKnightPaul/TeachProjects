use mysql;

drop database if exists projects;

create database projects default charset utf8 collate utf8_general_ci;

use projects;

/*文件上传演示（跑马灯效果）*/
create table TbMyImages
(
	imageId integer auto_increment primary key not null comment '主键',
	savePath varchar(255) unique not null comment '文件路径',
	description varchar(200) not null comment '文件描述',
	filename varchar(255) not null comment '文件原始名称',
	mime varchar(100) not null comment '文件类型',
	filesize bigint not null comment '文件大小',
	uploadDate timestamp default now() not null comment '上传时间'
)comment '图片上传信息表';

select * from TbMyImages;

/*bbs留言板演示*/
create table TbBbs
(
	bbsId integer auto_increment primary key not null comment '主键',
	username varchar(8) not null comment '留言人',
	info varchar(140) not null comment '留言内容',
	laud integer default 0 not null comment '点赞数量',
	createDate  timestamp default now() not null comment '上传时间'
)comment '留言板信息表';

select * from TbBbs;

/*vip充值卡管理演示*/
create table TbVipUser
(
	uid integer auto_increment primary key not null comment '主键',
	username varchar(50) unique not null  comment '登陆名称',
	password varchar(50) not null  comment '登陆密码',
	nickname varchar(50) unique not null comment '昵称',
	isDisable enum('y','n') default 'n' not null comment '是否停用，y：停用，n；不停用，默认n'
)comment 'vip卡用户信息表';

insert into TbVipUser(username,password,nickname) values('user','pwduser','默认用户');
select * from TbVipUser;



