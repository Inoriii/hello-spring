change master to master_host ='173.18.32.2',master_port =3306,master_user ='slave',master_password ='123456';

CREATE USER 'inoriii'@'%' IDENTIFIED BY '123456';
GRANT All privileges ON `hello_sring`.* TO 'inoriii'@'%';