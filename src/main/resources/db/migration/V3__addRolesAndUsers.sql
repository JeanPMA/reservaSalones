INSERT INTO rol (id, nombre, created_at) SELECT nextval('rol_sequence'), 'ADMIN', CURRENT_TIMESTAMP WHERE NOT EXISTS (SELECT 1 FROM rol WHERE nombre = 'ADMIN');
INSERT INTO rol (id, nombre, created_at) SELECT nextval('rol_sequence'), 'USER', CURRENT_TIMESTAMP WHERE NOT EXISTS (SELECT 1 FROM rol WHERE nombre = 'USER');
INSERT INTO rol (id, nombre, created_at) SELECT nextval('rol_sequence'), 'OWNER', CURRENT_TIMESTAMP WHERE NOT EXISTS (SELECT 1 FROM rol WHERE nombre = 'OWNER');

INSERT INTO usuario (id, username, password, telefono, nombre, apellido, correo, estado, rol_id)
SELECT nextval('usuario_sequence'), 'user', '$2a$10$oqpYCv2OgYgXxTQYdgc/UO8ixRDjpNXpAY.42Gku0jQv9kob6EJJu', '12345678', 'user', 'user', 'user@gmail.com', '1', r.id
FROM rol r
WHERE r.nombre = 'USER' AND NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'user');
INSERT INTO usuario (id, username, password, telefono, nombre, apellido, correo, estado, rol_id)
SELECT nextval('usuario_sequence'), 'owner', '$2a$10$oqpYCv2OgYgXxTQYdgc/UO8ixRDjpNXpAY.42Gku0jQv9kob6EJJu', '87654321', 'owner', 'owner', 'owner@gmail.com', '1', r.id
FROM rol r
WHERE r.nombre = 'OWNER' AND NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'owner');
INSERT INTO usuario (id, username, password, telefono, nombre, apellido, correo, estado, rol_id)
SELECT nextval('usuario_sequence'), 'admin', '$2a$10$oqpYCv2OgYgXxTQYdgc/UO8ixRDjpNXpAY.42Gku0jQv9kob6EJJu', '99999999', 'admin', 'admin', 'admin@gmail.com', '1', r.id
FROM rol r
WHERE r.nombre = 'ADMIN' AND NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'admin');