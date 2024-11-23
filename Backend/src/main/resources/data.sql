

-- Đây là file được dùng để test database, phải chạy bằng tay 



-- Thêm dữ liệu vào account
INSERT INTO account (username, password, email,full_name, phone, ROLE, is_delete, is_block) 
VALUES 
('customer1', SHA2('customer123', 256), 'customer1@example.com', 'Nguyễn Trí Dũng', '0987654321', 'ROLE_USER', 0, 0),
('customer2', SHA2('password456', 256), 'customer2@example.com','Đỗ Tiến Đạt', '1122334455', 'ROLE_USER', 0, 0),
('admin2', SHA2('adminpassword', 256), 'admin2@example.com', 'Trương Công Phúc', '2233445566', 'ROLE_ADMIN', 0, 1),
('customer3', SHA2('mypassword', 256), 'customer3@example.com', 'Lê Vạn An', '3344556677', 'ROLE_USER', 1, 0),
('john_doe', SHA2('mypassword', 256), 'john.doe@example.com', 'Nguyễn Quốc Toàn', '0123456789', 'ROLE_ADMIN', 0, 0),
('user1', SHA2('123456', 256), 'john.doe@example.com', 'Trần Khang Thịnh', '0123456789', 'ROLE_USER', 0, 0);


-- Thêm dữ liệu vào discount
INSERT INTO discount (discount_percentage, valid_from, valid_until, amount, is_delete) VALUES
(10.0, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 100, 0),
(20.0, '2024-06-01 00:00:00', '2024-09-30 23:59:59', 50, 0);


-- employee
INSERT INTO employee (driver_id, driver_name, license_number, phone_number, is_driver, is_delete) VALUES
(1, 'Nguyễn Văn A', 'B1234567', '0901234567', 1, 0),
(2, 'Trần Thị B', 'C7654321', '0912345678', 1, 0),
(3, 'Lê Văn C', 'D4567890', '0923456789', 1, 0),
(4, 'Phạm Minh D', 'E9876543', '0934567890', 1, 1),
(5, 'Hoàng Thị E', 'F6543210', '0945678901', 0, 0),
(6, 'Võ Văn F', 'G3456789', '0956789012', 0, 1),
(7, 'Đặng Thị G', 'H2345678', '0967890123', 1, 0),
(8, 'Bùi Văn H', 'I1234567', '0978901234', 0, 0),
(9, 'Ngô Thị I', 'J7654321', '0989012345', 1, 0),
(10, 'Dương Văn J', 'K9876543', '0990123456', 0, 1);


-- bus
INSERT INTO bus (bus_id, brand,  bus_number, capacity, is_delete) VALUES
(1, 'Toyota', '79A-12345', 45, 0),
(2, 'Hyundai', '30B-67890', 50, 0),
(3, 'Ford', '92C-54321', 30, 0),
(4, 'Mercedes', '29D-98765', 40, 1),
(5, 'Isuzu', '51E-45678', 55, 0),
(6, 'Hino', '60F-11223', 60, 1),
(7, 'Kia', '83G-44556', 25, 0),
(8, 'Daewoo', '84H-77889', 50, 0),
(9, 'Mitsubishi', '99K-66778', 35, 0),
(10, 'Volvo', '45L-12367', 40, 1);


-- bus-employee
INSERT INTO bus_employee(bus_id,driver_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);



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

--penalty ticket
INSERT INTO penalty_ticket (penalty_ticket_id,bus_id,driver_id,penalty_day,description,responsibility,price,is_delete) VALUES
(1,1,1,'2024-01-05 14:30:00', 'Vi phạm quá tốc độ', TRUE,200.0, FALSE),
(2,1,2,'2024-01-05 14:30:00', 'Vi phạm quá tốc độ', TRUE,200.0, FALSE);


