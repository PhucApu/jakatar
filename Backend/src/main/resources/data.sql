-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.40 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for bus-station
CREATE DATABASE IF NOT EXISTS `bus-station` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bus-station`;

-- Dumping structure for table bus-station.account
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `is_block` tinyint(1) NOT NULL DEFAULT '0',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `email` varchar(30) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `phone` varchar(15) NOT NULL,
  `role` enum('ROLE_USER','ROLE_ADMIN','ROLE_MANAGER','ROLE_STAFF') NOT NULL DEFAULT 'ROLE_USER',
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `IDXgex1lmaqpg0ir5g1f5eftyaa1` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.account: ~18 rows (approximately)
DELETE FROM `account`;
INSERT INTO `account` (`is_block`, `is_delete`, `email`, `full_name`, `password`, `phone`, `role`, `username`) VALUES
	(0, 0, 'user@gmail.com', 'Lê Vạn An', '$2y$10$Ge82QQtEWcexV69AJsSOq.j7AxmKmNoCI9IwusEyIIOeU3wHvaaBq', '0823072871', 'ROLE_USER', 'an'),
	(0, 0, 'user@gmail.com', 'Đỗ Tiến Đạt', '$2y$10$WQ4nZmE44PveXScIGt9ljeE/fFAKkUCDJRak0reMerttCLAO/xZ8a', '0823072871', 'ROLE_USER', 'dat'),
	(0, 0, 'manager@gmail.com', 'Nguyễn Trí Dũng', '$2y$10$rZ1fVKVZqA0e/N9mBDR6Qe2rpN1ATAhpCcFY0UZYM8Rh12RD.CW.K', '0987654321', 'ROLE_MANAGER', 'dung'),
	(0, 0, 'user10@gmail.com', 'Nguyễn Duy Anh', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823100011', 'ROLE_USER', 'duy'),
	(0, 0, 'user5@gmail.com', 'Lê Phương Giang', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823055566', 'ROLE_USER', 'giang'),
	(0, 0, 'user@gmail.com', 'Lê Tiến Hải', '$2y$10$luF7URSbx7DiJikHSOnEBuFkM1mT4/ANzecbvp1wWW0.IgIlkMZuK', '0823072871', 'ROLE_USER', 'hai'),
	(0, 0, 'user2@gmail.com', 'Nguyễn Văn Hiếu', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823022233', 'ROLE_USER', 'hieu'),
	(0, 0, 'user9@gmail.com', 'Phạm Văn Khánh', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823099900', 'ROLE_USER', 'khanh'),
	(0, 0, 'user1@gmail.com', 'Trần Văn Linh', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823011122', 'ROLE_USER', 'linh'),
	(0, 0, 'user3@gmail.com', 'Đặng Quốc Minh', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823033344', 'ROLE_USER', 'minh'),
	(0, 0, 'user8@gmail.com', 'Trần Thị Nhung', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823088899', 'ROLE_USER', 'nhung'),
	(0, 0, 'user7@gmail.com', 'Ngô Thành Phong', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823077788', 'ROLE_USER', 'phong'),
	(0, 0, 'admin@gmail.com', 'Trương Công Phúc', '$2a$10$h1IlhlhelQn9vVtQUijs9eONe037IpghOwFawhGZHsHDBIu8/Yzuq', '0823072871', 'ROLE_ADMIN', 'phuc'),
	(0, 0, 'user@gmail.com', 'Cao Bảo Quỳnh', '$2y$10$mPchIPcw2PP6XW6r4KoQK./kmkr1zaBiCRXpQdGSIM9VRVRS5l2R6', '0823072871', 'ROLE_STAFF', 'quynh'),
	(0, 0, 'user4@gmail.com', 'Phạm Thị Thảo', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823044455', 'ROLE_USER', 'thao'),
	(0, 0, 'admin@gmail.com', 'Trần Khang Thịnh', '$2a$10$h1IlhlhelQn9vVtQUijs9eONe037IpghOwFawhGZHsHDBIu8/Yzuq', '0987654321', 'ROLE_ADMIN', 'thinh'),
	(0, 0, 'user@gmail.com', 'Nguyễn Quốc Toàn', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823072871', 'ROLE_USER', 'toan'),
	(0, 0, 'user6@gmail.com', 'Hoàng Văn Việt', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', '0823066677', 'ROLE_USER', 'viet');

-- Dumping structure for table bus-station.bus
DROP TABLE IF EXISTS `bus`;
CREATE TABLE IF NOT EXISTS `bus` (
  `capacity` int NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `bus_id` bigint NOT NULL AUTO_INCREMENT,
  `routes_id` bigint DEFAULT NULL,
  `brand` varchar(30) NOT NULL,
  `bus_number` varchar(30) NOT NULL,
  PRIMARY KEY (`bus_id`),
  KEY `IDX978d2ck6exb6snrdgrqe51exd` (`bus_id`),
  KEY `FKkcwedsnwh7y5ei0xmwjr5687a` (`routes_id`),
  CONSTRAINT `FKkcwedsnwh7y5ei0xmwjr5687a` FOREIGN KEY (`routes_id`) REFERENCES `bus_routes` (`routes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.bus: ~20 rows (approximately)
