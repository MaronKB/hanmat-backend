drop table if exists comment;
drop table if exists post;
drop table if exists restaurant;
drop table if exists user;

create table user (
    user_id varchar(20) primary key,
    user_pw varchar(20) not null,
    user_nickname varchar(20) not null,
    user_email varchar(50) not null,
    user_reg_date date not null default current_date,
    user_reg_by varchar(20) not null default 'system' references user(user_id),
    user_mod_date date null,
    user_mod_by varchar(20) null references user(user_id),
    user_del_date date null,
    user_del_by varchar(20) null references user(user_id),
    user_is_deleted boolean not null default false,
    user_is_admin boolean not null default false
);

insert into user (user_id, user_pw, user_nickname, user_email, user_is_admin) values ('system', 'system', 'system', 'system@hanmat.com', true);
insert into user (user_id, user_pw, user_nickname, user_email, user_is_admin) values ('admin', 'admin', 'admin', 'admin@hanmat.com', true);
insert into user (user_id, user_pw, user_nickname, user_email) values ('user', 'user', 'user', 'user@hanmat.com');

create table restaurant (
    restaurant_id int primary key auto_increment,
    restaurant_name varchar(200) not null,
    restaurant_lmm_addr varchar(200) not null,
    restaurant_road_addr varchar(500) not null,
    restaurant_x float not null,
    restaurant_y float not null,
    restaurant_reg_date date not null default current_date,
    restaurant_reg_by varchar(20) not null default 'system' references user(user_id),
    restaurant_last_mod_date date not null default current_date,
    restaurant_last_mod_by varchar(20) not null default 'system' references user(user_id),
    restaurant_is_closed boolean not null default false
);

create table post (
    post_id int primary key auto_increment,
    post_title varchar(100) not null,
    post_content text not null,
    post_restaurant_id int not null references restaurant(restaurant_id),
    post_image_1 varchar(200) null,
    post_image_2 varchar(200) null,
    post_image_3 varchar(200) null,
    post_image_4 varchar(200) null,
    post_reg_date date not null default current_date,
    post_reg_by varchar(20) not null references user(user_id),
    post_mod_date date null,
    post_mod_by varchar(20) null references user(user_id),
    post_del_date date null,
    post_del_by varchar(20) null references user(user_id),
    post_is_hidden boolean not null default false,
    post_is_reported boolean not null default false,
    post_is_deleted boolean not null default false
);

create table comment (
    comment_id int primary key auto_increment,
    comment_content text not null,
    comment_post_id int not null references post(post_id),
    comment_reg_date date not null default current_date,
    comment_reg_by varchar(20) not null references user(user_id),
    comment_mod_date date null,
    comment_mod_by varchar(20) null references user(user_id),
    comment_del_date date null,
    comment_del_by varchar(20) null references user(user_id),
    comment_is_hidden boolean not null default false,
    comment_is_reported boolean not null default false,
    comment_is_deleted boolean not null default false
);