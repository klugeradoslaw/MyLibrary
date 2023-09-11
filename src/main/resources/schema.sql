DROP TABLE IF EXISTS  user_role;
DROP TABLE IF EXISTS  users;

CREATE TABLE users
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE user_role
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE user_roles
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES user_role(id)
);

CREATE TABLE books
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL UNIQUE,
    author VARCHAR(50) NOT NULL,
    genre_id BIGINT NOT NULL,
    isbn BIGINT UNIQUE
);

CREATE TABLE genres
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE book_rating
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rating INT NOT NULL,
    book_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN  key(book_id) references books(id),
    FOREIGN  key(user_id) references users(id)
);

CREATE TABLE library
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES user_role(id)
);