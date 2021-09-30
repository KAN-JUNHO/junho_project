create table users (
                       id BIGINT AUTO_INCREMENT NOT NULL,
                       name VARCHAR(50) NOT NULL,
                       age INT NOT NULL,
                       profile_picture_url VARCHAR(200) NULL,
                       updated_at datetime NOT NULL,
                       created_at datetime NOT NULL
);

insert into users (name, age, profile_picture_url, updated_at, created_at) values ('user1', 10, null, now(), now());
insert into users (name, age, profile_picture_url, updated_at, created_at) values ('user2', 20, null, now(), now());
insert into users (name, age, profile_picture_url, updated_at, created_at) values ('user3', 30, null, now(), now());
