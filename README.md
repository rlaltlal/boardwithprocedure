# boardwithprocedure
오라클 프로시저를 사용한 게시판

===========================


1.oracle 계정 생성

C:\Users\302-00>SQLPLUS /NOLOG
SQL> CONN /AS SYSDBA
연결되었습니다.
SQL> SHOW USER
USER은 "SYS"입니다
SQL> ALTER SESSION SET "_ORACLE_SCRIPT"=true;
세션이 변경되었습니다.

SQL> CREATE USER BOARD IDENTIFIED BY 1234;
사용자가 생성되었습니다.

SQL> GRANT CONNECT, RESOURCE TO BOARD;
권한이 부여되었습니다.

SQL> ALTER USER BOARD DEFAULT TABLESPACE
  2  USERS QUOTA UNLIMITED ON USERS;
사용자가 변경되었습니다.

===========================
2-1. table sample
menus
    MENU_ID  MENU_NAME  MENU_SEQ
    MENU01   JAVA       2
    MENU02   JSP        3     
    MENU03   HTML       1
    MENU04   SPRING     4

BOARD
    IDX     TITLE            MENU_ID
    3      SPRING  소개     MENU04 
    1      JAVA 1강         MENU01
    2       [RE] 어려워요   MENU01
    
=============================
2-2
CREATE TABLE MENUS
(
     MENU_ID     VARCHAR2(6)   NOT  NULL  PRIMARY KEY    
   , MENU_NAME   VARCHAR2(120) NOT  NULL
   , MENU_SEQ    NUMBER(5, 0)  NOT  NULL
)

INSERT INTO MENUS ( MENU_ID, MENU_NAME, MENU_SEQ)
 VALUES  ('MENU01', 'JAVA', 1);

COMMIT;     

CREATE  TABLE  BOARD
(
      IDX           NUMBER( 5, 0 )    PRIMARY KEY
    , MENU_ID       VARCHAR2(6)       NOT NULL
        REFERENCES   MENUS (MENU_ID) 
    , TITLE         VARCHAR2(300)     NOT NULL
    , CONT          VARCHAR2(4000) 
    , WRITTER        VARCHAR2(50) 
    , REGDATE       DATE              DEFAULT  SYSDATE
    , READCOUNT     NUMBER( 6, 0 )    DEFAULT  0
    
    , BNUM          NUMBER( 5, 0 )    DEFAULT  0
    , LVL           NUMBER( 5, 0 )    DEFAULT  0
    , STEP          NUMBER( 5, 0 )    DEFAULT  0
    , NREF          NUMBER( 5, 0 )    DEFAULT  0
    , DELNUM          NUMBER( 5, 0 )    DEFAULT  0
)

CREATE   TABLE    FILES
(
    FILE_NUM     NUMBER(6, 0)  NOT NULL   -- 파일번호
   , IDX         NUMBER(5, 0)  NOT NULL   -- 게시글 번호
   , FILENAME    VARCHAR2(300) NOT NULL   -- 파일명
   , FILEEXT     VARCHAR2(100) NOT NULL   -- 파일확장자
   , SFILENAME   VARCHAR2(300) NOT NULL   -- 저장된 실제 파일명
   
   , CONSTRAINT  FILES_PK     PRIMARY KEY  -- 기본키(복합키) 
     (
          FILE_NUM,
          IDX
     ) 
   , CONSTRAINT  FK_BOARD_FILES_IDX 
     FOREIGN KEY (IDX)
     REFERENCES  BOARD(IDX)
      ON  DELETE CASCADE
);


=======================
3. ORACLE 배열 

-- ORACLE 배열 -> TABLE
CREATE OR REPLACE 
  TYPE FILE_ARRAY 
   AS  TABLE OF
       VARCHAR2(4000);

======================
4. 시퀀스, 함수


--GET_FILENUM() 가능
--  ( SELECT NVL( MAX(FILE_NUM), 0)+1 FROM FILES ),  PK 위반 x
--  FILENUM_SEQ.NEXTVAL, PK 위반 X

-- 시퀀스
CREATE SEQUENCE FILENUM_SEQ;

-- 함수
CREATE  OR REPLACE FUNCTION  GET_FILENUM
RETURN 
   NUMBER   AS NUM  NUMBER;
BEGIN
   SELECT     FILENUM_SEQ.NEXTVAL
     INTO     NUM
     FROM     DUAL;
     RETURN   NUM;                                                             
END;
/
 
