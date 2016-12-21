DROP TABLE IF EXISTS sequence;  
CREATE TABLE sequence (  
         name VARCHAR(50) NOT NULL,  
         current_value INT NOT NULL,  
         increment INT NOT NULL DEFAULT 1,  
         PRIMARY KEY (name)  
);

DROP FUNCTION IF EXISTS currval; 
CREATE FUNCTION currval (seq_name VARCHAR(50))  
         RETURNS INTEGER
BEGIN  
         DECLARE value INTEGER;  
         SET value = 0;  
         SELECT current_value INTO value  
                   FROM sequence  
                   WHERE name = seq_name;  
         RETURN value;  
END;  


DROP FUNCTION IF EXISTS nextval;
CREATE FUNCTION nextval (seq_name VARCHAR(50))  
         RETURNS INTEGER 
BEGIN  
         UPDATE sequence  
                   SET current_value = current_value + increment  
                   WHERE name = seq_name;  
         RETURN currval(seq_name);  
END;  


DROP FUNCTION IF EXISTS setval; 
CREATE FUNCTION setval (seq_name VARCHAR(50), value INTEGER)  
         RETURNS INTEGER 
BEGIN  
         UPDATE sequence  
                   SET current_value = value  
                   WHERE name = seq_name;  
         RETURN currval(seq_name);  
END;  


INSERT INTO sequence VALUES ('TestSeq', 0, 1);


/*SELECT SETVAL('TestSeq', 10);---����ָ��sequence�ĳ�ʼֵ
SELECT CURRVAL('TestSeq');--��ѯָ��sequence�ĵ�ǰֵ
SELECT NEXTVAL('TestSeq');--��ѯָ��sequence����һ��ֵ
*/




drop table if exists DM_PERMISSION;

drop table if exists DM_RES_TYPE;

drop table if exists QX_RESOURCES;

drop table if exists QX_ROLE;

drop table if exists QX_ROLE_RESOURCES;

drop table if exists QX_USER;

drop table if exists QX_USER_ROLE;

/*==============================================================*/
/* Table: DM_PERMISSION                                         */
/*==============================================================*/
create table DM_PERMISSION
(
   PERMISSION_DM        int(2) not null comment 'Ȩ��',
   PERMISSION_MC        varchar(80) not null comment 'Ȩ������',
   YXBZ                 char(1) not null default 'Y' comment '��Ч��־',
   primary key (PERMISSION_DM)
);

alter table DM_PERMISSION comment 'DM_PERMISSION';

/*==============================================================*/
/* Table: DM_RES_TYPE                                           */
/*==============================================================*/
create table DM_RES_TYPE
(
   RES_TYPE_DM          varchar(2) not null comment '��Դ����',
   RES_TYPE_MC          varchar(80) not null comment '��Դ��������',
   YXBZ                 char(1) not null default 'Y' comment '��Ч��־',
   primary key (RES_TYPE_DM)
);

alter table DM_RES_TYPE comment 'DM_RES_TYPE';

/*==============================================================*/
/* Table: QX_RESOURCES                                          */
/*==============================================================*/
create table QX_RESOURCES
(
   UUID                 varchar(32) not null comment 'UUID',
   NAME                 varchar(80) not null comment '����',
   PARENTUUID           varchar(32) not null comment '����UUID',
   LAYER                int(11) not null comment '�㼶',
   RES_TYPE_DM          varchar(2) not null comment '����',
   RESURL               varchar(512) comment '��ԴURL',
   DESCRIPTION          varchar(100) comment '����',
   LRRQ                 date not null comment '¼������',
   XGRQ                 date not null comment '�޸�����',
   YXBZ                 char(1) not null default 'Y' comment '��Ч��־',
   primary key (UUID)
);

alter table QX_RESOURCES comment '��Դ��';

/*==============================================================*/
/* Table: QX_ROLE                                               */
/*==============================================================*/
create table QX_ROLE
(
   UUID                 varchar(32) not null comment 'UUID',
   NAME                 varchar(80) not null comment '����',
   DESCRIPTION          varchar(100) comment '����',
   LRRQ                 date not null comment '¼������',
   XGRQ                 date not null comment '�޸�����',
   YXBZ                 char(1) not null default 'Y' comment '��Ч��־',
   primary key (UUID)
);

alter table QX_ROLE comment '��ɫ';

/*==============================================================*/
/* Table: QX_ROLE_RESOURCES                                     */
/*==============================================================*/
create table QX_ROLE_RESOURCES
(
   ROLE_UUID            varchar(32) not null comment '��ɫUUID',
   RESOURCES_UUID       varchar(32) not null comment '��ԴUUID',
   PERMISSION_DM        int(2) not null default 7 comment 'Ȩ��',
   primary key (ROLE_UUID, RESOURCES_UUID)
);

alter table QX_ROLE_RESOURCES comment '��ɫ��Դ';

/*==============================================================*/
/* Table: QX_USER                                               */
/*==============================================================*/
create table QX_USER
(
   UUID                 varchar(32) not null comment 'UUID',
   LOGINNAME            varchar(32) not null comment '��½����',
   PASSWORD             varchar(32) not null comment '����',
   SALT                 varchar(32) not null comment '����',
   NAME                 varchar(80) not null comment '����',
   EMAIL                varchar(255) comment '�ʼ�',
   DESCRIPTION          varchar(100) comment '����',
   LRRQ                 date not null comment '¼������',
   XGRQ                 date not null comment '�޸�����',
   YXBZ                 char(1) not null default 'Y' comment '��Ч��־',
   primary key (UUID)
);

alter table QX_USER comment '�û�';

/*==============================================================*/
/* Index: IDX_QX_USER_LOGINNAME                                 */
/*==============================================================*/
create unique index IDX_QX_USER_LOGINNAME on QX_USER
(
   LOGINNAME
);

/*==============================================================*/
/* Table: QX_USER_ROLE                                          */
/*==============================================================*/
create table QX_USER_ROLE
(
   USER_UUID            varchar(32) not null comment '�û�UUID',
   ROLE_UUID            varchar(32) not null comment '��ɫUUID',
   primary key (USER_UUID, ROLE_UUID)
);

alter table QX_USER_ROLE comment '�û���ɫ';




