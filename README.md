springboot简单项目
SQl语句：
create database zhanneixin;
use zhanneixin;
create table d16_user(
    id int primary key auto_increment,
    username varchar(255),
    password varchar(255),
    role_name varchar(50)
);
create table d16_message(
    id int primary key auto_increment,
    title varchar(60),
    content varchar(60),
    fid int,
    sid int,
    num int,
    status varchar(30),
    send_time date,
    type varchar(30),
    read_status varchar(50)
);
