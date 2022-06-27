DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test`
(
    `id`          int(11)                                                 NOT NULL AUTO_INCREMENT,
    `username`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户名称',
    `birthday`    date                                                    NULL     DEFAULT NULL COMMENT '出生日期',
    `sex`         char(1) CHARACTER SET utf8 COLLATE utf8_general_ci      NULL     DEFAULT NULL COMMENT '性别 0:男 1：女',
    `address`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '地址',
    `create_time` datetime(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
    `update_time` datetime(0)                                             NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 500001
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;