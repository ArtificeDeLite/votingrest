DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM restaurant;
DELETE FROM users;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', '{noop}password'),
  ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('USER', 100000),
  ('ADMIN', 100001);

INSERT INTO restaurant (description) VALUES
  ('Moskva'),
  ('Rostov'),
  ('Tbilisi'),
  ('St.Petersburg'),
  ('Stavropol');

INSERT INTO dishes (description, price, date_time, rest_id) VALUES
  ('soup #1', 25000, '2020-01-31 0:00:00', 100002),
  ('main dish #1', 45000, '2020-01-31 0:00:00', 100002),
  ('tea #1', 5000, '2020-01-31 0:00:00', 100002),
  ('soup #2', 15000, '2020-01-31 0:00:00', 100003),
  ('main dish #2', 35000, '2020-01-31 0:00:00', 100003),
  ('coffee #2', 5000, '2020-01-31 0:00:00', 100003),
  ('soup #3', 20000, '2020-01-31 0:00:00', 100004),
  ('main dish #3', 30000, '2020-01-31 0:00:00', 100004),
  ('coffee #3', 5000, '2020-01-31 0:00:00', 100004),
  ('soup #4', 20000, '2020-01-31 0:00:00', 100005),
  ('main dish #4', 30000, '2020-01-31 0:00:00', 100005),
  ('coffee #4', 35000, '2020-01-31 0:00:00', 100005),
  ('soup #5', 20000, '2020-01-31 0:00:00', 100006),
  ('main dish #5', 30000, '2020-01-31 0:00:00', 100006),
  ('coffee #5', 35000, '2020-01-31 0:00:00', 100006),
  ('soup #11', 25000, CURRENT_DATE, 100002),
  ('main dish #11', 45000, CURRENT_DATE, 100002),
  ('tea #11', 5000, CURRENT_DATE, 100002),
  ('soup #21', 15000, CURRENT_DATE, 100003),
  ('main dish #21', 35000, CURRENT_DATE, 100003),
  ('coffee #21', 5000, CURRENT_DATE, 100003),
  ('soup #31', 20000, CURRENT_DATE, 100004),
  ('main dish #31', 30000, CURRENT_DATE, 100004),
  ('coffee #31', 5000, CURRENT_DATE, 100004),
  ('soup #41', 20000, CURRENT_DATE, 100005),
  ('main dish #41', 30000, CURRENT_DATE, 100005),
  ('coffee #41', 35000, CURRENT_DATE, 100005),
  ('soup #51', 20000, CURRENT_DATE, 100006),
  ('main dish #51', 30000, CURRENT_DATE, 100006),
  ('coffee #51', 35000, CURRENT_DATE, 100006);
  --('coffee #', 35000, CURRENT_DATE, 100002);


INSERT INTO votes (date_time, user_id, rest_id) VALUES
    ('2020-01-31 0:00:00', 100000, 100002),
    ('2020-01-31 0:00:00', 100001, 100002),
    ('2020-02-01 0:00:00', 100001, 100003);
