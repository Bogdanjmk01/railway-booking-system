SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM user_roles;

INSERT INTO users(first_name, last_name, email, password) VALUES('Bogdan', 'Mocanu', 'bogdanzeu@yahoo.com', '$2a$12$5j8Xq/bSMt3Ehdi9lBUbgO.VxrGk4z6bAu.YaH6V/fV6VdyDTsagW');
INSERT INTO roles(name, description) VALUES('ROLE_ADMIN', 'Can perform any kind of operation');
INSERT INTO roles(name, description) VALUES('ROLE_USER', 'Is a user');
INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);