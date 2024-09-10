# utf8mb4 是 utf8 的升级版，支持 4 字节的 Unicode 字符。(可支持Emoji表情或生僻字)
drop database if exists shield_store;
create database shield_store Default Charset utf8mb4 collate utf8mb4_general_ci;
use shield_store;