

-- Đây là file được dùng để test database, phải chạy bằng tay 


-- Thêm dữ liệu vào account
INSERT INTO account (username, password, email, phone, ROLE, is_delete, is_block) 
VALUES 
('john_doe', 'mypassword123', 'john.doe@example.com', '0123456789', 'USER', 'NO', 'NO'),
('user', '123123', 'john.doe@example.com', '0123456789', 'USER', 'NO', 'NO');


-- Thêm dữ liệu vào discount
INSERT INTO discount (discount_percentage, valid_from, valid_until, amount, is_delete) VALUES
(10.0, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 100, 'NO'),
(20.0, '2024-06-01 00:00:00', '2024-09-30 23:59:59', 50, 'NO');


-- employee
INSERT INTO employee (is_driver, driver_name, license_number, phone_number, is_delete) VALUES
('YES', 'Nguyen Van A', '123456789', '0987654321', 'NO'),
('NO', 'Tran Thi B', '987654321', '0123456789', 'NO');


-- bus
INSERT INTO bus (bus_id, brand,  bus_number, capacity, is_delete) VALUES
(1,'Higer','BS1234', 40, 'NO'),
(2,'Higer','BS5678', 50, 'NO');


-- bus-employee
INSERT INTO bus_employee(bus_id,driver_id) VALUES
(1,1),
(1,2)



-- bus_routes
INSERT INTO bus_routes (routes_id, arival_time, departure_time, departure_location, destination_location, distance_location, price, is_delete) VALUES
(1, '2024-01-05 14:30:00','2024-01-06 14:30:00', 'Hà Nội', 'Hải Phòng', 200, 120.5, 'NO'),
(2, '2024-01-05 14:30:00','2024-01-06 14:30:00', 'Long An', 'TP Hồ Chí Minh', 47, 30.0, 'NO');



-- feedback
INSERT INTO feedback (feedback_id, username, content, ticket_id, date_comment, rating, is_delete) VALUES
(1,'john_doe', 'Dịch vụ tốt', 1, '2024-01-05 14:30:00', 5, 'NO'),
(2,'user', 'Cần cải thiện', 2, '2024-01-06 15:45:00', 5,'NO');


-- payment
INSERT INTO payment (payment_time, payment_method, is_delete) VALUES
('2024-01-01 12:00:00', 'Credit Card', 'NO'),
('2024-01-02 13:30:00', 'Cash', 'NO');


-- ticket
INSERT INTO ticket (username_id, bus_id, routes_id, payment_id, discount_id, seat_number, departure_date, price, userName, phone, email, is_delete) VALUES
('john_doe', 1, 1, 1, 1, 'A1', '2024-10-12', 50.0, 'Nguyen Van A', '0987654321', 'a@example.com', 'NO'),
('user', 2, 2, 2, NULL, 'B2', '2024-10-13', 75.0, 'Tran Thi B', '0123456789', 'b@example.com', 'NO');







