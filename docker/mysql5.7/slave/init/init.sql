change master to master_host ='mysql-master',master_port =3306,master_user ='slave',master_password ='123456';

CREATE USER 'inoriii'@'%' IDENTIFIED BY '123456';
GRANT All privileges ON `hello_sring`.* TO 'inoriii'@'%';