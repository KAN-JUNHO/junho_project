create table boards (
                        id BIGINT AUTO_INCREMENT NOT NULL,
                        title VARCHAR(500) NOT NULL,
                        content VARCHAR(4000) NOT NULL,
                        updated_at datetime NOT NULL,
                        created_at datetime NOT NULL
);

insert into boards (title, content, updated_at, created_at) values ('Title 1', 'Content1', now(), now());
insert into boards (title, content, updated_at, created_at) values ('Title 2', 'Content2', now(), now());
insert into boards (title, content, updated_at, created_at) values ('Title 3', 'Content3', now(), now());
