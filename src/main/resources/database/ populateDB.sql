INSERT INTO users
VALUES (1, 'Vassily', 'Petrov', 'vpetrov@jr.com', 'pass'),
       (2, 'Pjotr', 'Vasechkin', 'pvasechkin@jr.com', 'pass');
insert into role
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_USER');
insert into users_roles
values (1, 1),
       (2, 2);