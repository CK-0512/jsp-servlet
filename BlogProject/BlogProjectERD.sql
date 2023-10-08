
/* Drop Triggers */

DROP TRIGGER TRI_article_id;
DROP TRIGGER TRI_board_id;
DROP TRIGGER TRI_member1_id;
DROP TRIGGER TRI_member_id;
DROP TRIGGER TRI_non_member_id;
DROP TRIGGER TRI_notice_id;
DROP TRIGGER TRI_reply_id;



/* Drop Tables */

DROP TABLE non_member CASCADE CONSTRAINTS;
DROP TABLE notice CASCADE CONSTRAINTS;
DROP TABLE reply CASCADE CONSTRAINTS;
DROP TABLE article CASCADE CONSTRAINTS;
DROP TABLE blogInformation CASCADE CONSTRAINTS;
DROP TABLE board CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_article_id;
DROP SEQUENCE SEQ_board_id;
DROP SEQUENCE SEQ_member1_id;
DROP SEQUENCE SEQ_member_id;
DROP SEQUENCE SEQ_non_member_id;
DROP SEQUENCE SEQ_notice_id;
DROP SEQUENCE SEQ_reply_id;




/* Create Sequences */

CREATE SEQUENCE SEQ_article_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_board_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_member1_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_member_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_non_member_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_notice_id INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_reply_id INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE article
(
	id number NOT NULL UNIQUE,
	-- 1 = 본인, 비회원
	memberId number NOT NULL,
	-- 0 = 본인, 1 = 회원, 2 = 비회원
	membertype number NOT NULL,
	regDate date DEFAULT SYSDATE NOT NULL,
	updateDate date DEFAULT SYSDATE NOT NULL,
	hitCount number DEFAULT 0 NOT NULL,
	title varchar2(20) NOT NULL,
	body clob NOT NULL,
	boardId number NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE blogInformation
(
	regDate date DEFAULT SYSDATE NOT NULL UNIQUE,
	updateDate date DEFAULT SYSDATE NOT NULL,
	introduction clob NOT NULL,
	PRIMARY KEY (regDate)
);


CREATE TABLE board
(
	id number NOT NULL UNIQUE,
	regDate date DEFAULT SYSDATE NOT NULL,
	updateDate date DEFAULT SYSDATE NOT NULL,
	name varchar2(10) NOT NULL,
	-- 0 = 존재, 1 = 삭제
	delStatus number DEFAULT 0 NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE member
(
	id number NOT NULL UNIQUE,
	userId varchar2(10) NOT NULL UNIQUE,
	userPass varchar2(20) NOT NULL,
	email varchar2(20),
	nickname varchar2(10),
	-- 0 = 본인, 1 = 일반회원
	authLevel number DEFAULT 1 NOT NULL,
	-- 0 = 존재, 1 = 삭제됨
	delStatus number DEFAULT 0 NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE non_member
(
	id number NOT NULL UNIQUE,
	regDate date DEFAULT SYSDATE NOT NULL,
	nickname varchar2(10) NOT NULL,
	pass varchar2(20) NOT NULL,
	-- 1 = 질문글, 2 = 댓글
	type number NOT NULL,
	articleId number NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE notice
(
	id number NOT NULL UNIQUE,
	regDate date DEFAULT SYSDATE NOT NULL,
	updateDate date DEFAULT SYSDATE NOT NULL,
	-- 1 = 질문글등록, 2 = 댓글등록
	type number NOT NULL,
	articleId number NOT NULL,
	-- 0 = 미확인, 1 = 확인
	checkStatus number NOT NULL,
	url varchar2(100) NOT NULL,
	memberId number NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE reply
(
	id number NOT NULL UNIQUE,
	regDate date DEFAULT SYSDATE NOT NULL,
	updateDate date DEFAULT SYSDATE NOT NULL,
	-- 1 = 본인, 비회원
	memberId number NOT NULL,
	body varchar2(100) NOT NULL,
	articleId number NOT NULL,
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE non_member
	ADD FOREIGN KEY (articleId)
	REFERENCES article (id)
;


ALTER TABLE notice
	ADD FOREIGN KEY (articleId)
	REFERENCES article (id)
;


ALTER TABLE reply
	ADD FOREIGN KEY (articleId)
	REFERENCES article (id)
;


ALTER TABLE article
	ADD FOREIGN KEY (boardId)
	REFERENCES board (id)
;


ALTER TABLE article
	ADD FOREIGN KEY (memberId)
	REFERENCES member (id)
;


ALTER TABLE notice
	ADD FOREIGN KEY (memberId)
	REFERENCES member (id)
;


ALTER TABLE reply
	ADD FOREIGN KEY (memberId)
	REFERENCES member (id)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_article_id BEFORE INSERT ON article
FOR EACH ROW
BEGIN
	SELECT SEQ_article_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_board_id BEFORE INSERT ON board
FOR EACH ROW
BEGIN
	SELECT SEQ_board_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_member1_id BEFORE INSERT ON member1
FOR EACH ROW
BEGIN
	SELECT SEQ_member1_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_member_id BEFORE INSERT ON member
FOR EACH ROW
BEGIN
	SELECT SEQ_member_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_non_member_id BEFORE INSERT ON non_member
FOR EACH ROW
BEGIN
	SELECT SEQ_non_member_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_notice_id BEFORE INSERT ON notice
FOR EACH ROW
BEGIN
	SELECT SEQ_notice_id.nextval
	INTO :new.id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_reply_id BEFORE INSERT ON reply
FOR EACH ROW
BEGIN
	SELECT SEQ_reply_id.nextval
	INTO :new.id
	FROM dual;
END;

/




/* Comments */

COMMENT ON COLUMN article.memberId IS '1 = 본인, 비회원';
COMMENT ON COLUMN article.membertype IS '1 = 회원, 2 = 비회원';
COMMENT ON COLUMN board.delStatus IS '0 = 존재, 1 = 삭제';
COMMENT ON COLUMN member.authLevel IS '0 = 본인, 1 = 일반회원';
COMMENT ON COLUMN member.delStatus IS '0 = 존재, 1 = 삭제됨';
COMMENT ON COLUMN non_member.type IS '1 = 질문글, 2 = 댓글';
COMMENT ON COLUMN notice.type IS '1 = 질문글등록, 2 = 댓글등록';
COMMENT ON COLUMN notice.checkStatus IS '0 = 미확인, 1 = 확인';
COMMENT ON COLUMN reply.memberId IS '1 = 본인, 비회원';



