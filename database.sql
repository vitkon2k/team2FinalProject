CREATE DATABASE laptoplc;
USE laptoplc;

CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE brand (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE,
	image_url VARCHAR(500)
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category_id INT,
    brand_id INT,
    price DECIMAL(10,2) NOT NULL,
    discount_price DECIMAL(10,2),
    stock INT DEFAULT 0,
    image_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
    FOREIGN KEY (brand_id) REFERENCES brand(id) ON DELETE SET NULL
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(15),
    password_hash VARCHAR(255) NOT NULL,
    address TEXT,
    role ENUM('customer', 'admin') DEFAULT 'customer',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    total_price DECIMAL(10,2) NOT NULL,
    order_status ENUM('pending', 'shipped', 'delivered', 'canceled') DEFAULT 'pending',
    shipping_address TEXT,
    payment_status ENUM('pending', 'paid', 'failed') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE promotions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    category_id INT NULL,
    discount_percentage DECIMAL(5,2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

CREATE TABLE product_detail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    attribute_name VARCHAR(255) NOT NULL,
    attribute_value VARCHAR(300),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);



CREATE TABLE news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    image_url VARCHAR(500),
    author_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,  -- Mỗi khách hàng chỉ có một giỏ hàng hiện hành
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE cart_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT,
    product_id INT,
    quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

USE laptoplc;

INSERT INTO users (full_name, email, phone, password_hash, address, role)
VALUES ('Admin User', 'admin@example.com', '0123456789', '$2a$12$3UxGruGEnEQEBzUG0LbSq.Oz5qGCfIu7Qz1708QkgBQMqN6TwbT4m', 'Some Address', 'admin');

INSERT INTO categories (name, description) VALUES
('Laptop Văn Phòng', 'Laptop mỏng nhẹ, pin trâu, phù hợp cho công việc và học tập.'),
('Laptop Gaming', 'Laptop hiệu năng cao, card đồ họa mạnh, phù hợp cho gaming.'),
('PC', 'Máy tính để bàn dành cho công việc và gaming.'),
('Phụ kiện', 'Chuột, bàn phím, tai nghe và các phụ kiện khác.');

INSERT INTO brand (name, image_url) VALUES
('ASUS', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('Dell', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('Apple', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('Lenovo', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('HP', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('Acer', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('MSI', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('Razer', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('Logitech', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('Corsair', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg'),
('SteelSeries', 'https://gdziejestneon.com/wp-content/uploads/2023/06/IMG_6853-scaled-1.jpg');


INSERT INTO products (name, description, category_id, brand_id, price, discount_price, stock, image_url) VALUES
('Dell XPS 15', 'Laptop đồ họa mạnh mẽ với màn hình 4K OLED, Intel Core i9, RAM 32GB.', 1, 2, 2499.99, 2399.99, 5, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('MacBook Air M2', 'Laptop mỏng nhẹ với chip M2, màn hình Retina, pin 18 giờ.', 1, 3, 1299.99, 1249.99, 10, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('HP Spectre x360', 'Ultrabook cao cấp với Intel Core i7, màn hình cảm ứng OLED, RAM 16GB.', 1, 5, 1799.99, 1699.99, 7, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('Lenovo ThinkPad X1 Carbon', 'Laptop doanh nhân với Intel Core i7, màn hình 14" Full HD.', 1, 4, 1599.99, 1499.99, 8, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('ASUS ROG Strix G16', 'Laptop gaming với CPU Intel Core i7, RTX 4060, RAM 16GB, SSD 1TB.', 2, 1, 3299.99, 2999.99, 10, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('Acer Predator Helios 300', 'Laptop gaming với Intel Core i7, RTX 3060, RAM 16GB, SSD 512GB.', 2, 6, 1499.99, 1399.99, 15, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('MSI Stealth 15M', 'Laptop gaming mỏng nhẹ với Intel Core i7, RTX 4050, RAM 16GB.', 2, 7, 1699.99, 1599.99, 9, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('Razer Blade 14', 'Laptop gaming cao cấp với Ryzen 9, RTX 4070, màn hình QHD 165Hz.', 2, 8, 2699.99, 2599.99, 6, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('ASUS ROG Strix G35', 'PC gaming mạnh mẽ với Intel i9, RTX 4090, RAM 32GB.', 3, 1, 3999.99, 3799.99, 5, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('Dell XPS Desktop', 'PC cao cấp với Intel Core i7, RAM 16GB, SSD 1TB.', 3, 2, 1999.99, 1899.99, 7, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('Apple Mac Studio', 'Máy tính Mac Studio với chip M2 Ultra, RAM 64GB.', 3, 3, 3999.99, 3799.99, 3, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('HP Omen 45L', 'PC gaming với Intel i7, RTX 4070, RAM 32GB.', 3, 5, 2999.99, 2799.99, 4, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('Logitech G Pro X Superlight', 'Chuột gaming siêu nhẹ chỉ 63g, kết nối không dây.', 4, 9, 149.99, 139.99, 20, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('Corsair K95 RGB Platinum', 'Bàn phím cơ gaming RGB với switch Cherry MX.', 4, 10, 199.99, 179.99, 15, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('SteelSeries Arctis Pro Wireless', 'Tai nghe gaming không dây chất lượng cao.', 4, 11, 299.99, 279.99, 12, 'http://localhost:8080/api/file/get/product_12_1741070296319.png'),
('Razer Goliathus Extended Chroma', 'Lót chuột gaming RGB cỡ lớn.', 4, 8, 59.99, 49.99, 30, 'http://localhost:8080/api/file/get/product_12_1741070296319.png');

INSERT INTO users (full_name, email, phone, password_hash, address, role, created_at) VALUES
('User 1', 'user1@example.com', '0912345678', '$2b$12$jUrkqyM7HZ2oFgGrt9GHJ./Vfa6iwuv/MNXumhGn/AQgfONEFpWLS', 'Address 1, Vietnam', 'customer', NOW()),
('User 2', 'user2@example.com', '0912345679', '$2b$12$hwcNzYTTn/.2Hg/KEfsmIOF/B1B/oFYSCOQHK8OBBBRqEBfeu7UA2', 'Address 2, Vietnam', 'customer', NOW()),
('User 3', 'user3@example.com', '0912345680', '$2b$12$nG1Dt7RqCjkAiBfZdLhPDOBCZPaGxqVGqlQdmz9Udiwh5XoRd7272', 'Address 3, Vietnam', 'customer', NOW()),
('User 4', 'user4@example.com', '0912345681', '$2b$12$ZlJN1QCCovHt6vfmjOofHu9A4trMgAmNW7COdS6tE1BEyt2tLcqEm', 'Address 4, Vietnam', 'customer', NOW()),
('User 5', 'user5@example.com', '0912345682', '$2b$12$/5ILC8jelNEHG.5jC2Fm2ewHOYF8.ldcAHTaaU.XkpZT4CdjZoyIS', 'Address 5, Vietnam', 'customer', NOW()),
('User 6', 'user6@example.com', '0912345683', '$2b$12$T0/ZS5Bf92Op8oIwfFMwIeWwjcVfGNxeIF57HImJTOv.SEVX61KNK', 'Address 6, Vietnam', 'customer', NOW()),
('User 7', 'user7@example.com', '0912345684', '$2b$12$Dqdj1vUZnNKlSJXfjpZ8hu5mZ/cMbBiO4WY9i9raIfamuiu8oObwK', 'Address 7, Vietnam', 'customer', NOW()),
('User 8', 'user8@example.com', '0912345685', '$2b$12$AkHaoVD76tvX5.NGQh2OlO3Cahx29FUxxuDKHli4WOCDEDnD1.kSq', 'Address 8, Vietnam', 'customer', NOW()),
('User 9', 'user9@example.com', '0912345686', '$2b$12$b6h8/zGuuvitF5aTzIaideLW71kxPKnX81annbrQj4pvJQLfWEgjW', 'Address 9, Vietnam', 'customer', NOW()),
('User 10', 'user10@example.com', '0912345687', '$2b$12$rW3q/YPXx1WbHyunzwynZeEj7mboXHvVpsLpOSIivzrJmnneiIA.O', 'Address 10, Vietnam', 'customer', NOW()),
('User 11', 'user11@example.com', '0912345688', '$2b$12$yX5vwIwEJfrGbvbGQegBRec8QofNgA9B1Omyjsbs5uA53Hitm0dxO', 'Address 11, Vietnam', 'customer', NOW()),
('User 12', 'user12@example.com', '0912345689', '$2b$12$9LigAywLEW/ll9cHVHqJyOwS4gXDRGgFFQLUgFQFZcqDkUZ6PKKgW', 'Address 12, Vietnam', 'customer', NOW()),
('User 13', 'user13@example.com', '0912345690', '$2b$12$/0QBI4N5ycAFDbKvw616E.4/h7Purzz.IygKf2L7Il84/xlNCPqry', 'Address 13, Vietnam', 'customer', NOW()),
('User 14', 'user14@example.com', '0912345691', '$2b$12$cW/JHlyKfFUlSBw7T6Y4ZOdjqvGXk4oYskVdZhmS0IS8sUs8O4Igu', 'Address 14, Vietnam', 'customer', NOW()),
('User 15', 'user15@example.com', '0912345692', '$2b$12$5BFRkNfc.c5uUu4yDHx62eJ87.sxzbKYGeAZN1LuZo466AHs5xn5O', 'Address 15, Vietnam', 'customer', NOW());

INSERT INTO orders (user_id, total_price, order_status, shipping_address, payment_status, created_at) VALUES
(1, 250000, 'pending', 'Hà Nội, Việt Nam', 'pending', NOW()),
(2, 350000, 'shipped', 'TP Hồ Chí Minh, Việt Nam', 'paid', NOW()),
(3, 150000, 'delivered', 'Đà Nẵng, Việt Nam', 'paid', NOW()),
(4, 550000, 'canceled', 'Hải Phòng, Việt Nam', 'failed', NOW()),
(5, 120000, 'pending', 'Cần Thơ, Việt Nam', 'pending', NOW()),
(6, 220000, 'shipped', 'Hà Giang, Việt Nam', 'paid', NOW()),
(7, 780000, 'delivered', 'Huế, Việt Nam', 'paid', NOW()),
(8, 340000, 'pending', 'Quảng Ninh, Việt Nam', 'pending', NOW()),
(9, 470000, 'shipped', 'Nghệ An, Việt Nam', 'paid', NOW()),
(10, 650000, 'delivered', 'Bắc Giang, Việt Nam', 'paid', NOW()),
(11, 890000, 'canceled', 'Thanh Hóa, Việt Nam', 'failed', NOW()),
(12, 990000, 'pending', 'Nam Định, Việt Nam', 'pending', NOW()),
(13, 123000, 'shipped', 'Vũng Tàu, Việt Nam', 'paid', NOW()),
(14, 234000, 'delivered', 'Hải Dương, Việt Nam', 'paid', NOW()),
(15, 345000, 'canceled', 'Lào Cai, Việt Nam', 'failed', NOW());

INSERT INTO news (title, content, image_url, author_id, created_at, updated_at) VALUES
('AI và Tương lai', 'Trí tuệ nhân tạo đang thay đổi thế giới...', 'uploads/ai-future.jpg', 11, NOW(), NOW()),
('Cách mạng Công nghệ 4.0', 'Cuộc cách mạng công nghiệp 4.0 mang đến...', 'uploads/tech-revolution.jpg', 12, NOW(), NOW()),
('Bí quyết thành công của Elon Musk', 'Elon Musk nổi tiếng với...', 'uploads/elon-musk.jpg', 13, NOW(), NOW()),
('Thế giới hậu COVID-19', 'Đại dịch COVID-19 đã thay đổi...', 'uploads/covid19.jpg', 14, NOW(), NOW()),
('Hành trình khám phá vũ trụ', 'NASA và SpaceX đang nghiên cứu...', 'uploads/space-exploration.jpg', 15, NOW(), NOW()),
('Blockchain và Tiền điện tử', 'Công nghệ blockchain đã tạo ra...', 'uploads/blockchain.jpg', 11, NOW(), NOW()),
('Xe điện - Tương lai giao thông', 'Xe điện đang dần thay thế xe...', 'uploads/electric-car.jpg', 12, NOW(), NOW()),
('Bí quyết sống khỏe', 'Làm thế nào để có một cuộc sống khỏe mạnh?', 'uploads/healthy-living.jpg', 13, NOW(), NOW()),
('Tại sao ChatGPT phổ biến?', 'ChatGPT là một chatbot AI tiên tiến...', 'uploads/chatgpt.jpg', 14, NOW(), NOW()),
('Làm chủ kỹ năng lập trình', 'Lập trình là một kỹ năng quan trọng...', 'uploads/coding.jpg', 15, NOW(), NOW());









