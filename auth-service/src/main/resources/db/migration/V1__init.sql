DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE roles
(
    id bigserial PRIMARY KEY,
    name CHAR(50)
);

CREATE TABLE users
(
    id bigserial PRIMARY KEY,
    username CHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
    first_name CHAR(50) NOT NULL,
    last_name CHAR(50) NOT NULL,
    email CHAR(50) NOT NULL
);

CREATE TABLE users_roles (
    user_id bigserial NOT NULL,
    role_id bigserial NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
INSERT INTO users (username, password, first_name, last_name, email)
    VALUES ('login', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Arara', 'Ururu', 'ara@gmail.com');
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
