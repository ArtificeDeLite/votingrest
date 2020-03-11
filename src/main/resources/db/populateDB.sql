DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM restaurant;
DELETE FROM users;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurant (description) VALUES
  ('Moskva'),
  ('Rostov'),
  ('Tbilisi'),
  ('St.Petersburg'),
  ('Stavropol');

INSERT INTO dishes (description, price, date_time, rest_id) VALUES
  ('soup #1', 250.0, '2020-01-31 0:00:00', 100002),
  ('main dish #1', 450.0, '2020-01-31 0:00:00', 100002),
  ('tea #1', 50.0, '2020-01-31 0:00:00', 100002),
  ('soup #2', 150.0, '2020-01-31 0:00:00', 100003),
  ('main dish #2', 350.0, '2020-01-31 0:00:00', 100003),
  ('coffee #2', 50.0, '2020-01-31 0:00:00', 100003),
  ('soup #3', 200.0, '2020-01-31 0:00:00', 100004),
  ('main dish #3', 300.0, '2020-01-31 0:00:00', 100004),
  ('coffee #3', 50.0, '2020-01-31 0:00:00', 100004),
  ('soup #4', 200.0, '2020-01-31 0:00:00', 100005),
  ('main dish #4', 300.0, '2020-01-31 0:00:00', 100005),
  ('coffee #4', 350.0, '2020-01-31 0:00:00', 100005),
  ('soup #5', 200.0, '2020-01-31 0:00:00', 100006),
  ('main dish #5', 300.0, '2020-01-31 0:00:00', 100006),
  ('coffee #5', 350.0, '2020-01-31 0:00:00', 100006),
  ('soup #11', 250.0, '2020-02-01 0:00:00', 100002),
  ('main dish #11', 450.0, '2020-02-01 0:00:00', 100002),
  ('tea #11', 50.0, '2020-02-01 0:00:00', 100002),
  ('soup #21', 150.0, '2020-02-01 0:00:00', 100003),
  ('main dish #21', 350.0, '2020-02-01 0:00:00', 100003),
  ('coffee #21', 50.0, '2020-02-01 0:00:00', 100003),
  ('soup #31', 200.0, '2020-02-01 0:00:00', 100004),
  ('main dish #31', 300.0, '2020-02-01 0:00:00', 100004),
  ('coffee #31', 50.0, '2020-02-01 0:00:00', 100004),
  ('soup #41', 200.0, '2020-02-01 0:00:00', 100005),
  ('main dish #41', 300.0, '2020-02-01 0:00:00', 100005),
  ('coffee #41', 350.0, '2020-02-01 0:00:00', 100005),
  ('soup #51', 200.0, '2020-02-01 0:00:00', 100006),
  ('main dish #51', 300.0, '2020-02-01 0:00:00', 100006),
  ('coffee #51', 350.0, '2020-02-01 0:00:00', 100006);

INSERT INTO votes (date_time, user_id, rest_id) VALUES
    ('2020-01-31 0:00:00', 100000, 100002),
    ('2020-01-31 0:00:00', 100001, 100002);

-- TODO: заполнить таблицы данными
-- TODO : создай энтити под таблицам
-- TODO :создай хибернейт аннотации и named query
-- TODO :сервис, контроллер, сервлет (и вью по желанию)
