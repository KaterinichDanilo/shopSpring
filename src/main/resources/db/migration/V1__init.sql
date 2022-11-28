DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS orders_item;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS orders;

CREATE TABLE roles
(
    id bigserial PRIMARY KEY,
    name CHAR(50)
);

CREATE TABLE users
(
    id bigserial PRIMARY KEY,
    login CHAR(50) NOT NULL,
    password CHAR(50) NOT NULL,
    firstName CHAR(50) NOT NULL,
    lastName CHAR(50) NOT NULL,
    email CHAR(50) NOT NULL
);

CREATE TABLE users_roles (
    user_id bigserial NOT NULL,
    role_id bigserial NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE categories
(
    id bigserial PRIMARY KEY,
    name CHAR(50)
);

CREATE TABLE products
(
    id bigserial PRIMARY KEY,
    title CHAR(50) NOT NULL,
    price DECIMAL NOT NULL,
    category_id bigserial,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE orders
(
    id bigserial PRIMARY KEY,
    user_id bigserial NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status CHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE orders_item
(
    id bigserial PRIMARY KEY,
    order_id bigserial NOT NULL,
    product_id bigserial NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

