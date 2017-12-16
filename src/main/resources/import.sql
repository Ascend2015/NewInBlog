INSERT INTO user (id, username, password, name, email) VALUES (1, 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '老卫', 'i@waylau.com');
INSERT INTO user (id, username, password, name, email)  VALUES (2, 'waylau', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'Way Lau', 'waylau@waylau.com');

INSERT INTO my_authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO my_authority (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_authority (user_id, my_authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, my_authority_id) VALUES (2, 2);
