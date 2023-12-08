drop
database if exists shield_batch;
create
database shield_batch Default Charset utf8mb4 collate utf8mb4_general_ci;
use
shield_batch;

drop table if exists batch_job_config;
create table batch_job_config
(
    id                     int primary key auto_increment,
    name                   varchar(50)  not null comment '名称 ',
    description            varchar(255) comment '描述信息',
    monitor_file           varchar(255) comment '监听文件: 监听的单个文件，会监听该文件变化，匹配监听目录',
    load_file              varchar(255) comment '加载文件: 加载的单个文件，会加载该文件内容，匹配监听目录',
    column_mark            varchar(255) comment '列标记: 列标记，用于匹配列',
    column_name            varchar(255) comment '列名称: 列名称，用于匹配列',
    allow_ip               varchar(255) not null default '127.0.0.1' comment '执行器ip: 任务执行的ip地址',
    allow_time             varchar(255) not null default '08:00:00' comment '允许最早执行时间: 格式: HH:mm:ss',
    maximum_limit          int not null default 5000 comment '最大限制: 最大限制，当达到最大限制时，任务将不会再执行',
    api_url                varchar(255) not null comment '任务url: 调用的api地址，支持http和https',
    api_params             varchar(255) not null comment '任务参数: 调用api时传递的参数，支持json格式',
    format_func            varchar(255) not null default '00' comment '格式化函数: 格式化函数，用于格式化返回的数据: 00-表达式;01-Jar包',
    jar_name               varchar(255) comment 'jar包名称: 格式化函数为jar包时，需要指定jar包名称',
    class_name             varchar(255) comment '类名: 格式化函数为jar包时，需要指定类名',
    express                varchar(255) comment '表达式: 格式化函数为表达式时，需要指定表达式',
    result_file            varchar(255) not null default 'BAT_RESULT_#date_to_string(sysdate(), ''yyyyMMdd'').TXT' comment '结果文件: 结果文件，用于保存结果',
    result_flag            varchar(255) not null default 'BAT_RESULT_#date_to_string(sysdate(), ''yyyyMMdd'').FLAG' comment '结果标记: 结果标记，用于匹配结果',
    status                 int                   default 0 comment '状态: 0-未启用;1-启用;',
    remote_server_ip       varchar(255) comment '(optional)远程服务器ip',
    remote_server_port     int comment '(optional)远程服务器端口',
    remote_server_user     varchar(255) comment '(optional)远程服务器用户名',
    remote_server_password varchar(255) comment '(optional)远程服务器密码',
    remote_server_path     varchar(255) comment '(optional)远程服务器路径',
    create_uid             varchar(50)           default 'unknown' comment '创建用户',
    create_time            timestamp             default current_timestamp comment '创建时间',
    modify_uid             varchar(50)           default 'unknown' comment '修改用户',
    modify_time            timestamp             default current_timestamp comment '修改时间'
) comment '任务配置表';

drop table if exists batch_job;
create table batch_job
(
    id               int primary key auto_increment,
    config_id        int          not null comment '任务配置id',
    job_name         varchar(50)  not null comment '任务名称',
    job_status       varchar(2)   not null default '00' comment '任务状态00-未执行;01-执行中;02-执行成功;03-执行失败;04-推送中;05-推送失败;06-已取消',
    monitor_file     varchar(255) not null comment '监听文件: 绝对路径',
    load_file        varchar(255) not null comment '实际加载文件: 绝对路径',
    allow_ip         varchar(255) not null comment '执行器ip',
    start_time       timestamp    not null default current_timestamp comment '实际开始时间',
    end_time         timestamp    not null default current_timestamp comment '结束时间',
    total_count      int          not null comment '总数',
    success_count    int          not null comment '成功数',
    fail_count       int          not null comment '失败数',
    done_count       int          not null comment '完成数',
    result_count     int          not null comment '结果数',
    result_file      varchar(255) not null comment '结果文件: 绝对路径',
    result_flag_file varchar(255) not null comment '结果标记文件: 绝对路径',
    description      varchar(255) not null comment '描述信息',
    create_time      timestamp             default current_timestamp comment '创建时间',
    modify_time      timestamp             default current_timestamp comment '修改时间',
    finish_time      timestamp             default current_timestamp comment '完成时间'
) comment '任务表';

