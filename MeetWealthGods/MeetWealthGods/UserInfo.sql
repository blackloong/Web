DROP TABLE IF EXISTS `BogusWXFriendsUser`;

CREATE TABLE `BogusWXFriendsUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `headimgurl` varchar(200) DEFAULT NULL COMMENT '头像',
  `sex` tinyint(2) DEFAULT '1' COMMENT '性别',
  `addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;