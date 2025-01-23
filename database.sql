create sequence SEQ_POST_ID
    nocache
/

create sequence SEQ_COMMENT_ID
    nocache
/

create sequence SEQ_FOOD_ID
    nocache
/

create sequence SEQ_RESTAURANT_ID
    nocache
/

create table T_USER
(
    USER_EMAIL          VARCHAR2(50 char)                             not null
        primary key,
    USER_NICKNAME       VARCHAR2(20 char)                             not null,
    USER_REG_DATE       DATE              default current_date        not null,
    USER_REG_BY         VARCHAR2(50 char) default 'system@hanmat.com' not null
        references T_USER,
    USER_MOD_DATE       DATE,
    USER_MOD_BY         VARCHAR2(20 char)
        references T_USER,
    USER_DEL_DATE       DATE,
    USER_DEL_BY         VARCHAR2(20 char)
        references T_USER,
    USER_IS_DELETED     VARCHAR2(1 char)  default 'N'                 not null,
    USER_IS_ADMIN       VARCHAR2(1 char)  default 'N'                 not null,
    USER_PROFILE_IMAGE  VARCHAR2(255 char),
    USER_IS_BUDDY       VARCHAR2(1 char),
    USER_AUTO_TRANSLATE VARCHAR2(1 char),
    USER_RADIUS         NUMBER,
    USER_INTERESTED     VARCHAR2(4000),
    USER_DESCRIPTION    VARCHAR2(400 char)
)
/

create table T_FOOD_KR
(
    FOOD_ID          NUMBER             not null
        primary key,
    FOOD_NAME        VARCHAR2(100 char) not null,
    FOOD_DSCRN       VARCHAR2(4000 char),
    FOOD_CATEGORY    VARCHAR2(100 char),
    FOOD_IMAGE       VARCHAR2(200 char),
    FOOD_DESCRIPTION VARCHAR2(4000 char),
    FOOD_SPICY       NUMBER(1),
    FOOD_HANMAT      NUMBER(3)
)
/

create trigger TRG_FOOD_KR_ID
    before insert
    on T_FOOD_KR
    for each row
begin
    select seq_food_id.nextval into :new.food_id from dual;
end;
/

create table T_FOOD_JP
(
    FOOD_ID          NUMBER             not null
        primary key,
    FOOD_NAME        VARCHAR2(100 char) not null,
    FOOD_DSCRN       VARCHAR2(4000 char),
    FOOD_CATEGORY    VARCHAR2(100 char),
    FOOD_IMAGE       VARCHAR2(200 char),
    FOOD_DESCRIPTION VARCHAR2(4000 char),
    FOOD_SPICY       NUMBER(1),
    FOOD_HANMAT      NUMBER(3)
)
/

create trigger TRG_FOOD_JP_ID
    before insert
    on T_FOOD_JP
    for each row
begin
    select seq_food_id.nextval into :new.food_id from dual;
end;
/

create table T_FOOD_EN
(
    FOOD_ID          NUMBER             not null
        primary key,
    FOOD_NAME        VARCHAR2(100 char) not null,
    FOOD_DSCRN       VARCHAR2(4000 char),
    FOOD_CATEGORY    VARCHAR2(100 char),
    FOOD_IMAGE       VARCHAR2(200 char),
    FOOD_DESCRIPTION VARCHAR2(4000 char),
    FOOD_SPICY       NUMBER(1),
    FOOD_HANMAT      NUMBER(3)
)
/

create trigger TRG_FOOD_EN_ID
    before insert
    on T_FOOD_EN
    for each row
begin
    select seq_food_id.nextval into :new.food_id from dual;
end;
/

create table T_RESTAURANT
(
    RESTAURANT_ID            NUMBER                                        not null
        primary key,
    RESTAURANT_NAME          VARCHAR2(200 char)                            not null,
    RESTAURANT_LMM_ADDR      VARCHAR2(200 char)                            not null,
    RESTAURANT_ROAD_ADDR     VARCHAR2(500 char)                            not null,
    RESTAURANT_X             FLOAT                                         not null,
    RESTAURANT_Y             FLOAT                                         not null,
    RESTAURANT_REG_DATE      DATE              default current_date        not null,
    RESTAURANT_REG_BY        VARCHAR2(50 char) default 'system@hanmat.com' not null
        references T_USER,
    RESTAURANT_LAST_MOD_DATE DATE              default current_date        not null,
    RESTAURANT_LAST_MOD_BY   VARCHAR2(50 char) default 'system@hanmat.com' not null
        references T_USER,
    RESTAURANT_IS_CLOSED     VARCHAR2(1 char)  default 'N'                 not null,
    RESTAURANT_DESCRIPTION   VARCHAR2(4000 char)
)
/

