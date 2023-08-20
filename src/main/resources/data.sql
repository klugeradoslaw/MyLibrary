INSERT INTO
    users(email, name, password)
VALUES
    -- superadmin@example.com / hard
    ('superadmin@example.com', 'Jan', '{bcrypt}$2a$10$Ruu5GtmSVkfLeuGfz/wHUuzflCcMbwJHSBo/.Wui0EM0KIM52Gs2S'),
    -- john@example.com / dog.8
    ('john@example.com', 'John', '{MD5}{AlZCLSQMMNLBS5mEO0kSem9V3mxplC6cTjWy9Kj/Gxs=}d9007147eb3a5f727b2665d647d36e35'),
    -- kluge.radoslaw@gmail.com / javaiscool
    ('kluge.radoslaw@gmail.com', 'Radek','{argon2}$argon2id$v=19$m=4096,t=3,p=1$YBBBwx+kfrNgczYDcLlWYA$LEPgdtfskoobyFtUWTMejaE5SBRyieHYbiE5ZmFKE7I');

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