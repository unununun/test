-- Create table
create table user_table
(
  id        number,
  user_name varchar2(100),
  password  varchar2(100),
  email     varchar2(100),
  role      number
)
;
-- Create/Recreate primary, unique and foreign key constraints 
alter table user_table
  add constraint PK_User_table_id primary key (ID);
-- Create/Recreate check constraints 
alter table user_table
  add constraint CHK_table_user_role
  check (role in(0,1));

-- Create sequence 
create sequence USER_TABLE_ID_SEQ
minvalue 2
maxvalue 999999999999999999999999999
start with 2
increment by 1
nocache;
  
insert into user_table(id,user_name,password,email,role) values(1,'haha','123456','haha@neusoft.com',0);