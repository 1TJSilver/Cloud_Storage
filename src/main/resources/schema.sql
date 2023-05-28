create schema if not exists cloud;
create table if not exists cloud.files
(
    file_id     int primary key auto_increment,
    file_name   char(200) not null,
    user_id     int       not null,
    not_deleted boolean,
    content     longblob  not null
);
create table if not exists cloud.users
(
    user_id  int primary key auto_increment,
    login    char(255) not null,
    password char(255) not null
);
create table if not exists cloud.revoke_tokens
(
    tokens char(255) not null primary key
);