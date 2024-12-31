drop table t_comment;
drop table t_post;
drop table t_restaurant;
drop table t_user;
create table t_user (
    user_email varchar2(50 char) primary key not null,
    user_nickname varchar2(20 char) not null,
    user_reg_date date default current_date not null,
    user_reg_by varchar2(20 char) default 'system@hanmat.com' references t_user(user_email) not null,
    user_mod_date date null,
    user_mod_by varchar2(20 char) null references t_user(user_email),
    user_del_date date null,
    user_del_by varchar2(20 char) null references t_user(user_email),
    user_is_deleted varchar2(1 char) default 'N' not null,
    user_is_admin varchar2(1 char) default 'N' not null
);

insert into t_user (user_email, user_nickname, user_reg_by, user_is_admin) values ( 'system@hanmat.com', 'system', 'system@hanmat.com', 'Y');
insert into t_user (user_email, user_nickname, user_is_admin) values ('admin@hanmat.com', 'admin', 'Y');
insert into t_user (user_email, user_nickname) values ('user@hanmat.com', 'user');

create table t_restaurant (
    restaurant_id int primary key,
    restaurant_name varchar2(200 char) not null,
    restaurant_lmm_addr varchar2(200 char) not null,
    restaurant_road_addr varchar2(500 char) not null,
    restaurant_x float not null,
    restaurant_y float not null,
    restaurant_reg_date date default current_date not null,
    restaurant_reg_by varchar2(20 char) default 'system@hanmat.com' references t_user(user_email) not null,
    restaurant_last_mod_date date default current_date not null,
    restaurant_last_mod_by varchar2(20 char) default 'system@hanmat.com' references t_user(user_email) not null,
    restaurant_is_closed varchar2(1 char) default 'N' not null
);

create sequence seq_restaurant_id start with 1 increment by 1 nocache;
create or replace trigger trg_restaurant_id
    before insert on t_restaurant
    for each row
begin
    select seq_restaurant_id.nextval into :new.restaurant_id from dual;
end;

create table t_post (
    post_id int primary key,
    post_title varchar2(100 char) not null,
    post_content varchar2(4000 char) not null,
    post_restaurant_id int not null references t_restaurant(restaurant_id),
    post_image_1 varchar2(200 char) null,
    post_image_2 varchar2(200 char) null,
    post_image_3 varchar2(200 char) null,
    post_image_4 varchar2(200 char) null,
    post_reg_date date default current_date not null,
    post_reg_by varchar2(20 char) not null references t_user(user_email),
    post_mod_date date null,
    post_mod_by varchar2(20 char) null references t_user(user_email),
    post_del_date date null,
    post_del_by varchar2(20 char) null references t_user(user_email),
    post_is_hidden varchar2(1 char) default 'N' not null,
    post_is_reported varchar2(1 char) default 'N' not null,
    post_is_deleted varchar2(1 char) default 'N' not null
);

create sequence seq_post_id start with 1 increment by 1 nocache;
create or replace trigger trg_post_id
    before insert on t_post
    for each row
begin
    select seq_post_id.nextval into :new.post_id from dual;
end;

create table t_comment (
    comment_id int primary key,
    comment_content varchar2(4000 char) not null,
    comment_post_id int not null references t_post(post_id),
    comment_reg_date date default current_date not null,
    comment_reg_by varchar2(20 char) not null references t_user(user_email),
    comment_mod_date date null,
    comment_mod_by varchar2(20 char) null references t_user(user_email),
    comment_del_date date null,
    comment_del_by varchar2(20 char) null references t_user(user_email),
    comment_is_hidden varchar2(1 char) default 'N' not null,
    comment_is_reported varchar2(1 char) default 'N' not null,
    comment_is_deleted varchar2(1 char) default 'N' not null
);

create sequence seq_comment_id start with 1 increment by 1 nocache;
create or replace trigger trg_comment_id
    before insert on t_comment
    for each row
begin
    select seq_comment_id.nextval into :new.comment_id from dual;
end;

create table t_food_kr (
    food_id int primary key,
    food_name varchar2(100 char) not null,
    food_dscrn varchar2(4000 char) null,
    food_category varchar2(100 char) null,
    food_image varchar2(200 char) null
);

create table t_food_jp (
    food_id int primary key,
    food_name varchar2(100 char) not null,
    food_dscrn varchar2(4000 char) null,
    food_category varchar2(100 char) null,
    food_image varchar2(200 char) null
);

create table t_food_en (
    food_id int primary key,
    food_name varchar2(100 char) not null,
    food_dscrn varchar2(4000 char) null,
    food_category varchar2(100 char) null,
    food_image varchar2(200 char) null
);

create sequence seq_food_id start with 1 increment by 1 nocache;
create or replace trigger trg_food_kr_id
    before insert on t_food_kr
    for each row
begin
    select seq_food_id.nextval into :new.food_id from dual;
end;

create or replace trigger trg_food_jp_id
    before insert on t_food_jp
    for each row
begin
    select seq_food_id.nextval into :new.food_id from dual;
end;

create or replace trigger trg_food_en_id
    before insert on t_food_en
    for each row
begin
    select seq_food_id.nextval into :new.food_id from dual;
end;