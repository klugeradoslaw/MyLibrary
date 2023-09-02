INSERT INTO
    users(email, name, password)
VALUES
    -- admin@admin.com / admin
    ('admin@admin.com', 'Administrator', '{bcrypt}$2a$10$b0ZQzE.SGKoni4RaQ6Hut.7QBzQHHswK8GKNJHCxZvR2W7OcrZErG'),
    -- kluge.radoslaw@gmail.com / pass123
    ('kluge.radoslaw@gmail.com', 'Radosław Kluge', '{bcrypt}$2a$10$qqzCu4GlWFa.9WBLURs8YOWUR.mRKHbSsD8piMoaBqCB.VS.wKSRC'),
    -- test@test.com / 1234
    ('test@test.com', 'Tester','{bcrypt}$2a$10$If85EGk6daM8ZbwMjLRLweeJbVHL6mLQypWWTrWtGEmPhrtuQ9psK');

INSERT INTO
    user_role (name)
VALUES
    ('ADMIN'),
    ('USER');

INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 2);