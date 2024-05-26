-- 创建数据库
CREATE DATABASE IF NOT EXISTS test;

-- 切换到新创建的数据库
USE test;

DROP table users;

CREATE TABLE users (
                       id int AUTO_INCREMENT PRIMARY KEY,
                       user_id VARCHAR(50),
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       first_name VARCHAR(50),
                       last_name VARCHAR(50),
                       created_at datetime,
                       updated_at datetime,
                       is_Deleted int default 1
);

UPDATE users
SET updated_at = updated_at + INTERVAL 1 SECOND