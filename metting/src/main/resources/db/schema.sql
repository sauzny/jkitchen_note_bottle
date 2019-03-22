CREATE TABLE IF NOT EXISTS `tb_order`(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '预定人名字',
  `room_id` int(10) NOT NULL DEFAULT '0' COMMENT '会议室id',
  `order_date` date DEFAULT NULL COMMENT '预定日期',
  `start_time` time DEFAULT NULL COMMENT '开始时间',
  `end_time` time DEFAULT NULL COMMENT '结束时间',
  `contact` varchar(255) NOT NULL DEFAULT '' COMMENT '联系方式',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '1表示删除，0表示未删除。',
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `tb_room`(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '会议室名字',
  `max_num` int(11) NOT NULL DEFAULT '0' COMMENT '会议室最大人数',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '1表示删除，0表示未删除。',
  PRIMARY KEY (`id`)
);