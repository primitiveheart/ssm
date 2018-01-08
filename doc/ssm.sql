DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=myisam DEFAULT CHARSET=utf8;

BEGIN;
 INSERT INTO `t_role` VALUES ('1', 'admin'), ('2', 'teacher');
COMMIT;

BEGIN;
 INSERT INTO `t_user` VALUES ('1', 'zgb', '123456', '1'), ('2', 'aaa', '12345', '2'), ('3', 'bbb', '12345', null), ('4', 'ccc', '12345', null);
 COMMIT;



DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `permissionName` varchar(50) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
     PRIMARY KEY (`id`),
   KEY `roleId` (`roleId`) USING BTREE,
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=myisam DEFAULT CHARSET=utf8;


CREATE  TABLE `onair_vms_library`(
  `id` varchar(40) NOT NULL PRIMARY KEY ,
  `pid` VARCHAR(40),
  `name` VARCHAR(40),
  `create_time` TIMESTAMP ,
  `updata_time` TIMESTAMP ,
  `is_delete` int(1),
  `update_user` VARCHAR(30),
  `create_user` VARCHAR(30),
  `level_id` int(2),
  `order_id` int(11)
);
