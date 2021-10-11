create schema counts default character set utf8;
use counts;
create table counts (
    type VARCHAR(100) ,
    sender VARCHAR(100) NOT NULL,
    content VARCHAR(100) NOT NULL,
    cnt int NOT NULL
);

INSERT INTO counts(type, sender, content, cnt) VALUES(null, 'junho', 'wer', 1);

show databases;

DROP TABLE counts;

