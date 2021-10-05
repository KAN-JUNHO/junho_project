create schema counts default character set utf8;
use counts;
create table counts (
    type VARCHAR(100) ,
    sender VARCHAR(100) NOT NULL,
    content VARCHAR(100) NOT NULL,
    cnt int NOT NULL
);

show databases;

