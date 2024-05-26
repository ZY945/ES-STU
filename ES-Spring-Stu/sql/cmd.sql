# 是否开启日志
show variables like '%log_bin%';


select * from mysql.user;

CREATE USER canal IDENTIFIED BY 'canal';
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
FLUSH PRIVILEGES;

select * from mysql.user;

