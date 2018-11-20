CREATE TABLE jiawei.user
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(64),
    birth varchar(64),
    id_card varchar(32),
    code varchar(64),
    email varchar(128),
    mobile varchar(32),
    address varchar(256),
    company varchar(256),
    pwd varchar(32),
    create_time datetime,
    update_time datetime,
    type tinyint(1) DEFAULT '1' COMMENT '用户类型'
);
CREATE UNIQUE INDEX user_code_uindex ON jiawei.user (code);
INSERT INTO jiawei.user (name, birth, id_card, code, email, mobile, address, company, pwd, create_time, update_time, type) VALUES ('admin', null, null, 'admin', null, null, null, null, 'admin', null, null, 0);