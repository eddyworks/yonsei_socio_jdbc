/* http://dev.mysql.com/doc/refman/5.0/en/sql-syntax-data-manipulation.html */
/* http://kwonnam.pe.kr/wiki/database/mysql/basic */

create database db1;

create table table1 (
  column_name1 INT,
  column_name2 VARCHAR(15),
  column_name3 INT );

/* 사용자 추가 예시 */
create user a
SET PASSWORD FOR a=PASSWORD("realpwd");
GRANT ALL PRIVILEGES ON dbname.* TO a

use Twitter_20110101;


CREATE TABLE animals (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     PRIMARY KEY (id)
) ENGINE=MyISAM;

INSERT INTO animals (name) VALUES
    ('dog'),('cat'),('penguin'),
    ('lax'),('whale'),('ostrich');

SELECT * FROM animals;

/* JOIN 예제 */
select u.name, m.messages, m.message_time from twitterUser u, messageTable5 m where u.userid = m.userid limit 3;


/* UNION 예제 */
select * from (select * from messageTable5 limit 3 union select * from messageTable6 limit 50) a;