create trigger TRG_RESTAURANT_ID
    before insert
    on T_RESTAURANT
    for each row
begin
    select seq_restaurant_id.nextval into :new.restaurant_id from dual;
end;
/

create table T_POST
(
    POST_ID            NUMBER                                not null
        primary key,
    POST_TITLE         VARCHAR2(100 char)                    not null,
    POST_AUTHOR        VARCHAR2(50 char)                     not null
        references T_USER,
    POST_CONTENT       VARCHAR2(4000 char)                   not null,
    POST_RATING        NUMBER                                not null,
    POST_RESTAURANT_ID NUMBER                                not null
        references T_RESTAURANT,
    POST_IMAGE_1       VARCHAR2(200 char),
    POST_IMAGE_2       VARCHAR2(200 char),
    POST_IMAGE_3       VARCHAR2(200 char),
    POST_IMAGE_4       VARCHAR2(200 char),
    POST_REG_DATE      DATE             default current_date not null,
    POST_REG_BY        VARCHAR2(50 char)                     not null
        references T_USER,
    POST_MOD_DATE      DATE,
    POST_MOD_BY        VARCHAR2(50 char)
        references T_USER,
    POST_DEL_DATE      DATE,
    POST_DEL_BY        VARCHAR2(50 char)
        references T_USER,
    POST_IS_HIDDEN     VARCHAR2(1 char) default 'N'          not null,
    POST_IS_REPORTED   VARCHAR2(1 char) default 'N'          not null,
    POST_IS_DELETED    VARCHAR2(1 char) default 'N'          not null
)
/

create trigger TRG_POST_ID
    before insert
    on T_POST
    for each row
begin
    select seq_post_id.nextval into :new.post_id from dual;
end;
/

create trigger TRG_DELETE_POST
    after delete
    on T_POST
    for each row
BEGIN
    DELETE FROM T_BEST
    WHERE BEST_POST_ID = :OLD.POST_ID;
END;
/

create table T_COMMENT
(
    COMMENT_ID          NUMBER                                not null
        primary key,
    COMMENT_CONTENT     VARCHAR2(4000 char)                   not null,
    COMMENT_AUTHOR      VARCHAR2(50 char)                     not null
        references T_USER,
    COMMENT_POST_ID     NUMBER                                not null
        references T_POST,
    COMMENT_REG_DATE    DATE             default current_date not null,
    COMMENT_REG_BY      VARCHAR2(50 char)                     not null
        references T_USER,
    COMMENT_MOD_DATE    DATE,
    COMMENT_MOD_BY      VARCHAR2(50 char)
        references T_USER,
    COMMENT_DEL_DATE    DATE,
    COMMENT_DEL_BY      VARCHAR2(50 char)
        references T_USER,
    COMMENT_IS_HIDDEN   VARCHAR2(1 char) default 'N'          not null,
    COMMENT_IS_REPORTED VARCHAR2(1 char) default 'N'          not null,
    COMMENT_IS_DELETED  VARCHAR2(1 char) default 'N'          not null
)
/

create trigger TRG_COMMENT_ID
    before insert
    on T_COMMENT
    for each row
begin
    select seq_comment_id.nextval into :new.comment_id from dual;
end;
/

create table T_BEST
(
    BEST_ID         NUMBER not null
        primary key,
    BEST_POST_ID    NUMBER
        references T_POST,
    BEST_IS_VISIBLE VARCHAR2(1 char) default 'Y',
    BEST_REG_DATE   DATE             default SYSDATE,
    BEST_REG_BY     VARCHAR2(20 char)
        references T_USER
)
/

create trigger TRG_BEST_ID
    before insert
    on T_BEST
    for each row
BEGIN
    SELECT SEQ_BEST_ID.NEXTVAL
    INTO :NEW.BEST_ID
    FROM DUAL;
END;
/


