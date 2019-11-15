use home;
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `contact` varchar(100) NOT NULL COMMENT '联系方式',
  `login_name` varchar(50) NOT NULL COMMENT '登陆名',
  `login_pwd` varchar(50) NOT NULL COMMENT '登陆密码',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户主表';

DROP TABLE IF EXISTS `auth_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `auth_dictionary` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) NOT NULL DEFAULT '权限描述',
  `code` varchar(100) NOT NULL DEFAULT '权限代码',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限字典表';

DROP TABLE IF EXISTS `user_auth_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `user_auth_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `auth_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权限关联表';

DROP TABLE IF EXISTS `menu_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `menu_dictionary` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父菜单id',
  `name` varchar(100) NOT NULL DEFAULT '菜单名称',
  `code` varchar(100) NOT NULL DEFAULT '菜单代码',
  `desc` text DEFAULT NULL COMMENT '菜单描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单字典表';

DROP TABLE IF EXISTS `auth_menu_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `auth_menu_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `auth_id` bigint(20) NOT NULL COMMENT '权限id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限菜单关联';

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `family` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) DEFAULT NULL COMMENT '组名称',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='家庭表';

DROP TABLE IF EXISTS `family_member_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `family_member_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `family_id` bigint(20) NOT NULL COMMENT '家庭id',
  `member_id` bigint(20) NOT NULL COMMENT '成员id',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='家庭成员关联表';

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `member` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) DEFAULT NULL COMMENT '成员名称',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '成员昵称',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成员表';

DROP TABLE IF EXISTS `user_family_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `user_family_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `family_id` bigint(20) NOT NULL COMMENT '家庭id',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户家庭关联表';

DROP TABLE IF EXISTS `email_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `email_job` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(50) DEFAULT NULL COMMENT '任务名',
  `sender` varchar(50) DEFAULT NULL COMMENT '邮件发件人',
  `auth_code` varchar(50) DEFAULT NULL COMMENT '邮件发件授权码',
  `type` tinyint(4) DEFAULT NULL COMMANT '邮件类型, 1主题邮件, 2模板邮件',
  `subject` text DEFAULT NULL COMMENT '邮件主题',
  `context` text DEFAULT NULL COMMENT '邮件文本',
  `template_id` bigint(20) DEFAULT NULL COMMENT '邮件模板id',
  `last_run_time` datetime DEFAULT NULL COMMENT '上次运行任务时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件任务列表';

DROP TABLE IF EXISTS `email_job_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `email_job_rule` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `email_id` bigint(20) NOT NULL COMMENT '邮件任务id',
  `start_time` time NOT NULL COMMENT '开始发送时间点',
  `end_time` time DEFAULT NULL COMMENT '结束发送时间点',
  `start_date` date NOT NULL COMMENT '开始发送日期',
  `end_date` date DEFAULT NULL COMMENT '结束发送日期',
  `interval` int(11) NOT NULL COMMENT '发送间隔,单位分钟',
  `rule_sort` int(11) NOT NULL COMMENT '规则序号',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件发件时间规则表';

DROP TABLE IF EXISTS `food_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `food_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `title` varchar(255) default null comment '标题',
  `remark` varchar(1000) default null comment '描述',
  `material` varchar(255) default null comment '原料',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食谱主表';

DROP TABLE IF EXISTS `food_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `food_step` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `food_menu_id` bigint(20) NOT NULL COMMENT '食谱id',
  `remark` varchar(1000) default null comment '步骤描述',
  `material` varchar(255) default null comment '原料',
  `step` int(11) NOT NULL COMMENT '步骤',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食谱步骤';

DROP TABLE IF EXISTS `video_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `video_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `group_name` varchar(255) NOT NULL COMMENT '组名',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `remark` text default null comment '备注描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频组别';

DROP TABLE IF EXISTS `video_series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `video_series` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `group_id` bigint(20) NOT NULL COMMENT '隶属组别',
  `series_name` varchar(255) NOT NULL COMMENT '季名',
  `series_num` int(11) NOT NULL COMMENT '季数',
  `total_episode` int(11) NOT NULL COMMENT '总集数',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `remark` text default null comment '备注描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频季别';

DROP TABLE IF EXISTS `video_episode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `video_episode` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `series_id` bigint(20) NOT NULL COMMENT '隶属季别',
  `episode_name` varchar(255) NOT NULL COMMENT '集名',
  `episode_num` int(11) NOT NULL COMMENT '集数',
  `next_episode_id` bigint(20) NOT NULL COMMENT '下一集',
  `local_url` text NOT NULL COMMENT '本地路径',
  `web_url` text NOT NULL COMMENT '网络路径',
  `memory` int(11) NOT NULL COMMENT '大小, 单位MB',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `play_count` int(11) NOT NULL DEFAULT 0 COMMENT '播放次数',
  `remark` text default null comment '备注描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频集信息';

DROP TABLE IF EXISTS `jk_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4;
CREATE TABLE `jk_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) NOT NULL COMMENT 'jk名称',
  `style` varchar(255) NOT NULL COMMENT '风格',
  `color` varchar(20) NOT NULL COMMENT '颜色',
  `price` DECIMAL(10,2) default NULL COMMENT '价格',
  `type` tinyint(4) NOT NULL COMMENT '类型',
  `remark` text default null comment '备注描述',
  `preview_img` varchar(255) default null comment '预览图片',
  `full_img` text default null comment '完整图片列表',
  `obj_model` varchar(255) default null comment 'obj模型',
  `mtl_model` varchar(255) default null comment 'mtl模型',
  `sale_time` datetime default null COMMENT '售卖时间',
  `buy_time` datetime default null COMMENT '购买时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='jk信息';