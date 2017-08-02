--
-- Table structure for table `region`
--
CREATE TABLE `region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_REGION_COUNTRY_ID` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Update table `user`
--
ALTER TABLE user
  ADD `region_id` bigint(20) DEFAULT NULL,
  ADD CONSTRAINT `FK_USER_REGION_ID` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`);

--
-- Update table `city`
--
ALTER TABLE city
  ADD `region_id` bigint(20) DEFAULT NULL,
  ADD CONSTRAINT `FK_CITY_REGION_ID` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`),
  DROP FOREIGN KEY `FK_CITY_COUNTRY_ID`,
  DROP COLUMN `country_id`;