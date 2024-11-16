

-- Đây là file được dùng để test database, phải chạy bằng tay 


-- Thêm dữ liệu vào account
INSERT INTO account (username, password, email, phone, ROLE, is_delete, is_block) 
VALUES 
('john_doe', 'mypassword123', 'john.doe@example.com', '0123456789', 'ROLE_ADMIN', FALSE, FALSE),
('user1', '123123', 'john.doe@example.com', '0123456789', 'ROLE_USER', FALSE, FALSE);


-- Thêm dữ liệu vào discount
INSERT INTO discount (discount_percentage, valid_from, valid_until, amount, is_delete) VALUES
(10.0, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 100, FALSE),
(20.0, '2024-06-01 00:00:00', '2024-09-30 23:59:59', 50, FALSE);


-- employee
INSERT INTO employee (is_driver, driver_name, license_number, phone_number, is_delete) VALUES
(1, 'Nguyen Van A', '123456789', '0987654321', FALSE),
(2, 'Tran Thi B', '987654321', '0123456789', FALSE);


-- bus
INSERT INTO bus (bus_id, brand,  bus_number, capacity, is_delete) VALUES
(1,'Higer','BS1234', 40, FALSE),
(2,'Higer','BS5678', 50, FALSE);


-- bus-employee
INSERT INTO bus_employee(bus_id,driver_id) VALUES
(1,1),
(1,2)



-- bus_routes
INSERT INTO bus_routes (routes_id, arival_time, departure_time, departure_location, destination_location, distance_location, price, is_delete) VALUES
(1, '2024-01-05 14:30:00','2024-01-06 14:30:00', 'Hà Nội', 'Hải Phòng', 200, 120.5, FALSE),
(2, '2024-01-05 14:30:00','2024-01-06 14:30:00', 'Long An', 'TP Hồ Chí Minh', 47, 30.0, FALSE);

--penalty ticket
INSERT INTO penalty_ticket (penalty_ticket_id,bus_id,driver_id,penalty_day,description,responsibility,price,is_delete) VALUES
(1,1,1,'2024-01-05 14:30:00', 'Vi phạm quá tốc độ', TRUE,200.0, FALSE),
(2,1,2,'2024-01-05 14:30:00', 'Vi phạm quá tốc độ', TRUE,200.0, FALSE);


-- payment
INSERT INTO payment (payment_time,original_amount, discount_amount, final_amount, payment_method,status, is_delete) VALUES
('2024-01-01 12:00:00',100.0,30.0,70.0,  'Credit Card','success', FALSE),
('2024-01-01 12:00:00',150.0,30.0,120.0,  'Credit Card','success', FALSE);


-- ticket
INSERT INTO ticket (username_id, bus_id, routes_id, payment_id, discount_id, seat_number, departure_date, price, phone,status, is_delete) VALUES
('john_doe', 1, 1, 1, 1, 'A1', '2024-10-12', 100.0, '0987654321', 'success', FALSE),
('user1', 1, 1, 1, 1, 'A2', '2024-10-12', 150.0, '0987654321', 'success', FALSE);

-- feedback
INSERT INTO feedback (feedback_id, username, content, ticket_id, date_comment, rating, is_delete) VALUES
(1,'john_doe', 'Dịch vụ tốt', 1, '2024-01-05 14:30:00', 5, FALSE),
(2,'user1', 'Cần cải thiện', 2, '2024-01-06 15:45:00', 5,FALSE);