-- payment
INSERT INTO payment (payment_time,original_amount, discount_amount, final_amount, payment_method,status, is_delete) VALUES
('2024-11-15 10:00:00', 500000, 50000, 450000, 'Credit Card', 'Completed', 0),
('2024-11-16 12:30:00', 300000, 30000, 270000, 'ZaloPay', 'Completed', 0),
('2024-11-17 09:45:00', 400000, 20000, 380000, 'Bank Transfer', 'Pending', 0),
('2024-11-18 14:00:00', 600000, 60000, 540000, 'ZaloPay', 'Completed', 0),
('2024-11-19 08:15:00', 700000, 70000, 630000, 'Credit Card', 'Refunded', 1),
('2024-11-20 16:45:00', 800000, 0, 800000, 'ZaloPay', 'Completed', 0),
('2024-11-21 11:30:00', 550000, 50000, 500000, 'E-Wallet', 'Pending', 0),
('2024-11-22 15:10:00', 250000, 0, 250000, 'Bank Transfer', 'Completed', 0),
('2024-11-23 17:20:00', 450000, 45000, 405000, 'Credit Card', 'Failed', 1),
('2024-11-24 10:50:00', 600000, 0, 600000, 'ZaloPay', 'Completed', 0);


-- ticket
INSERT INTO ticket (username_id, bus_id, routes_id, payment_id, discount_id, seat_number, departure_date, price, phone, status, is_delete) VALUES
('customer1', 1, 1, 1, 1, 'A01', '2024-11-20', 450000, '0901234567', 'Booked', 0),
('customer2', 2, 2, 2, 2, 'A02', '2024-11-21', 270000, '0912345678', 'Booked', 0),
('customer3', 3, 3, 3, NULL, 'A03', '2024-11-22', 380000, '0923456789', 'Cancelled', 1),
('customer1', 4, 4, 4, 1, 'A04', '2024-11-23', 540000, '0901234567', 'Completed', 0),
('customer2', 5, 5, 5, NULL, 'A05', '2024-11-24', 630000, '0912345678', 'Refunded', 1),
('customer3', 6, 6, 6, NULL, 'A06', '2024-11-25', 800000, '0923456789', 'Completed', 0),
('customer1', 7, 7, 7, NULL, 'A07', '2024-11-26', 500000, '0901234567', 'Booked', 0),
('customer2', 8, 8, 8, NULL, 'A08', '2024-11-27', 250000, '0912345678', 'Cancelled', 1),
('customer3', 9, 9, 9, 2, 'A09', '2024-11-28', 405000, '0923456789', 'Completed', 0),
('customer1', 10, 10, 1, NULL, 'A10', '2024-11-29', 600000, '0901234567', 'Booked', 0);

-- feedback
INSERT INTO feedback (feedback_id, username, content, ticket_id, date_comment, rating, is_delete) VALUES
(1, 'user1', 'Dịch vụ rất tốt, tài xế thân thiện.', 21, '2024-11-10', 5, 0),
(2, 'customer1', 'Xe buýt sạch sẽ và đúng giờ.', 22, '2024-11-11', 4, 0),
(3, 'customer2', 'Tài xế lái xe an toàn nhưng hơi chậm.', 23, '2024-11-12', 3, 0),
(4, 'admin2', 'Hành trình không như mong đợi, xe trễ giờ.', 24, '2024-11-13', 2, 0),
(5, 'customer3', 'Ghế ngồi không thoải mái, cần cải thiện.', 25, '2024-11-14', 3, 0),
(6, 'customer1', 'Tài xế không lịch sự, không hài lòng.', 26, '2024-11-15', 1, 1),
(7, 'user1', 'Giá vé hợp lý, dịch vụ ổn.', 27, '2024-11-16', 4, 0),
(8, 'customer2', 'Điểm đến không đúng giờ, xe cũ.', 28, '2024-11-17', 2, 0),
(9, 'customer3', 'Xe mới, thoải mái, tài xế nhiệt tình.', 29, '2024-11-18', 5, 0),
(10, 'admin2', 'Quá đông, không đủ ghế ngồi.', 30, '2024-11-19', 1, 0);















