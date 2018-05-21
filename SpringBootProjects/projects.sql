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
);

select * from TbMyImages;