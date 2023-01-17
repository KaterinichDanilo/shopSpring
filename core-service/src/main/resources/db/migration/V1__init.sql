DROP TABLE IF EXISTS orders_item;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS orders;

CREATE TABLE categories
(
    id bigserial PRIMARY KEY,
    title CHAR(50)
);

CREATE TABLE products
(
    id bigserial PRIMARY KEY,
    title CHAR(50) NOT NULL,
    price INT NOT NULL,
    category_id bigserial,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE orders
(
    id bigserial PRIMARY KEY,
    username CHAR(250) NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status CHAR(50) NOT NULL,
);

CREATE TABLE orders_item
(
    id bigserial PRIMARY KEY,
    order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO categories (title) VALUES ('food');
INSERT INTO products (title, price, category_id) VALUES ('apple', 10, 1), ('bread', 20, 1), ('banana', 40, 1);
INSERT INTO users (username, password, first_name, last_name, email)
    VALUES ('login', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Arara', 'Ururu', 'ara@gmail.com');
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
