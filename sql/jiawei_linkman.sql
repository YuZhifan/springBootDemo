create table linkman
(
	id int auto_increment
		primary key,
	name varchar(64) null,
	code varchar(64) null,
	birth varchar(64) null,
	id_card varchar(32) null,
	email varchar(32) null,
	mobile varchar(32) null,
	address varchar(32) null,
	company varchar(128) null,
	create_time datetime null,
	update_time datetime null,
	type tinyint(1) null,
	ext1 varchar(32) null,
	ext2 varchar(32) null,
	ext3 varchar(32) null,
	uid int not null
)
;




INSERT INTO jiawei.linkman (name, code, birth, id_card, email, mobile, address, company, create_time, update_time, type, ext1, ext2, ext3, uid) VALUES ('user2', 'user2', '122221', '122221', '122221', '122221', '22', '122221', null, '2018-11-20 21:36:38', null, '122221', '122221', '122221', 1);
INSERT INTO jiawei.linkman (name, code, birth, id_card, email, mobile, address, company, create_time, update_time, type, ext1, ext2, ext3, uid) VALUES ('user3', 'user3', null, null, null, null, null, null, null, null, null, null, null, null, 0);
INSERT INTO jiawei.linkman (name, code, birth, id_card, email, mobile, address, company, create_time, update_time, type, ext1, ext2, ext3, uid) VALUES ('user4', 'user4', null, null, null, null, null, null, null, null, null, null, null, null, 0);
INSERT INTO jiawei.linkman (name, code, birth, id_card, email, mobile, address, company, create_time, update_time, type, ext1, ext2, ext3, uid) VALUES ('哈哈哈', null, null, null, null, null, null, null, '2018-11-20 21:28:00', null, null, null, null, null, 1);
INSERT INTO jiawei.linkman (name, code, birth, id_card, email, mobile, address, company, create_time, update_time, type, ext1, ext2, ext3, uid) VALUES ('哈哈哈', null, null, null, null, null, null, null, '2018-11-20 21:28:10', null, null, null, null, null, 1);
INSERT INTO jiawei.linkman (name, code, birth, id_card, email, mobile, address, company, create_time, update_time, type, ext1, ext2, ext3, uid) VALUES ('user2', 'user2', '122221', '122221', '122221', '122221', '22', '122221', '2018-11-20 21:52:33', '2018-11-20 21:53:05', null, '122221', '122221', '122221', 15);