
-- 权限组件
-- 用户
DROP TABLE IF EXISTS `oauth_user`;
CREATE TABLE `oauth_user` (
  `id` char(36) NOT NULL ,
  `avatar` varchar(255) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `first_name` varchar(255) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `mail_address` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mail_address`),
  KEY  (`id`),
  KEY  (`mail_address`)
);

-- 角色
DROP TABLE IF EXISTS `oauth_role`;
CREATE TABLE `oauth_role` (
  `id` char(36) NOT NULL ,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

-- 用户角色关系表
DROP TABLE IF EXISTS `oauth_user_role_rel`;
CREATE TABLE `oauth_user_role_rel` (
  `role_id` char(36) NOT NULL ,
  `user_id` char(36) NOT NULL ,
  PRIMARY KEY (`user_id`,`role_id`),
  CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `oauth_user` (`id`),
  CONSTRAINT  FOREIGN KEY (`role_id`) REFERENCES `oauth_role` (`id`)
);


-- 可用权限
DROP TABLE IF EXISTS `oauth_privilege`;
CREATE TABLE `oauth_privilege` (
  `id` char(36) NOT NULL ,
  `detail` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);


-- 可用权限与角色关系表
DROP TABLE IF EXISTS `oauth_privilege_role_rel`;
CREATE TABLE `oauth_privilege_role_rel` (
  `role_id` char(36) NOT NULL ,
  `privilege_id` char(36) NOT NULL ,
  PRIMARY KEY (`privilege_id`,`role_id`),
  CONSTRAINT  FOREIGN KEY (`privilege_id`) REFERENCES `oauth_privilege` (`id`),
  CONSTRAINT  FOREIGN KEY (`role_id`) REFERENCES `oauth_role` (`id`)
);
