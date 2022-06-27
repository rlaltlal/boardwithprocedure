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
    
    
