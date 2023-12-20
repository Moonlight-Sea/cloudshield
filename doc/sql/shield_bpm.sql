drop database if exists shield_bpm;
create database shield_bpm;
use shield_bpm;

-- 流程定义表: 包括流程定义的ID、KEY、版本、名称、描述、流程图等信息
drop table if exists bpm_flow_define;
create table bpm_flow_define
(
    uuid         varchar(50)                              not null primary key,
    flow_key     varchar(255)                             not null comment '流程KEY',
    flow_name    varchar(255)                             not null comment '流程名称',
    flow_desc    varchar(255)                             not null default '' comment '流程描述',
    flow_status  varchar(255)                             not null comment '流程状态:00-关闭;01-启用',
    flow_version int                                      not null comment '流程版本',
    create_time  datetime     default current_timestamp() not null comment '创建时间',
    update_time  datetime     default current_timestamp() not null comment '更新时间',
    create_user  varchar(255) default 'Unknown'           not null,
    update_user  varchar(255) default 'Unknown'           not null
);

# 流程实例表: 包括流程实例的ID、KEY、状态、开始时间、结束时间、持续时间等信息。
drop table if exists bpm_flow_instance;
create table bpm_flow_instance
(
    uuid         varchar(50)                              not null primary key,
    flow_id      varchar(50)                              not null,
    flow_key     varchar(255)                             not null comment '流程KEY',
    flow_status  varchar(255)                             not null comment '流程状态:00-运行结束;01-运行中',
    flow_version int                                      not null comment '流程版本',
    params       varchar(255)                             not null comment '流程参数(JSON)',
    result       varchar(255)                             not null comment '流程结果(JSON)',
    create_time  datetime     default current_timestamp() not null comment '创建时间',
    update_time  datetime     default current_timestamp() not null comment '更新时间',
    create_user  varchar(255) default 'Unknown'           not null,
    update_user  varchar(255) default 'Unknown'           not null
);

# 任务数据模型: 包括任务的ID、名称、描述、处理人、创建时间、到期时间、优先级等信息。
drop table if exists bpm_flow_task;
create table bpm_flow_task
(
    uuid         varchar(50)                              not null primary key,
    flow_id      varchar(50)                              not null,
    task_name    varchar(50)                              not null comment '任务名称',
    task_type    varchar(255)                             not null comment '任务类型:00-事件;02-网关(逻辑门)',
    task_order   int                                      not null comment '任务顺序、优先级',
    task_status  varchar(255)                             not null comment '任务状态:00-关闭;01-启用',
    task_version int                                      not null comment '任务版本',
    task_expire  datetime comment '任务到期时间',
    create_time  datetime     default current_timestamp() not null comment '创建时间',
    update_time  datetime     default current_timestamp() not null comment '更新时间',
    create_user  varchar(255) default 'Unknown'           not null,
    update_user  varchar(255) default 'Unknown'           not null
);

-- 流程节点属性表
drop table if exists bpm_flow_task_attr;
create table bpm_flow_task_attr
(
    uuid              varchar(50)                              not null primary key,
    task_id           varchar(50)                              not null,
    task_attr_type    varchar(255)                             not null comment '节点属性:A00-',
    task_attr_key     varchar(255)                             not null comment '节点属性键',
    task_attr_value   varchar(255)                             not null comment '节点属性值(aviatorScript)',
    task_attr_sort    int                                      not null comment '节点属性排序',
    task_attr_status  varchar(255)                             not null comment '节点属性状态:00-关闭;01-启用',
    task_attr_express varchar(255) comment '任务表达式',
    task_attr_path    varchar(255) comment '任务jar包路径',
    task_attr_version int                                      not null comment '节点属性版本',
    create_time       datetime     default current_timestamp() not null comment '创建时间',
    update_time       datetime     default current_timestamp() not null comment '更新时间',
    create_user       varchar(255) default 'Unknown'           not null,
    update_user       varchar(255) default 'Unknown'           not null
);

# 任务执行历史表
drop table if exists bpm_flow_task_record;
create table bpm_flow_task_record
(
    uuid         varchar(50)                              not null primary key,
    flow_id      varchar(50)                              not null,
    task_id      varchar(50)                              not null,
    task_status  varchar(255)                             not null comment '任务状态:00-关闭;01-启用',
    task_version int                                      not null comment '任务版本',
    params       varchar(255)                             not null comment '任务参数(JSON)',
    result       varchar(255)                             not null comment '任务结果(JSON)',
    create_time  datetime     default current_timestamp() not null comment '创建时间',
    update_time  datetime     default current_timestamp() not null comment '更新时间',
    create_user  varchar(255) default 'Unknown'           not null,
    update_user  varchar(255) default 'Unknown'           not null
);