DELETE FROM `bus`;
INSERT INTO `bus` (`capacity`, `is_delete`, `bus_id`, `routes_id`, `brand`, `bus_number`) VALUES
	(45, 0, 1, 1, 'Toyota', '79A-12345'),
	(50, 0, 2, 2, 'Hyundai', '30B-67890'),
	(30, 0, 3, 3, 'Ford', '92C-54321'),
	(40, 0, 4, 4, 'Mercedes', '29D-98765'),
	(55, 0, 5, 5, 'Isuzu', '51E-45678'),
	(60, 0, 6, 6, 'Hino', '60F-11223'),
	(25, 0, 7, 7, 'Kia', '83G-44556'),
	(50, 0, 8, 8, 'Daewoo', '84H-77889'),
	(35, 0, 9, 9, 'Mitsubishi', '99K-66778'),
	(40, 0, 10, 10, 'Volvo', '45L-12367'),
	(40, 0, 11, 11, 'Toyota', '70M-11223'),
	(50, 0, 12, 12, 'Hyundai', '72N-44556'),
	(35, 0, 13, 13, 'Ford', '74P-77889'),
	(45, 0, 14, 14, 'Mercedes', '75Q-66778'),
	(55, 0, 15, 18, 'Isuzu', '77R-55667'),
	(60, 0, 16, 19, 'Hino', '78S-33445'),
	(30, 0, 17, 20, 'Kia', '79T-22334'),
	(50, 0, 18, 7, 'Daewoo', '81U-88990'),
	(40, 0, 19, 8, 'Mitsubishi', '82V-99887'),
	(60, 0, 20, 9, 'Volvo', '83W-77665');

