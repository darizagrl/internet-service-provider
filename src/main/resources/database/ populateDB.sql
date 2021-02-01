INSERT INTO users
VALUES (1, 'Vassily', 'Petrov', 'vpetrov@jr.com', 'pass'),
       (2, 'Pjotr', 'Vasechkin', 'pvasechkin@jr.com', 'pass'),
       (3, 'Admin', 'Admin', 'admin@mail.com', 'admin'),
       (4, 'Admin2', 'Admin2', 'admin2@mail.com', 'admin');
insert into role
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_ADMIN'),
       (4, 'ROLE_ADMIN');;
insert into users_roles
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);