ALTER TABLE "salon" ALTER COLUMN "descripcion" TYPE varchar(255);

INSERT INTO tipo_solicitud_reserva (id, nombre, created_at) SELECT nextval('tipo_solicitud_reserva_sequence'), 'PENDIENTE', CURRENT_TIMESTAMP WHERE NOT EXISTS (SELECT 1 FROM tipo_solicitud_reserva WHERE nombre = 'PENDIENTE');
INSERT INTO tipo_solicitud_reserva (id, nombre, created_at) SELECT nextval('tipo_solicitud_reserva_sequence'), 'ACEPTADO', CURRENT_TIMESTAMP WHERE NOT EXISTS (SELECT 1 FROM tipo_solicitud_reserva WHERE nombre = 'ACEPTADO');
INSERT INTO tipo_solicitud_reserva (id, nombre, created_at) SELECT nextval('tipo_solicitud_reserva_sequence'), 'CANCELADO', CURRENT_TIMESTAMP WHERE NOT EXISTS (SELECT 1 FROM tipo_solicitud_reserva WHERE nombre = 'CANCELADO');
INSERT INTO tipo_solicitud_reserva (id, nombre, created_at) SELECT nextval('tipo_solicitud_reserva_sequence'), 'RECHAZADO', CURRENT_TIMESTAMP WHERE NOT EXISTS (SELECT 1 FROM tipo_solicitud_reserva WHERE nombre = 'RECHAZADO');
INSERT INTO tipo_solicitud_reserva (id, nombre, created_at) SELECT nextval('tipo_solicitud_reserva_sequence'), 'INVISIBLE', CURRENT_TIMESTAMP WHERE NOT EXISTS (SELECT 1 FROM tipo_solicitud_reserva WHERE nombre = 'INVISIBLE');