# Host: 127.0.0.1  (Version: 5.5.32)
# Date: 2013-12-03 23:07:58
# Generator: MySQL-Front 5.3  (Build 4.43)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "article_class"
#

DROP TABLE IF EXISTS `article_class`;
CREATE TABLE `article_class` (
  `ArticleClassId` int(11) NOT NULL AUTO_INCREMENT,
  `ArticleClassName` varchar(50) NOT NULL DEFAULT '',
  `ArticleClassShowType` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ArticleClassId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "article_class"
#

INSERT INTO `article_class` VALUES (1,'教师风采',1),(2,'儿童天地',0),(3,'教师心得',0),(4,'园所新闻',0),(5,'育儿技巧',0);

#
# Structure for table "article_record"
#

DROP TABLE IF EXISTS `article_record`;
CREATE TABLE `article_record` (
  `ArticleId` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(50) NOT NULL DEFAULT '',
  `Date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ImagePath` varchar(100) DEFAULT NULL,
  `Content` varchar(2000) NOT NULL DEFAULT '',
  `ArticleClassId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ArticleId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "article_record"
#

INSERT INTO `article_record` VALUES (1,'家长如何和孩子谈心','2013-12-01 23:38:55','/userimages/fcd2f6cae9ff43d9bf9a6a112380d49a','你常和自己的孩子谈心吗?也许你会说:小毛孩子，啥也不懂，有什么好谈的?或者认为，一天到晚忙个不休，哪有功夫和小家伙解嘴皮才还有一些家长。生怕有损于自己的‘“尊严“，对孩子，开口便训，更不用说谈心了、其实，谈心是对孩子进行教育的一种行之有效的方法，做父毋的都应该学会运用这种方法。\n<br/>\n怎样和孩子谈心呢?<br/>\n第一，要明确谈心的作用语言是思维的信号，通过谈心，父毋可以了解孩子想些什么，便于有针对性地进行教育，通过谈心，父母可以和孩子交换思想，增进感情，有些家长强调工作、家务忙，没有时间同孩子谈心，如果他们懂得了谈心的作用，一定能够找到机会的。<br/>\n第二，要了解孩子的心理特点上小学的孩子，心直口快，喜怒衰乐都挂在脸上。他们在学校碰到高兴的事或不愉快的事，往往不等父母问，就会一古脑儿倒出来，这个时期，孩子的思想是向家长敞开的、但是，随着年龄的增长，孩子的心理也相应地会起变化，上中学的孩子就不再象上小学时那么直爽了，话也说得少了，似乎和爸爸妈妈不那么贴心了，常常不肯向父母吐露直情，国外有的心理学家称这一时期为“青少年闭锁期”。处于这一时期的孩子，虽然沉默寡言，把秘密埋藏在心里。但同时又希望被父毋和其他人理解了解了孩子的这些矛盾的心理特点，家长就应主动接近孩子，经常有意识地找孩子谈心，及时消除他们的思想顾虑，使孩子有话愿意和父母讲、只有这样，家长才能掌握孩子的思想脉络，有针对性地进行教育，<br/>\n第三，父母要用平等的态度和孩子谈心。孩子年龄小，不容易理解深奥的道理，当孩子对一咎问题理解不了时，家长不能训斥，要做到”三心”。即“，细心”、，‘耐心”和“恒心”。细心，就是要仔细观察分析孩子的言行，善于发现孩子身上的优点和缺点;耐心，就是要态度和蔼，循循许诱，不搞“一言堂”，更不能搞“审讯式”，恒心。就是要有打“持久战”的精神，反复给孩子作思想工作，直到做通。总之，家长只有用平等的态度和孩子谈心，才可能有成效。',5);

#
# Structure for table "lottery_activity"
#

DROP TABLE IF EXISTS `lottery_activity`;
CREATE TABLE `lottery_activity` (
  `LotteryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '抽奖活动ID',
  `LotteryName` varchar(50) DEFAULT '' COMMENT '抽奖活动名称',
  `LotterySummary` varchar(500) DEFAULT '' COMMENT '抽奖活动介绍',
  `LotteryPicture` varchar(100) DEFAULT NULL COMMENT '抽奖活动背景图片地址',
  `StartDate` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '抽奖活动起始日期',
  `EndDate` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '抽奖活动结束日期',
  `ChanceNum` int(11) DEFAULT '0' COMMENT '最大抽奖次数',
  `LotteryStatus` int(11) NOT NULL DEFAULT '0' COMMENT '抽奖活动状态，约束(0,1,2)',
  PRIMARY KEY (`LotteryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "lottery_activity"
#


#
# Structure for table "lottery_lucky_record"
#

DROP TABLE IF EXISTS `lottery_lucky_record`;
CREATE TABLE `lottery_lucky_record` (
  `LuckyId` int(11) NOT NULL AUTO_INCREMENT COMMENT '中奖记录ID',
  `PrizeId` int(11) NOT NULL DEFAULT '0' COMMENT '奖项ID',
  `OpenId` varchar(30) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `PrizeKey` varchar(15) NOT NULL DEFAULT '' COMMENT '中奖码',
  `PrizeStatus` int(11) NOT NULL DEFAULT '0' COMMENT '是否已兑奖',
  PRIMARY KEY (`LuckyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "lottery_lucky_record"
#


#
# Structure for table "lottery_prize"
#

DROP TABLE IF EXISTS `lottery_prize`;
CREATE TABLE `lottery_prize` (
  `PrizeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '奖项ID',
  `LotteryId` int(11) NOT NULL DEFAULT '0' COMMENT '抽奖活动ID',
  `PrizeName` varchar(10) NOT NULL DEFAULT '' COMMENT '奖项名称',
  `PrizeContent` varchar(100) NOT NULL DEFAULT '' COMMENT '奖项内容',
  `LuckyNum` int(11) NOT NULL DEFAULT '0' COMMENT '中奖人数',
  `LuckyPercent` decimal(12,5) NOT NULL DEFAULT '0.00000' COMMENT '中奖概率',
  PRIMARY KEY (`PrizeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "lottery_prize"
#


#
# Structure for table "lottery_record"
#

DROP TABLE IF EXISTS `lottery_record`;
CREATE TABLE `lottery_record` (
  `RecordId` int(11) NOT NULL AUTO_INCREMENT COMMENT '抽奖记录ID',
  `LotteryId` int(11) NOT NULL DEFAULT '0' COMMENT '抽奖活动ID',
  `OpenId` varchar(30) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `LotteryResult` int(11) NOT NULL DEFAULT '0' COMMENT '抽奖结果（是否中奖）约束：(0,1)',
  PRIMARY KEY (`RecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "lottery_record"
#


#
# Structure for table "message_record"
#

DROP TABLE IF EXISTS `message_record`;
CREATE TABLE `message_record` (
  `MessageId` int(11) NOT NULL AUTO_INCREMENT,
  `Content` varchar(500) DEFAULT NULL,
  `UserName` varchar(30) DEFAULT NULL,
  `Contact` varchar(50) DEFAULT NULL,
  `Date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`MessageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "message_record"
#

INSERT INTO `message_record` VALUES (1,'adsf',NULL,'dsfa',NULL),(2,'1231','bai','123','2013-12-01 16:39:39'),(3,'adsf','asdf','adf','2013-12-01 16:42:56'),(4,'asfsad','','','2013-12-01 16:46:09'),(5,'adsfasff','','','2013-12-01 16:46:20'),(6,'阿斯蒂芬飞','阿斯蒂芬','爱的色放飞','2013-12-01 18:19:06'),(7,'           阿斯蒂芬飞       ','阿斯蒂芬','爱的色放飞','2013-12-01 18:19:13'),(8,'           阿斯蒂芬飞       ','阿斯蒂芬','爱的色放飞','2013-12-01 18:27:09'),(9,'','','','2013-12-01 18:28:26'),(10,'','','','2013-12-01 18:29:17'),(11,'','','','2013-12-01 18:29:29'),(12,'asdfaadsfs','','','2013-12-01 18:38:30');

#
# Structure for table "order_dishes"
#

DROP TABLE IF EXISTS `order_dishes`;
CREATE TABLE `order_dishes` (
  `DishId` int(11) NOT NULL AUTO_INCREMENT COMMENT '餐馆名称',
  `DishName` varchar(20) NOT NULL DEFAULT '' COMMENT '菜品名称',
  `Price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '菜品单价',
  `Discribe` varchar(500) NOT NULL DEFAULT '' COMMENT '菜品描述',
  `Picture` varchar(100) DEFAULT NULL COMMENT '菜品图片',
  PRIMARY KEY (`DishId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

#
# Data for table "order_dishes"
#

INSERT INTO `order_dishes` VALUES (3,'鱼香肉丝',19.50,'美味可口，香气难挡','/XXXXX/XXX.jpg'),(4,'鱼香肉丝',19.50,'美味可口，香气难挡','/XXXXX/XXX.jpg'),(5,'鱼香肉丝',19.50,'美味可口，香气难挡','/XXXXX/XXX.jpg'),(7,'小鸡蘑菇',30.00,'美味可口，香气难挡','/XXXXX/XXX.jpg'),(8,'鱼香肉丝',19.50,'美味可口，香气难挡','/XXXXX/XXX.jpg'),(9,'鱼香肉丝',19.50,'美味可口，香气难挡','/XXXXX/XXX.jpg'),(10,'鱼香肉丝',19.50,'美味可口，香气难挡','/XXXXX/XXX.jpg'),(11,'鱼香肉丝',19.50,'美味可口，香气难挡','/XXXXX/XXX.jpg');

#
# Structure for table "order_menu"
#

DROP TABLE IF EXISTS `order_menu`;
CREATE TABLE `order_menu` (
  `MenuId` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `Date` int(11) NOT NULL DEFAULT '0' COMMENT '周X（例周六：X=6），约束（1,2,3,4,5,6,7）',
  `Dish` varchar(100) NOT NULL DEFAULT '0' COMMENT '可选菜品ID',
  PRIMARY KEY (`MenuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_menu"
#


#
# Structure for table "order_store"
#

DROP TABLE IF EXISTS `order_store`;
CREATE TABLE `order_store` (
  `StoreId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订餐餐馆ID',
  `StoreName` varchar(20) NOT NULL DEFAULT '' COMMENT '餐馆名称',
  PRIMARY KEY (`StoreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_store"
#


#
# Structure for table "order_user"
#

DROP TABLE IF EXISTS `order_user`;
CREATE TABLE `order_user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户在数据库中的ID',
  `OpenId` varchar(30) NOT NULL DEFAULT '' COMMENT '微信返回的用户ID',
  `Address` varchar(30) NOT NULL DEFAULT '' COMMENT '订餐配送地址',
  `Phone` varchar(11) NOT NULL DEFAULT '' COMMENT '配送电话',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_user"
#


#
# Structure for table "order_user_order"
#

DROP TABLE IF EXISTS `order_user_order`;
CREATE TABLE `order_user_order` (
  `OrderId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `Date` date NOT NULL DEFAULT '0000-00-00' COMMENT '订单日期',
  `DishId` int(11) NOT NULL DEFAULT '0' COMMENT '菜品ID',
  `DishNum` int(11) NOT NULL DEFAULT '0' COMMENT '菜品份数',
  `Price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单个菜品总价',
  `State` int(11) NOT NULL DEFAULT '0' COMMENT '订餐状态',
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_user_order"
#


#
# Structure for table "order_wiki"
#

DROP TABLE IF EXISTS `order_wiki`;
CREATE TABLE `order_wiki` (
  `Wiki` varchar(500) NOT NULL DEFAULT '',
  `WikiId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`WikiId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_wiki"
#


#
# Structure for table "recommend_store"
#

DROP TABLE IF EXISTS `recommend_store`;
CREATE TABLE `recommend_store` (
  `StoreId` int(11) NOT NULL AUTO_INCREMENT COMMENT '分店ID',
  `StoreName` varchar(20) NOT NULL DEFAULT '' COMMENT '分店名',
  `Address` varchar(30) NOT NULL DEFAULT '' COMMENT '分店地址',
  `Coordinate` varchar(50) NOT NULL DEFAULT '' COMMENT '坐标',
  PRIMARY KEY (`StoreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "recommend_store"
#


#
# Structure for table "song_catalog"
#

DROP TABLE IF EXISTS `song_catalog`;
CREATE TABLE `song_catalog` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StyleId` int(11) NOT NULL DEFAULT '0',
  `Link` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8;

#
# Data for table "song_catalog"
#

INSERT INTO `song_catalog` VALUES (1,1,'http://www.xiami.com/widget/player-single?uid=0&sid=2568232&mode=js'),(2,1,'http://www.xiami.com/widget/player-single?uid=0&sid=2568234&mode=js'),(3,1,'http://www.xiami.com/widget/player-single?uid=0&sid=55901&mode=js'),(4,1,'http://www.xiami.com/widget/player-single?uid=0&sid=142936&mode=js'),(5,1,'http://www.xiami.com/widget/player-single?uid=0&sid=37434&mode=js'),(6,1,'http://www.xiami.com/widget/player-single?uid=0&sid=42508&mode=js'),(7,1,'http://www.xiami.com/widget/player-single?uid=0&sid=71550&mode=js'),(8,1,'http://www.xiami.com/widget/player-single?uid=0&sid=93356&mode=js'),(9,1,'http://www.xiami.com/widget/player-single?uid=0&sid=2071375&mode=js'),(10,1,'http://www.xiami.com/widget/player-single?uid=0&sid=2101117&mode=js'),(11,1,'http://www.xiami.com/widget/player-single?uid=0&sid=66202&mode=js'),(12,1,'http://www.xiami.com/widget/player-single?uid=0&sid=3623221&mode=js'),(13,1,'http://www.xiami.com/widget/player-single?uid=0&sid=86949&mode=js'),(14,1,'http://www.xiami.com/widget/player-single?uid=0&sid=93366&mode=js'),(15,1,'http://www.xiami.com/widget/player-single?uid=0&sid=31589&mode=js'),(16,1,'http://www.xiami.com/widget/player-single?uid=0&sid=2105440&mode=js'),(17,1,'http://www.xiami.com/widget/player-single?uid=0&sid=66906&mode=js'),(18,1,'http://www.xiami.com/widget/player-single?uid=0&sid=22630&mode=js'),(19,1,'http://www.xiami.com/widget/player-single?uid=0&sid=3402688&mode=js'),(20,1,'http://www.xiami.com/widget/player-single?uid=0&sid=384812&mode=js'),(21,1,'http://www.xiami.com/widget/player-single?uid=0&sid=80738&mode=js'),(22,1,'http://www.xiami.com/widget/player-single?uid=0&sid=378828&mode=js'),(23,1,'http://www.xiami.com/widget/player-single?uid=0&sid=145504&mode=js'),(24,1,'http://www.xiami.com/widget/player-single?uid=0&sid=391236&mode=js'),(25,1,'http://www.xiami.com/widget/player-single?uid=0&sid=46818&mode=js'),(26,1,'http://www.xiami.com/widget/player-single?uid=0&sid=181461&mode=js'),(27,1,'http://www.xiami.com/widget/player-single?uid=0&sid=3400401&mode=js'),(28,1,'http://www.xiami.com/widget/player-single?uid=0&sid=2079912&mode=js'),(29,1,'http://www.xiami.com/widget/player-single?uid=0&sid=375918&mode=js'),(30,1,'http://www.xiami.com/widget/player-single?uid=0&sid=3499049&mode=js'),(31,1,'http://www.xiami.com/widget/player-single?uid=0&sid=78891&mode=js'),(32,1,'http://www.xiami.com/widget/player-single?uid=0&sid=121974&mode=js'),(33,1,'http://www.xiami.com/widget/player-single?uid=0&sid=118362&mode=js'),(34,1,'http://www.xiami.com/widget/player-single?uid=0&sid=11396&mode=js'),(35,1,'http://www.xiami.com/widget/player-single?uid=0&sid=96116&mode=js'),(36,1,'http://www.xiami.com/widget/player-single?uid=0&sid=158465&mode=js'),(37,1,'http://www.xiami.com/widget/player-single?uid=0&sid=145908&mode=js'),(38,1,'http://www.xiami.com/widget/player-single?uid=0&sid=103066&mode=js'),(39,1,'http://www.xiami.com/widget/player-single?uid=0&sid=106884&mode=js'),(40,1,'http://www.xiami.com/widget/player-single?uid=0&sid=392109&mode=js'),(41,1,'http://www.xiami.com/widget/player-single?uid=0&sid=24929&mode=js'),(42,1,'http://www.xiami.com/widget/player-single?uid=0&sid=178901&mode=js'),(43,1,'http://www.xiami.com/widget/player-single?uid=0&sid=162422&mode=js'),(44,1,'http://www.xiami.com/widget/player-single?uid=0&sid=2077453&mode=js'),(45,1,'http://www.xiami.com/widget/player-single?uid=0&sid=179525&mode=js'),(46,1,'http://www.xiami.com/widget/player-single?uid=0&sid=24122&mode=js'),(47,1,'http://www.xiami.com/widget/player-single?uid=0&sid=142833&mode=js'),(48,2,'http://www.xiami.com/widget/player-single?uid=0&sid=381698&mode=js'),(49,2,'http://www.xiami.com/widget/player-single?uid=0&sid=376715&mode=js'),(50,2,'http://www.xiami.com/widget/player-single?uid=0&sid=158465&mode=js'),(51,2,'http://www.xiami.com/widget/player-single?uid=0&sid=174652&mode=js'),(52,2,'http://www.xiami.com/widget/player-single?uid=0&sid=378046&mode=js'),(53,2,'http://www.xiami.com/widget/player-single?uid=0&sid=72829&mode=js'),(54,2,'http://www.xiami.com/widget/player-single?uid=0&sid=3529496&mode=js'),(55,2,'http://www.xiami.com/widget/player-single?uid=0&sid=139691&mode=js'),(56,2,'http://www.xiami.com/widget/player-single?uid=0&sid=10099&mode=js'),(57,2,'http://www.xiami.com/widget/player-single?uid=0&sid=174697&mode=js'),(58,2,'http://www.xiami.com/widget/player-single?uid=0&sid=374344&mode=js'),(59,2,'http://www.xiami.com/widget/player-single?uid=0&sid=386984&mode=js'),(60,2,'http://www.xiami.com/widget/player-single?uid=0&sid=385368&mode=js'),(61,2,'http://www.xiami.com/widget/player-single?uid=0&sid=54811&mode=js'),(62,2,'http://www.xiami.com/widget/player-single?uid=0&sid=73669&mode=js'),(63,2,'http://www.xiami.com/widget/player-single?uid=0&sid=41303&mode=js'),(64,2,'http://www.xiami.com/widget/player-single?uid=0&sid=45984&mode=js'),(65,2,'http://www.xiami.com/widget/player-single?uid=0&sid=383611&mode=js'),(66,2,'http://www.xiami.com/widget/player-single?uid=0&sid=400120&mode=js'),(67,2,'http://www.xiami.com/widget/player-single?uid=0&sid=29059&mode=js'),(68,2,'http://www.xiami.com/widget/player-single?uid=0&sid=377521&mode=js'),(69,2,'http://www.xiami.com/widget/player-single?uid=0&sid=106904&mode=js'),(70,2,'http://www.xiami.com/widget/player-single?uid=0&sid=58602&mode=js'),(71,2,'http://www.xiami.com/widget/player-single?uid=0&sid=58840&mode=js'),(72,2,'http://www.xiami.com/widget/player-single?uid=0&sid=375028&mode=js'),(73,2,'http://www.xiami.com/widget/player-single?uid=0&sid=146898&mode=js'),(74,2,'http://www.xiami.com/widget/player-single?uid=0&sid=61533&mode=js'),(75,2,'http://www.xiami.com/widget/player-single?uid=0&sid=106853&mode=js'),(76,2,'http://www.xiami.com/widget/player-single?uid=0&sid=381985&mode=js'),(77,2,'http://www.xiami.com/widget/player-single?uid=0&sid=90620&mode=js'),(78,2,'http://www.xiami.com/widget/player-single?uid=0&sid=381643&mode=js'),(79,2,'http://www.xiami.com/widget/player-single?uid=0&sid=134999&mode=js'),(80,2,'http://www.xiami.com/widget/player-single?uid=0&sid=39821&mode=js'),(81,2,'http://www.xiami.com/widget/player-single?uid=0&sid=22630&mode=js'),(82,2,'http://www.xiami.com/widget/player-single?uid=0&sid=42508&mode=js'),(83,2,'http://www.xiami.com/widget/player-single?uid=0&sid=73667&mode=js'),(84,2,'http://www.xiami.com/widget/player-single?uid=0&sid=23998&mode=js'),(85,2,'http://www.xiami.com/widget/player-single?uid=0&sid=33227&mode=js'),(86,2,'http://www.xiami.com/widget/player-single?uid=0&sid=176827&mode=js'),(87,2,'http://www.xiami.com/widget/player-single?uid=0&sid=374834&mode=js'),(88,2,'http://www.xiami.com/widget/player-single?uid=0&sid=385951&mode=js'),(89,2,'http://www.xiami.com/widget/player-single?uid=0&sid=159913&mode=js'),(90,2,'http://www.xiami.com/widget/player-single?uid=0&sid=98713&mode=js'),(91,2,'http://www.xiami.com/widget/player-single?uid=0&sid=25310&mode=js'),(92,2,'http://www.xiami.com/widget/player-single?uid=0&sid=390654&mode=js'),(93,2,'http://www.xiami.com/widget/player-single?uid=0&sid=51954&mode=js'),(94,2,'http://www.xiami.com/widget/player-single?uid=0&sid=90605&mode=js'),(95,2,'http://www.xiami.com/widget/player-single?uid=0&sid=75042&mode=js'),(96,2,'http://www.xiami.com/widget/player-single?uid=0&sid=17286&mode=js'),(97,2,'http://www.xiami.com/widget/player-single?uid=0&sid=51601&mode=js'),(98,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2088537&mode=js'),(99,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2110751&mode=js'),(100,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2088564&mode=js'),(101,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769209746&mode=js'),(102,3,'http://www.xiami.com/widget/player-single?uid=0&sid=3455559&mode=js'),(103,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2091772&mode=js'),(104,3,'http://www.xiami.com/widget/player-single?uid=0&sid=3566577&mode=js'),(105,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2088563&mode=js'),(106,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769209759&mode=js'),(107,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2091781&mode=js'),(108,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2110744&mode=js'),(109,3,'http://www.xiami.com/widget/player-single?uid=0&sid=3621488&mode=js'),(110,3,'http://www.xiami.com/widget/player-single?uid=0&sid=369231&mode=js'),(111,3,'http://www.xiami.com/widget/player-single?uid=0&sid=35600&mode=js'),(112,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2091806&mode=js'),(113,3,'http://www.xiami.com/widget/player-single?uid=0&sid=3440144&mode=js'),(114,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2091807&mode=js'),(115,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1015&mode=js'),(116,3,'http://www.xiami.com/widget/player-single?uid=0&sid=3571408&mode=js'),(117,3,'http://www.xiami.com/widget/player-single?uid=0&sid=20516&mode=js'),(118,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1770083075&mode=js'),(119,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1770094787&mode=js'),(120,3,'http://www.xiami.com/widget/player-single?uid=0&sid=75577&mode=js'),(121,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769006428&mode=js'),(122,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2088504&mode=js'),(123,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2891117&mode=js'),(124,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2099153&mode=js'),(125,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1770455491&mode=js'),(126,3,'http://www.xiami.com/widget/player-single?uid=0&sid=20526&mode=js'),(127,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2723106&mode=js'),(128,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1354490&mode=js'),(129,3,'http://www.xiami.com/widget/player-single?uid=0&sid=3552084&mode=js'),(130,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769561364&mode=js'),(131,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2100635&mode=js'),(132,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2099540&mode=js'),(133,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2078050&mode=js'),(134,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769027083&mode=js'),(135,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2122592&mode=js'),(136,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769802668&mode=js'),(137,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769255141&mode=js'),(138,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2732116&mode=js'),(139,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1770161916&mode=js'),(140,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2080531&mode=js'),(141,3,'http://www.xiami.com/widget/player-single?uid=0&sid=82474&mode=js'),(142,3,'http://www.xiami.com/widget/player-single?uid=0&sid=369146&mode=js'),(143,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769482990&mode=js'),(144,3,'http://www.xiami.com/widget/player-single?uid=0&sid=2091766&mode=js'),(145,3,'http://www.xiami.com/widget/player-single?uid=0&sid=1769042192&mode=js'),(146,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2088861&mode=js'),(147,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2088856&mode=js'),(148,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769257408&mode=js'),(149,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769415354&mode=js'),(150,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1770518173&mode=js'),(151,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769823418&mode=js'),(152,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1768993653&mode=js'),(153,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1770770110&mode=js'),(154,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1770770105&mode=js'),(155,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1770297452&mode=js'),(156,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769995564&mode=js'),(157,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2075448&mode=js'),(158,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1770453324&mode=js'),(159,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2898512&mode=js'),(160,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769920660&mode=js'),(161,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1824686&mode=js'),(162,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2703945&mode=js'),(163,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1320787&mode=js'),(164,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3320694&mode=js'),(165,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769466180&mode=js'),(166,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1629559&mode=js'),(167,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2094751&mode=js'),(168,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1768918963&mode=js'),(169,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3007989&mode=js'),(170,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3564424&mode=js'),(171,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3588357&mode=js'),(172,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1768980114&mode=js'),(173,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1770183028&mode=js'),(174,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2079018&mode=js'),(175,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2095209&mode=js'),(176,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769012342&mode=js'),(177,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3259635&mode=js'),(178,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3514940&mode=js'),(179,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2093348&mode=js'),(180,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769680515&mode=js'),(181,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769887524&mode=js'),(182,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769078589&mode=js'),(183,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1770361013&mode=js'),(184,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3259639&mode=js'),(185,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3600735&mode=js'),(186,4,'http://www.xiami.com/widget/player-single?uid=0&sid=1769360258&mode=js'),(187,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2070207&mode=js'),(188,4,'http://www.xiami.com/widget/player-single?uid=0&sid=3139598&mode=js'),(189,4,'http://www.xiami.com/widget/player-single?uid=0&sid=2095195&mode=js');

#
# Structure for table "song_shopsentence"
#

DROP TABLE IF EXISTS `song_shopsentence`;
CREATE TABLE `song_shopsentence` (
  `SentenceId` int(11) NOT NULL AUTO_INCREMENT,
  `Sentence` varchar(500) NOT NULL DEFAULT '' COMMENT '商户寄语',
  PRIMARY KEY (`SentenceId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "song_shopsentence"
#

INSERT INTO `song_shopsentence` VALUES (1,'yes'),(2,'yes'),(3,'yes'),(4,'yes'),(5,'yes');

#
# Structure for table "song_styles"
#

DROP TABLE IF EXISTS `song_styles`;
CREATE TABLE `song_styles` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "song_styles"
#

INSERT INTO `song_styles` VALUES (1,'中国经典影视金曲'),(2,'华语歌坛80年代-90年代末经典老歌①'),(3,'轻音乐之乡·天籁净土（不断更新ing~）'),(4,'欧美经典情歌对唱');

#
# Structure for table "vote_activity"
#

DROP TABLE IF EXISTS `vote_activity`;
CREATE TABLE `vote_activity` (
  `VoteId` int(11) NOT NULL AUTO_INCREMENT COMMENT '投票活动ID',
  `VoteTitle` varchar(50) DEFAULT '' COMMENT '投票活动标题',
  `VoteSummary` varchar(500) DEFAULT '' COMMENT '投票活动描述',
  `VotePicture` varchar(100) DEFAULT NULL COMMENT '投票活动背景图片地址',
  `StartDate` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '投票活动起始时间',
  `EndDate` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '投票活动结束时间',
  `IsMultiChoice` int(11) DEFAULT '0' COMMENT '投票是否为多选类型，约束(0,1)',
  `MaxChoice` int(11) DEFAULT '0' COMMENT '（如果为多选）最大选择数',
  `EnableAdvice` int(11) DEFAULT '0' COMMENT '投票是否可留言，约束(0,1)',
  `VoteStatus` int(11) NOT NULL DEFAULT '0' COMMENT '投票活动状态，约束(0,1,2)',
  PRIMARY KEY (`VoteId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "vote_activity"
#

INSERT INTO `vote_activity` VALUES (1,'下面哪个新年亲子活动最有趣的？','为迎接新年，我们将举行一年一度的亲子活动，您对下面哪个活动比较感兴趣？','/userimages/1d2c7b431d874c78984edffa9f4cfab6','2014-11-25 00:00:00','2014-12-25 00:00:00',1,3,1,3);

#
# Structure for table "vote_advice"
#

DROP TABLE IF EXISTS `vote_advice`;
CREATE TABLE `vote_advice` (
  `AdviceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `VoteId` int(11) NOT NULL DEFAULT '0' COMMENT '投票活动ID',
  `OpenId` varchar(30) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `AdviceContent` varchar(500) NOT NULL DEFAULT '' COMMENT '留言内容',
  PRIMARY KEY (`AdviceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "vote_advice"
#


#
# Structure for table "vote_item"
#

DROP TABLE IF EXISTS `vote_item`;
CREATE TABLE `vote_item` (
  `ItemId` int(11) NOT NULL AUTO_INCREMENT COMMENT '投票选项ID',
  `VoteId` int(11) NOT NULL DEFAULT '0' COMMENT '投票活动ID',
  `ItemImage` varchar(100) DEFAULT NULL COMMENT '投票选项图片地址',
  `ItemDesc` varchar(100) DEFAULT NULL COMMENT '投票选项文本描述',
  PRIMARY KEY (`ItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "vote_item"
#

INSERT INTO `vote_item` VALUES (1,1,NULL,'歌唱比赛'),(2,1,NULL,'做游戏'),(3,1,NULL,'读故事');

#
# Structure for table "vote_item_choice"
#

DROP TABLE IF EXISTS `vote_item_choice`;
CREATE TABLE `vote_item_choice` (
  `ChoiceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户选择ID',
  `ItemId` int(11) NOT NULL DEFAULT '0' COMMENT '投票选项ID',
  `OpenId` varchar(30) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`ChoiceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "vote_item_choice"
#

INSERT INTO `vote_item_choice` VALUES (1,1,'sdfaffafffaf'),(2,2,'sdfaffafffaf');

#
# Structure for table "weather_city"
#

DROP TABLE IF EXISTS `weather_city`;
CREATE TABLE `weather_city` (
  `RecordId` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `UserOpenID` varchar(30) NOT NULL COMMENT '微信服务器返回的用户ID',
  `City` varchar(10) NOT NULL DEFAULT '' COMMENT '城市名',
  PRIMARY KEY (`RecordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "weather_city"
#

