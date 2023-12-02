# utf8mb4 是 utf8 的升级版，支持 4 字节的 Unicode 字符。(可支持Emoji表情或生僻字)
create database shield_dispatch Default Charset utf8mb4 collate utf8mb4_general_ci;
use shield_dispatch;

drop table if exists dispatch_business;
create table dispatch_business
(
    id          long primary key,
    code        varchar(10) unique not null comment '唯一编码',
    name        varchar(50)        not null comment '名称 ',
    description varchar(255) comment '描述信息',
    create_uid  varchar(50) default 'unknown' comment '创建用户',
    create_time timestamp   default current_time comment '创建时间',
    modify_uid  varchar(50) default 'unknown' comment '修改用户',
    modify_time timestamp   default current_time comment '修改时间'
) comment '业务表';

drop table if exists dispatch_param;
create table dispatch_param
(
    id               long primary key,
    name             varchar(50) unique not null comment '参数名称',
    name_cn          varchar(50)        not null comment '中文名称',
    mapping_name     varchar(50)        not null default name comment '映射名称',
    data_type        varchar(2)         not null default '00' comment '数据类型00-string;01-int;02-double;03-array;',
    must_input_check int                not null default 0 comment '必输校验0-非必输;1-必输',
    description      varchar(255)       not null comment '描述信息',
    create_uid       varchar(50)                 default 'unknown' comment '创建用户',
    create_time      timestamp                   default current_time comment '创建时间',
    modify_uid       varchar(50)                 default 'unknown' comment '修改用户',
    modify_time      timestamp                   default current_time comment '修改时间'
) comment '请求参数表';

drop table if exists dispatch_dictionary;
create table dispatch_dictionary
(
    id          long primary key,
    code        varchar(50) unicode not null comment '字典代码',
    value       varchar(50)         not null comment '字典值',
    name_cn     varchar(255)        not null comment '字典中文',
    description varchar(255) comment '字典中文描述',
    create_uid  varchar(50) default 'unknown' comment '创建用户',
    create_time timestamp   default current_time comment '创建时间',
    modify_uid  varchar(50) default 'unknown' comment '修改用户',
    modify_time timestamp   default current_time comment '修改时间'
) comment '字典表';

