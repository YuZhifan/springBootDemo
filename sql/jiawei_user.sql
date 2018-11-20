create table user
(
	id int auto_increment
		primary key,
	name varchar(64) null,
	birth varchar(64) null,
	id_card varchar(32) null,
	code varchar(64) null,
	email varchar(128) null,
	mobile varchar(32) null,
	address varchar(256) null,
	company varchar(256) null,
	pwd varchar(32) null,
	create_time datetime null,
	update_time datetime null,
	type tinyint(1) default '1' null comment '用户类型',
	constraint user_code_uindex
		unique (code)
)
;



INSERT INTO jiawei.user (name, birth, id_card, code, email, mobile, address, company, pwd, create_time, update_time, type) VALUES ('admin', '2018-06-01', '44058019900010', 'admin', '7438738@crc.com.hk', '1380000000', '广东深圳', 'crc', 'admin', '2018-06-01 00:00:00', null, 0);
INSERT INTO jiawei.user (name, birth, id_card, code, email, mobile, address, company, pwd, create_time, update_time, type) VALUES ('admin1', '2018-06-01', '44058019900010', 'admin1', '7438738@crc.com.hk', '1380000000', '广东深圳', 'crc', 'admin1', '2018-06-01 00:00:00', null, 0);
INSERT INTO jiawei.user (name, birth, id_card, code, email, mobile, address, company, pwd, create_time, update_time, type) VALUES ('yuzhifan', null, null, 'yuzhifan', null, null, null, null, 'yuzhifan', '2018-11-20 21:45:26', null, 1);
INSERT INTO jiawei.user (name, birth, id_card, code, email, mobile, address, company, pwd, create_time, update_time, type) VALUES ('yuzhifan1', null, null, 'yuzhifan1', null, null, null, null, 'yuzhifan1', '2018-11-20 21:51:00', null, 1);
INSERT INTO jiawei.user (name, birth, id_card, code, email, mobile, address, company, pwd, create_time, update_time, type) VALUES ('yuzhifan2', null, null, 'yuzhifan2', null, null, null, null, 'yuzhifan2', '2018-11-20 21:53:28', null, 1);