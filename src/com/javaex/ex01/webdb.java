---테이블만들기
create table book(
    book_id number(5),
    title   varchar2(50),
    author  varchar2(10),
    pub_date DATE
    );
---출력
DESCRIBE BOOK;
---추가,수정,삭제
ALTER TABLE book add(pubs varchar2(50));
alter table book MODIFY(title varchar2(100));
ALTER TABLE book DROP(author);

rename book TO article;
desc article;

drop table article;

create table author(
    author_id   number(10),
    author_name VARCHAR2(100) NOT NULL,
    author_desc VARCHAR2(500),
    PRIMARY KEY(author_id)
    );
    desc author;
    
create table book(
    book_id number(10),
    title   varchar2(100) not null,
    pubs varchar2(100),
    pub_date date,
    author_id number(10),
    primary key(book_id),
    constraint c_book_fk foreign key(author_id)
    references author(author_id)
);

INSERT INTO author
VALUES(1,' 박경리','토지작가');

insert into author(author_id,author_name)
values(2,'이문열');

select * from author;

insert in author(author_id,author_desc)
values(3,'웹툰작가');--author_name 안 넣으면 애러남 not null이야

insert into author(author_id,author_name)
values(2,'강풀');--author_id 중복되서 오류남

UPDATE author
set author_name='기안94',
    author_desc='웹툰작가'
where author_id=1;

select * from author;

update author
set author_name='강풀',
    author_desc='인기작가';
    
select * from author;--db에 적용안됨

commit;--db에 적용됨

delete from author
where author_id=1;
select * from author;

create sequence seq_aythir_id
increment by 1
start with 1;

insert into author
values(seq_author_id.nextval,'이문열','삼국지작가');

select * from author;
drop table author;

create table book(
    book_id number(10),
    title   varchar2(100),
    pubs    varchar2(100),
    pub_date date
    );
drop table book;
---------------------------------------------------
create table author(
    author_id   number(10),
    author_name varchar2(100) NOT NULL,
    author_desc varchar2(500),
    PRIMARY KEY(author_id)
    );
       
create table book(
    book_id number(10),
    title   varchar2(100) not null,
    pubs varchar2(100),
    pub_date date,
    author_id number(10),
    primary key(book_id),
    constraint c_book_fk foreign key(author_id)
    references author(author_id)
);

INSERT INTO author
VALUES(1,'이문열','경북 영양');

INSERT INTO author
VALUES(2,'박경리','경상남도 통영');

INSERT INTO author
VALUES(3,'유시민','17대 국회의원');

INSERT INTO author
VALUES(4,'기안84','기안동');

INSERT INTO author
VALUES(5,'강풀','온라인 만화가 1세대');

INSERT INTO author
VALUES(6,'김영하','알쓸신잡');

INSERT INTO author
VALUES(7,'한지수','신잡');

INSERT INTO book
VALUES(1,'우리들의 일그러진 영웅','다림','1998-02-02',1);

INSERT INTO book
VALUES(2,'삼국지','민음사','2002-03-01',1);

INSERT INTO book
VALUES(3,'토지','마로니에 북스','2010-08-15',2);

INSERT INTO book
VALUES(4,'유시민의 글쓰기 특강','생각의 길','2015-04-01',3);

INSERT INTO book
VALUES(5,'패션왕','중앙북스','2012-02-22',4);

INSERT INTO book
VALUES(6,'순정만화','재미주의','2011-08-04',5);

INSERT INTO book
VALUES(7,'오직 두사람','문학동네','2017-05-04',6);

INSERT INTO book
VALUES(8,'26년','재미주의','2012-02-04',5);

UPDATE author
set author_desc='서울특별시'
where author_id=5;

select book_id,title,pubs,pub_date,a.author_id,author_name,author_desc
from book b,author a
where b.author_id=a.author_id;


commit;
delete from book
where BOOK_ID=5;
DELETE FROM author
where author_id=4;
select book_id,title,pubs,pub_date,a.author_id,author_name,author_desc
from book b,author a
where b.author_id=a.author_id;

create table asd(
    asd_id number(5),
    title   varchar2(50),
    author  varchar2(10),
    pub_date DATE
    );
CREATE SEQUENCE seq_asd_id
INCREMENT BY 1
START WITH 1;
INSERT INTO asd
VALUES(seq_asd_id.nextval,'야호','김','2015-04-01');
INSERT INTO asd
VALUES(seq_asd_id.nextval,'헤헤','윤','2015-04-14');
INSERT INTO asd
VALUES(seq_asd_id.nextval,'바부','준','2015-04-22');
drop table asd;

drop table book;
drop table author;
drop sequence seq_book_id;
drop sequence seq_author_id;

CREATE TABLE author (
  author_id	NUMBER(10),
  author_name	VARCHAR2(100) 	NOT NULL,
  author_desc	VARCHAR2(500),
  PRIMARY 	KEY(author_id)	
);


CREATE TABLE book (
  book_id	 NUMBER(10),
  title	 VARCHAR2(100) 	NOT NULL,
  pubs	 VARCHAR2(100),
  pub_date	 DATE,
  author_id NUMBER(10),
  PRIMARY 	KEY(book_id),
  CONSTRAINT c_book_fk FOREIGN KEY (author_id)
  REFERENCES author(author_id)
);

CREATE SEQUENCE seq_author_id
INCREMENT BY 1 
START WITH 1 ;

CREATE SEQUENCE seq_book_id
INCREMENT BY 1 
START WITH 1 ;


insert into author 
values (seq_author_id.nextval, 
        '이문열', '경북영양');

insert into author 
values (seq_author_id.nextval, 
        '박경리', '경상통영');

update author 
set author_name = '이 문 열', 
    author_desc = '경상북도 영양' 
where auth_id = 1 ;
    
delete from author where author_id = 1;


select author_id, author_name, author_desc adesc
from author;

select *
from book;

commit;