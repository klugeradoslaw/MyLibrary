INSERT INTO users (email, name, password)
VALUES
    -- admin@admin.com / admin
    ('admin@admin.com', 'Administrator', '{bcrypt}$2a$10$b0ZQzE.SGKoni4RaQ6Hut.7QBzQHHswK8GKNJHCxZvR2W7OcrZErG'),
    -- kluge.radoslaw@gmail.com / pass123
    ('kluge.radoslaw@gmail.com', 'Radosław Kluge', '{bcrypt}$2a$10$qqzCu4GlWFa.9WBLURs8YOWUR.mRKHbSsD8piMoaBqCB.VS.wKSRC'),
    -- test@test.com / 1234
    ('test@test.com', 'Tester','{bcrypt}$2a$10$If85EGk6daM8ZbwMjLRLweeJbVHL6mLQypWWTrWtGEmPhrtuQ9psK');

INSERT INTO user_role (name)
VALUES
    ('ADMIN'),
    ('USER');

INSERT INTO user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 2);

INSERT INTO  genres (name)
VALUES
    ('Information Technology'),
    ('action'),
    ('adventure'),
    ('comedy'),
    ('drama'),
    ('fantasy'),
    ('horror'),
    ('science fiction'),
    ('western');

INSERT INTO books (title, author, genre_id, isbn)
VALUES
    ('W pustyni i w puszczy', 'Henryk Sienkiewicz', 3, 9788324029594),
    ('Java. Rusz głową!', 'Kathy Sierra', 1, 9788328399846),
    ('Wydajność Javy. Szczegółowe porady dotyczące programowania i strojenia aplikacji w Javie. Wydanie II', 'Scott Oaks', 1, 9788328370319);

INSERT INTO book_rating (rate, book_id, user_id)
VALUES
    (9,1,2),
    (6,2,3),
    (8,2,2);