-- Dumping structure for table bus-station.bus_employee
DROP TABLE IF EXISTS `bus_employee`;
CREATE TABLE IF NOT EXISTS `bus_employee` (
  `bus_id` bigint NOT NULL,
  `driver_id` bigint NOT NULL,
  KEY `FK98xaxi0twjvj2m9uotvsebh68` (`bus_id`),
  KEY `FK6b78g8fwksqn61yl9c8xqekw7` (`driver_id`),
  CONSTRAINT `FK6b78g8fwksqn61yl9c8xqekw7` FOREIGN KEY (`driver_id`) REFERENCES `employee` (`driver_id`),
  CONSTRAINT `FK98xaxi0twjvj2m9uotvsebh68` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.bus_employee: ~20 rows (approximately)
DELETE FROM `bus_employee`;
INSERT INTO `bus_employee` (`bus_id`, `driver_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6),
	(7, 7),
	(8, 8),
	(9, 9),
	(10, 10),
	(11, 11),
	(12, 1),
	(13, 2),
	(14, 3),
	(15, 4),
	(16, 5),
	(17, 6),
	(18, 7),
	(19, 8),
	(20, 9);

-- Dumping structure for table bus-station.bus_routes
DROP TABLE IF EXISTS `bus_routes`;
CREATE TABLE IF NOT EXISTS `bus_routes` (
  `distance_location` float DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `price` float DEFAULT NULL,
  `arival_time` datetime DEFAULT NULL,
  `departure_time` datetime DEFAULT NULL,
  `routes_id` bigint NOT NULL AUTO_INCREMENT,
  `departure_location` varchar(50) DEFAULT NULL,
  `destination_location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`routes_id`),
  KEY `IDXiu4li9dfdqttjaddw8lreokww` (`routes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.bus_routes: ~17 rows (approximately)
DELETE FROM `bus_routes`;
INSERT INTO `bus_routes` (`distance_location`, `is_delete`, `price`, `arival_time`, `departure_time`, `routes_id`, `departure_location`, `destination_location`) VALUES
	(60, 0, 50000, '2024-11-18 08:00:00', '2024-11-18 06:00:00', 1, 'Cần Thơ', 'Long Xuyên'),
	(120, 0, 100000, '2024-11-18 10:30:00', '2024-11-18 08:00:00', 2, 'Cần Thơ', 'Châu Đốc'),
	(90, 0, 80000, '2024-11-18 12:00:00', '2024-11-18 09:30:00', 3, 'Cần Thơ', 'Rạch Giá'),
	(180, 0, 150000, '2024-11-18 15:00:00', '2024-11-18 12:00:00', 4, 'Cần Thơ', 'Cà Mau'),
	(150, 0, 120000, '2024-11-18 17:00:00', '2024-11-18 14:00:00', 5, 'Cần Thơ', 'Bạc Liêu'),
	(60, 0, 50000, '2024-11-18 20:00:00', '2024-11-18 17:30:00', 6, 'Cần Thơ', 'Sóc Trăng'),
	(50, 0, 40000, '2024-11-18 09:00:00', '2024-11-18 07:00:00', 7, 'Long Xuyên', 'Châu Đốc'),
	(110, 0, 90000, '2024-11-18 11:30:00', '2024-11-18 09:00:00', 8, 'Long Xuyên', 'Rạch Giá'),
	(200, 0, 170000, '2024-11-18 14:00:00', '2024-11-18 11:30:00', 9, 'Long Xuyên', 'Cà Mau'),
	(170, 0, 140000, '2024-11-18 16:00:00', '2024-11-18 13:00:00', 10, 'Long Xuyên', 'Bạc Liêu'),
	(135, 0, 120000, '2024-11-18 09:00:00', '2024-11-18 06:00:00', 11, 'Hồ Chí Minh', 'Vĩnh Long'),
	(45, 0, 50000, '2024-11-18 10:30:00', '2024-11-18 07:30:00', 12, 'Hồ Chí Minh', 'Long An'),
	(70, 0, 70000, '2024-11-18 11:00:00', '2024-11-18 08:00:00', 13, 'Hồ Chí Minh', 'Tiền Giang'),
	(170, 0, 150000, '2024-11-18 15:00:00', '2024-11-18 12:30:00', 14, 'Hồ Chí Minh', 'Cần Thơ'),
	(35, 0, 40000, '2024-11-18 08:00:00', '2024-11-18 06:00:00', 18, 'Long An', 'Tiền Giang'),
	(45, 0, 50000, '2024-11-18 10:00:00', '2024-11-18 07:30:00', 19, 'Long An', 'Hồ Chí Minh'),
	(90, 0, 80000, '2024-11-18 12:30:00', '2024-11-18 10:00:00', 20, 'Long An', 'Vĩnh Long');

-- Dumping structure for table bus-station.discount
DROP TABLE IF EXISTS `discount`;
CREATE TABLE IF NOT EXISTS `discount` (
  `amount` int NOT NULL,
  `discount_percentage` float DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `discount_id` bigint NOT NULL AUTO_INCREMENT,
  `valid_from` datetime NOT NULL,
  `valid_until` datetime NOT NULL,
  PRIMARY KEY (`discount_id`),
  KEY `IDX4ckbbummjrfpo6hkr9ymi116v` (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.discount: ~12 rows (approximately)
DELETE FROM `discount`;
INSERT INTO `discount` (`amount`, `discount_percentage`, `is_delete`, `discount_id`, `valid_from`, `valid_until`) VALUES
	(100, 10, 0, 1, '2024-01-01 00:00:00', '2024-12-31 23:59:59'),
	(50, 20, 0, 2, '2024-06-01 00:00:00', '2024-09-30 23:59:59'),
	(75, 15, 0, 3, '2024-02-01 00:00:00', '2024-02-28 23:59:59'),
	(100, 25, 0, 4, '2024-03-01 00:00:00', '2024-03-31 23:59:59'),
	(30, 5, 0, 5, '2024-04-01 00:00:00', '2024-04-15 23:59:59'),
	(150, 30, 0, 6, '2024-07-01 00:00:00', '2024-07-31 23:59:59'),
	(200, 40, 0, 7, '2024-08-01 00:00:00', '2024-08-31 23:59:59'),
	(50, 10, 0, 8, '2024-09-15 00:00:00', '2024-10-15 23:59:59'),
	(250, 50, 0, 9, '2024-11-01 00:00:00', '2024-11-30 23:59:59'),
	(300, 60, 0, 10, '2024-12-01 00:00:00', '2024-12-31 23:59:59'),
	(125, 35, 0, 11, '2024-01-15 00:00:00', '2024-03-15 23:59:59'),
	(100, 20, 0, 12, '2024-05-01 00:00:00', '2024-06-30 23:59:59');

-- Dumping structure for table bus-station.employee
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `is_driver` tinyint(1) NOT NULL DEFAULT '1',
  `driver_id` bigint NOT NULL AUTO_INCREMENT,
  `driver_name` varchar(50) NOT NULL,
  `license_number` varchar(50) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  PRIMARY KEY (`driver_id`),
  KEY `IDXo9w3xhep3814gj7gqk2utw24s` (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.employee: ~11 rows (approximately)
DELETE FROM `employee`;
INSERT INTO `employee` (`is_delete`, `is_driver`, `driver_id`, `driver_name`, `license_number`, `phone_number`) VALUES
	(0, 1, 1, 'Nguyễn Văn A', '080203005137', '0901234567'),
	(0, 1, 2, 'Trần Văn B', '090203001234', '0901234567'),
	(0, 1, 3, 'Phạm Thị C', '080104002345', '0934567890'),
	(0, 1, 4, 'Lê Quốc D', '070305003456', '0976543210'),
	(0, 1, 5, 'Ngô Thị E', '060406004567', '0812345678'),
	(0, 1, 6, 'Hoàng Văn F', '050507005678', '0834567891'),
	(0, 1, 7, 'Nguyễn Thị G', '040608006789', '0856789012'),
	(0, 1, 8, 'Đặng Quốc H', '030709007890', '0878901234'),
	(0, 1, 9, 'Phan Thị I', '020801008901', '0890123456'),
	(0, 1, 10, 'Đỗ Văn J', '010902009012', '0709876543'),
	(0, 1, 11, 'Lý Thị K', '110203000123', '0901112223');

-- Dumping structure for table bus-station.feedback
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE IF NOT EXISTS `feedback` (
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `rating` int DEFAULT NULL,
  `date_comment` datetime NOT NULL,
  `feedback_id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint NOT NULL,
  `content` text,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `IDXa71orebce7dq4ueupg6usf2x7` (`feedback_id`),
  KEY `FK57t64pnsxtsop1t8a2i4xafh0` (`username`),
  KEY `FK8fth5acovhce06e6atsujlb6e` (`ticket_id`),
  CONSTRAINT `FK57t64pnsxtsop1t8a2i4xafh0` FOREIGN KEY (`username`) REFERENCES `account` (`username`),
  CONSTRAINT `FK8fth5acovhce06e6atsujlb6e` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.feedback: ~0 rows (approximately)
DELETE FROM `feedback`;

-- Dumping structure for table bus-station.payment
DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `discount_amount` float NOT NULL,
  `final_amount` float NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `original_amount` float NOT NULL,
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `payment_time` datetime NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `IDXh2cx18159w1evkp50cj9p8s8y` (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.payment: ~10 rows (approximately)
DELETE FROM `payment`;
INSERT INTO `payment` (`discount_amount`, `final_amount`, `is_delete`, `original_amount`, `payment_id`, `payment_time`, `payment_method`, `status`) VALUES
	(50000, 450000, 0, 500000, 1, '2024-11-15 10:00:00', 'VNPay', 'success'),
	(30000, 270000, 0, 300000, 2, '2024-11-16 12:30:00', 'VNPay', 'success'),
	(20000, 380000, 0, 400000, 3, '2024-11-17 09:45:00', 'VNPay', 'pending'),
	(60000, 540000, 0, 600000, 4, '2024-11-18 14:00:00', 'VNPay', 'success'),
	(70000, 630000, 0, 700000, 5, '2024-11-19 08:15:00', 'VNPay', 'failure'),
	(0, 800000, 0, 800000, 6, '2024-11-20 16:45:00', 'VNPay', 'success'),
	(50000, 500000, 0, 550000, 7, '2024-11-21 11:30:00', 'VNPay', 'pending'),
	(0, 250000, 0, 250000, 8, '2024-11-22 15:10:00', 'VNPay', 'success'),
	(45000, 405000, 0, 450000, 9, '2024-11-23 17:20:00', 'VNPay', 'failure'),
	(0, 600000, 0, 600000, 10, '2024-11-24 10:50:00', 'VNPay', 'success');

-- Dumping structure for table bus-station.penalty_ticket
DROP TABLE IF EXISTS `penalty_ticket`;
CREATE TABLE IF NOT EXISTS `penalty_ticket` (
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `price` float NOT NULL,
  `responsibility` tinyint(1) NOT NULL DEFAULT '0',
  `bus_id` bigint NOT NULL,
  `driver_id` bigint DEFAULT NULL,
  `penalty_day` datetime NOT NULL,
  `penalty_ticket_id` bigint NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  PRIMARY KEY (`penalty_ticket_id`),
  KEY `IDXni40svgxd1j3o0l5v2aev18be` (`penalty_ticket_id`),
  KEY `FKp2tg22ojf2h9ic80anhycedqe` (`bus_id`),
  KEY `FK6dya5x7nx1y8lpjykencb290p` (`driver_id`),
  CONSTRAINT `FK6dya5x7nx1y8lpjykencb290p` FOREIGN KEY (`driver_id`) REFERENCES `employee` (`driver_id`),
  CONSTRAINT `FKp2tg22ojf2h9ic80anhycedqe` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.penalty_ticket: ~0 rows (approximately)
DELETE FROM `penalty_ticket`;

-- Dumping structure for table bus-station.ticket
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `price` float NOT NULL,
  `bus_id` bigint NOT NULL,
  `departure_date` datetime NOT NULL,
  `discount_id` bigint DEFAULT NULL,
  `payment_id` bigint NOT NULL,
  `routes_id` bigint NOT NULL,
  `ticket_id` bigint NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) DEFAULT NULL,
  `seat_number` varchar(10) NOT NULL,
  `status` varchar(20) NOT NULL,
  `username_id` varchar(20) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `IDXkq50is04s07x2w1j3rtfgfysc` (`ticket_id`),
  KEY `FK8d1j7m72xx5tljsft6rmlk9g6` (`username_id`),
  KEY `FKca1w45srvscn21yd8m1btlatx` (`bus_id`),
  KEY `FKcn9l93n8sgp75wg4muf3e5oll` (`discount_id`),
  KEY `FKksvt4tgnlwi1n5ckvd8lcgws5` (`payment_id`),
  CONSTRAINT `FK8d1j7m72xx5tljsft6rmlk9g6` FOREIGN KEY (`username_id`) REFERENCES `account` (`username`),
  CONSTRAINT `FKca1w45srvscn21yd8m1btlatx` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`),
  CONSTRAINT `FKcn9l93n8sgp75wg4muf3e5oll` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`discount_id`),
  CONSTRAINT `FKksvt4tgnlwi1n5ckvd8lcgws5` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table bus-station.ticket: ~10 rows (approximately)
DELETE FROM `ticket`;
INSERT INTO `ticket` (`is_delete`, `price`, `bus_id`, `departure_date`, `discount_id`, `payment_id`, `routes_id`, `ticket_id`, `phone`, `seat_number`, `status`, `username_id`) VALUES
	(0, 450000, 1, '2024-11-20 00:00:00', 1, 1, 1, 21, '0901234567', 'A01', 'success', 'toan'),
	(0, 270000, 2, '2024-11-21 00:00:00', 2, 2, 2, 22, '0912345678', 'A02', 'success', 'an'),
	(1, 380000, 3, '2024-11-22 00:00:00', NULL, 3, 3, 23, '0923456789', 'A03', 'failure', 'hai'),
	(0, 540000, 4, '2024-11-23 00:00:00', 1, 4, 4, 24, '0901234567', 'A04', 'success', 'linh'),
	(1, 630000, 5, '2024-11-24 00:00:00', NULL, 5, 5, 25, '0912345678', 'A05', 'failure', 'hieu'),
	(0, 800000, 6, '2024-11-25 00:00:00', NULL, 6, 6, 26, '0923456789', 'A06', 'success', 'minh'),
	(0, 500000, 7, '2024-11-26 00:00:00', NULL, 7, 7, 27, '0901234567', 'A07', 'pending', 'thao'),
	(1, 250000, 8, '2024-11-27 00:00:00', NULL, 8, 8, 28, '0912345678', 'A08', 'failure', 'giang'),
	(0, 405000, 9, '2024-11-28 00:00:00', 2, 9, 9, 29, '0923456789', 'A09', 'success', 'viet'),
	(0, 600000, 10, '2024-11-29 00:00:00', NULL, 1, 10, 30, '0901234567', 'A10', 'pending', 'phong');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
