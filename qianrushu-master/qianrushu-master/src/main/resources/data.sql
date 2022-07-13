CREATE TABLE `fish` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `user_name` varchar(128) DEFAULT NULL,
                        `water_degree` int(8) DEFAULT NULL,
                        `feed` int(8) DEFAULT NULL,
                        `fish_size` int(8) DEFAULT NULL,
                        `light` tinyint(4) DEFAULT '0',
                        `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;