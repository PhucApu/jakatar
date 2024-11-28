-- Đây là file được dùng để test database, phải chạy bằng tay 

-- Mật khẩu admin là 123456Admin@
-- Mật khẩu manager là 123456Manager@
-- Mật khâu staff la 123456Staff@

-- user
-- Mật khâu user la 123456User@

-- Thêm dữ liệu vào account
INSERT INTO account (username, password, email, full_name, phone, ROLE, is_delete, is_block)
VALUES
('thinh','$2a$10$h1IlhlhelQn9vVtQUijs9eONe037IpghOwFawhGZHsHDBIu8/Yzuq', 'admin@gmail.com', 'Trần Khang Thịnh', '0987654321', 'ROLE_ADMIN', 0, 0),
('phuc','$2a$10$h1IlhlhelQn9vVtQUijs9eONe037IpghOwFawhGZHsHDBIu8/Yzuq', 'admin@gmail.com', 'Trương Công Phúc', '0823072871', 'ROLE_ADMIN', 0, 0),
('dung','$2y$10$rZ1fVKVZqA0e/N9mBDR6Qe2rpN1ATAhpCcFY0UZYM8Rh12RD.CW.K', 'manager@gmail.com', 'Nguyễn Trí Dũng', '0987654321', 'ROLE_MANAGER', 0, 0),
('quynh','$2y$10$mPchIPcw2PP6XW6r4KoQK./kmkr1zaBiCRXpQdGSIM9VRVRS5l2R6', 'user@gmail.com', 'Cao Bảo Quỳnh', '0823072871', 'ROLE_STAFF', 0, 0),
('toan','$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user@gmail.com', 'Nguyễn Quốc Toàn', '0823072871', 'ROLE_USER', 0, 0),
('an','$2y$10$Ge82QQtEWcexV69AJsSOq.j7AxmKmNoCI9IwusEyIIOeU3wHvaaBq', 'user@gmail.com', 'Lê Vạn An', '0823072871', 'ROLE_USER', 0, 0),
('hai','$2y$10$luF7URSbx7DiJikHSOnEBuFkM1mT4/ANzecbvp1wWW0.IgIlkMZuK', 'user@gmail.com', 'Lê Tiến Hải', '0823072871', 'ROLE_USER', 0, 0),
('linh', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user1@gmail.com', 'Trần Văn Linh', '0823011122', 'ROLE_USER', 0, 0),
('hieu', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user2@gmail.com', 'Nguyễn Văn Hiếu', '0823022233', 'ROLE_USER', 0, 0),
('minh', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user3@gmail.com', 'Đặng Quốc Minh', '0823033344', 'ROLE_USER', 0, 0),
('thao', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user4@gmail.com', 'Phạm Thị Thảo', '0823044455', 'ROLE_USER', 0, 0),
('giang', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user5@gmail.com', 'Lê Phương Giang', '0823055566', 'ROLE_USER', 0, 0),
('viet', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user6@gmail.com', 'Hoàng Văn Việt', '0823066677', 'ROLE_USER', 0, 0),
('phong', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user7@gmail.com', 'Ngô Thành Phong', '0823077788', 'ROLE_USER', 0, 0),
('nhung', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user8@gmail.com', 'Trần Thị Nhung', '0823088899', 'ROLE_USER', 0, 0),
('khanh', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user9@gmail.com', 'Phạm Văn Khánh', '0823099900', 'ROLE_USER', 0, 0),
('duy', '$2y$10$YsmlgKlBB76y6g9QvF004.Kloh6CEsmJntC428k5R3eFqauMUdcp2', 'user10@gmail.com', 'Nguyễn Duy Anh', '0823100011', 'ROLE_USER', 0, 0),
('dat','$2y$10$WQ4nZmE44PveXScIGt9ljeE/fFAKkUCDJRak0reMerttCLAO/xZ8a', 'user@gmail.com', 'Đỗ Tiến Đạt', '0823072871', 'ROLE_USER', 0, 0);


-- Thêm dữ liệu vào discount
INSERT INTO discount (discount_percentage, valid_from, valid_until, amount, is_delete) VALUES
(10.0, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 100, 0),
(20.0, '2024-06-01 00:00:00', '2024-09-30 23:59:59', 50, 0),
(15.0, '2024-02-01 00:00:00', '2024-02-28 23:59:59', 75, 0),
(25.0, '2024-03-01 00:00:00', '2024-03-31 23:59:59', 100, 0),
(5.0,  '2024-04-01 00:00:00', '2024-04-15 23:59:59', 30, 0),
(30.0, '2024-07-01 00:00:00', '2024-07-31 23:59:59', 150, 0),
(40.0, '2024-08-01 00:00:00', '2024-08-31 23:59:59', 200, 0),
(10.0, '2024-09-15 00:00:00', '2024-10-15 23:59:59', 50, 0),
(50.0, '2024-11-01 00:00:00', '2024-11-30 23:59:59', 250, 0),
(60.0, '2024-12-01 00:00:00', '2024-12-31 23:59:59', 300, 0),
(35.0, '2024-01-15 00:00:00', '2024-03-15 23:59:59', 125, 0),
(20.0, '2024-05-01 00:00:00', '2024-06-30 23:59:59', 100, 0);

-- bus_routes
INSERT INTO bus_routes (routes_id, arival_time, departure_time, departure_location, destination_location, distance_location, price, is_delete) VALUES
(1, '2024-11-18 08:00:00', '2024-11-18 06:00:00', 'Cần Thơ', 'Long Xuyên', 60, 50000, 0),
(2, '2024-11-18 10:30:00', '2024-11-18 08:00:00', 'Cần Thơ', 'Châu Đốc', 120, 100000, 0),
(3, '2024-11-18 12:00:00', '2024-11-18 09:30:00', 'Cần Thơ', 'Rạch Giá', 90, 80000, 0),
(4, '2024-11-18 15:00:00', '2024-11-18 12:00:00', 'Cần Thơ', 'Cà Mau', 180, 150000, 0),
(5, '2024-11-18 17:00:00', '2024-11-18 14:00:00', 'Cần Thơ', 'Bạc Liêu', 150, 120000, 0),
(6, '2024-11-18 20:00:00', '2024-11-18 17:30:00', 'Cần Thơ', 'Sóc Trăng', 60, 50000, 0),
(7, '2024-11-18 09:00:00', '2024-11-18 07:00:00', 'Long Xuyên', 'Châu Đốc', 50, 40000, 0),
(8, '2024-11-18 11:30:00', '2024-11-18 09:00:00', 'Long Xuyên', 'Rạch Giá', 110, 90000, 0),
(9, '2024-11-18 14:00:00', '2024-11-18 11:30:00', 'Long Xuyên', 'Cà Mau', 200, 170000, 0),
(10, '2024-11-18 16:00:00', '2024-11-18 13:00:00', 'Long Xuyên', 'Bạc Liêu', 170, 140000, 0),
(11, '2024-11-18 09:00:00', '2024-11-18 06:00:00', 'Hồ Chí Minh', 'Vĩnh Long', 135, 120000, 0),
(12, '2024-11-18 10:30:00', '2024-11-18 07:30:00', 'Hồ Chí Minh', 'Long An', 45, 50000, 0),
(13, '2024-11-18 11:00:00', '2024-11-18 08:00:00', 'Hồ Chí Minh', 'Tiền Giang', 70, 70000, 0),
(14, '2024-11-18 15:00:00', '2024-11-18 12:30:00', 'Hồ Chí Minh', 'Cần Thơ', 170, 150000, 0),
(18, '2024-11-18 08:00:00', '2024-11-18 06:00:00', 'Long An', 'Tiền Giang', 35, 40000, 0),
(19, '2024-11-18 10:00:00', '2024-11-18 07:30:00', 'Long An', 'Hồ Chí Minh', 45, 50000, 0),
(20, '2024-11-18 12:30:00', '2024-11-18 10:00:00', 'Long An', 'Vĩnh Long', 90, 80000, 0);


-- employee
INSERT INTO employee (driver_id, driver_name, license_number, phone_number, is_driver, is_delete) VALUES
(1,'Nguyễn Văn A', '080203005137', '0901234567', 1, 0),
(2,'Trần Văn B', '090203001234', '0901234567', 1, 0),
(3, 'Phạm Thị C', '080104002345', '0934567890', 1, 0),
(4, 'Lê Quốc D', '070305003456', '0976543210', 1, 0),
(5, 'Ngô Thị E', '060406004567', '0812345678', 1, 0),
(6, 'Hoàng Văn F', '050507005678', '0834567891', 1, 0),
(7, 'Nguyễn Thị G', '040608006789', '0856789012', 1, 0),
(8, 'Đặng Quốc H', '030709007890', '0878901234', 1, 0),
(9, 'Phan Thị I', '020801008901', '0890123456', 1, 0),
(10, 'Đỗ Văn J', '010902009012', '0709876543', 1, 0),
(11, 'Lý Thị K', '110203000123', '0901112223', 1, 0);


-- bus
INSERT INTO bus (bus_id, brand, bus_number, capacity, routes_id, is_delete) 
VALUES
(1, 'Toyota', '79A-12345', 45, 1, 0),
(2, 'Hyundai', '30B-67890', 50, 2, 0),
(3, 'Ford', '92C-54321', 30, 3, 0),
(4, 'Mercedes', '29D-98765', 40, 4, 0),
(5, 'Isuzu', '51E-45678', 55, 5, 0),
(6, 'Hino', '60F-11223', 60, 6, 0),
(7, 'Kia', '83G-44556', 25, 7, 0),
(8, 'Daewoo', '84H-77889', 50, 8, 0),
(9, 'Mitsubishi', '99K-66778', 35, 9, 0),
(10, 'Volvo', '45L-12367', 40, 10, 0),
(11, 'Toyota', '70M-11223', 40, 11, 0),
(12, 'Hyundai', '72N-44556', 50, 12, 0),
(13, 'Ford', '74P-77889', 35, 13, 0),
(14, 'Mercedes', '75Q-66778', 45, 14, 0),
(15, 'Isuzu', '77R-55667', 55, 18, 0),
(16, 'Hino', '78S-33445', 60, 19, 0),
(17, 'Kia', '79T-22334', 30, 20, 0),
(18, 'Daewoo', '81U-88990', 50, 7, 0),
(19, 'Mitsubishi', '82V-99887', 40, 8, 0),
(20, 'Volvo', '83W-77665', 60, 9, 0);


-- bus-employee
INSERT INTO bus_employee (bus_id, driver_id) 
VALUES
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
(11, 11), -- Thêm dữ liệu mới
(12, 1),
(13, 2),
(14, 3),
(15, 4),
(16, 5),
(17, 6),
(18, 7),
(19, 8),
(20, 9);


--penalty ticket
INSERT INTO penalty_ticket (penalty_ticket_id,bus_id,driver_id,penalty_day,description,responsibility,price,is_delete) VALUES
(1,1,1,'2024-01-05 14:30:00', 'Vi phạm quá tốc độ', TRUE,200.0, FALSE),
(2,1,2,'2024-01-05 14:30:00', 'Vi phạm quá tốc độ', TRUE,200.0, FALSE);


-- payment
INSERT INTO payment (payment_id, payment_time, original_amount, discount_amount, final_amount, payment_method, status, is_delete) 
VALUES
(1,'2024-11-15 10:00:00', 500000, 50000, 450000, 'VNPay', 'success', 0),
(2,'2024-11-16 12:30:00', 300000, 30000, 270000, 'VNPay', 'success', 0),
(3,'2024-11-17 09:45:00', 400000, 20000, 380000, 'VNPay', 'pending', 0),
(4,'2024-11-18 14:00:00', 600000, 60000, 540000, 'VNPay', 'success', 0),
(5,'2024-11-19 08:15:00', 700000, 70000, 630000, 'VNPay', 'failure', 0),
(6,'2024-11-20 16:45:00', 800000, 0, 800000, 'VNPay', 'success', 0),
(7,'2024-11-21 11:30:00', 550000, 50000, 500000, 'VNPay', 'pending', 0),
(8,'2024-11-22 15:10:00', 250000, 0, 250000, 'VNPay', 'success', 0),
(9,'2024-11-23 17:20:00', 450000, 45000, 405000, 'VNPay', 'failure', 0),
(10,'2024-11-24 10:50:00', 600000, 0, 600000, 'VNPay', 'success', 0);



-- ticket
INSERT INTO ticket (username_id, bus_id, routes_id, payment_id, discount_id, seat_number, departure_date, price, phone, status, is_delete) 
VALUES
('toan', 1, 1, 1, 1, 'A01', '2024-11-20', 450000, '0901234567', 'success', 0),
('an', 2, 2, 2, 2, 'A02', '2024-11-21', 270000, '0912345678', 'success', 0),
('hai', 3, 3, 3, NULL, 'A03', '2024-11-22', 380000, '0923456789', 'failure', 1),
('linh', 4, 4, 4, 1, 'A04', '2024-11-23', 540000, '0901234567', 'success', 0),
('hieu', 5, 5, 5, NULL, 'A05', '2024-11-24', 630000, '0912345678', 'failure', 1),
('minh', 6, 6, 6, NULL, 'A06', '2024-11-25', 800000, '0923456789', 'success', 0),
('thao', 7, 7, 7, NULL, 'A07', '2024-11-26', 500000, '0901234567', 'pending', 0),
('giang', 8, 8, 8, NULL, 'A08', '2024-11-27', 250000, '0912345678', 'failure', 1),
('viet', 9, 9, 9, 2, 'A09', '2024-11-28', 405000, '0923456789', 'success', 0),
('phong', 10, 10, 1, NULL, 'A10', '2024-11-29', 600000, '0901234567', 'pending', 0);

-- feedback
INSERT INTO feedback (feedback_id, username, content, ticket_id, date_comment, rating, is_delete) VALUES
(1, 'toan', 'Dịch vụ rất tốt, tài xế thân thiện.', 1, '2024-11-10', 5, 0),
(2, 'an', 'Xe buýt sạch sẽ và đúng giờ.', 2, '2024-11-11', 4, 0),
(3, 'hai', 'Tài xế lái xe an toàn nhưng hơi chậm.', 3, '2024-11-12', 3, 0),
(4, 'linh', 'Hành trình không như mong đợi, xe trễ giờ.', 4, '2024-11-13', 2, 0),
(5, 'linh', 'Ghế ngồi không thoải mái, cần cải thiện.', 5, '2024-11-14', 3, 0),
(6, 'hieu', 'Tài xế không lịch sự, không hài lòng.', 6, '2024-11-15', 1, 1),
(7, 'minh', 'Giá vé hợp lý, dịch vụ ổn.', 7, '2024-11-16', 4, 0),
(8, 'thao', 'Điểm đến không đúng giờ, xe cũ.', 8, '2024-11-17', 2, 0),
(9, 'giang', 'Xe mới, thoải mái, tài xế nhiệt tình.', 9, '2024-11-18', 5, 0),
(10, 'viet', 'Quá đông, không đủ ghế ngồi.', 10, '2024-11-19', 1, 0);















