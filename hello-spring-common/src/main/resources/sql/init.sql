CREATE TABLE `user_test`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `username`    varchar(32)  NOT NULL COMMENT '用户名称',
    `birthday`    datetime     NULL COMMENT '生日',
    `sex`         varchar(1)   NULL COMMENT '性别 0女1男',
    `address`     varchar(256) NULL COMMENT '地址',
    `create_time` datetime     NOT NULL default CURRENT_TIMESTAMP,
    `update_time` datetime     NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户信息表';