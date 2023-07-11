create table if not exists cloud.files
(
    file_id int primary key auto_increment,
    file_name char(200) not null,
    user_id int not null,
    not_deleted boolean,
    content longblob  not null,
    foreign key (user_id) references cloud.users (user_id)
);