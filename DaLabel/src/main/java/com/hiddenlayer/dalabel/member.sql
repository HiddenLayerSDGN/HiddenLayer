create table member (
	user_id varchar2(15 char) primary key,
	user_email varchar2(35 char),
	user_name varchar2(20 char) not null,
	user_pw varchar2(15 char) not null,
	user_birth date not null,
	user_img varchar2(100 char),
	user_isdeleted number(1) not null, -- 0이 탈퇴 안한거, 1이 탈퇴한거
	user_deleted_date date,
	user_rating number(2) not null,
	user_credit number(9) not null,
	user_trust number(3, 2)
)

alter table member drop column user_trust

alter table member add user_trust number(5, 2) default 0

select * from member


drop table member cascade constraint purge


delete BOARD
delete COMMENT_REPLY
delete DATA
delete DATABUNDLE
delete LABELING_DO
delete LABELING_DONE
delete LABELING_PROJECT
delete LABELING_RESULT
delete MEMBER
delete POST_COMMENT
delete REPLY
delete REPORT