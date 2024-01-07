-- crear tabla ROL
CREATE TABLE "rol" (
  "id" integer PRIMARY KEY not null,
  "nombre" varchar(100) not null UNIQUE,
  "created_at" timestamp not null
);
create sequence rol_sequence as integer increment 1;

-- crear tabla TIPO SOLICITUD RESERVA
CREATE TABLE "tipo_solicitud_reserva" (
  "id" integer PRIMARY KEY not null,
  "nombre" varchar(100) not null UNIQUE,
  "created_at" timestamp not null
);
create sequence tipo_solicitud_reserva_sequence as integer increment 1;

-- crear tabla USUARIO
CREATE TABLE "usuario" (
  "id" integer PRIMARY KEY not null,
  "usuario" varchar(100) not null UNIQUE,
  "password" varchar(100) not null,
  "telefono" integer not null,
  "nombre" varchar(100) not null,
  "apellido" varchar(100) not null,
  "correo" varchar(255) not null UNIQUE ,
  "estado" integer not null,
  "rol_id" integer
);
create sequence usuario_sequence as integer increment 1;
ALTER TABLE "usuario" ADD FOREIGN KEY ("rol_id") REFERENCES "rol" ("id") ON DELETE restrict ON UPDATE restrict;

-- crear tabla SALON
CREATE TABLE "salon" (
  "salon_id" integer PRIMARY KEY not null,
  "nombre"varchar(100) not null UNIQUE ,
  "direccion" varchar(255) not null,
  "capacidad" integer not null,
  "descripcion" varchar(100) not null,
  "tarifa" varchar(100) not null,
  "estado" integer not null,
  "usuario_id" integer,
  "created_at" timestamp
);
create sequence salon_sequence as integer increment 1;
ALTER TABLE "salon" ADD FOREIGN KEY ("usuario_id") REFERENCES "usuario" ("id") ON DELETE restrict ON UPDATE restrict;

-- crear tabla IMAGEN SALON
CREATE TABLE "imagen_salon" (
  "id" integer PRIMARY KEY not null,
  "url" varchar(255) not null,
  "salon_id" integer
);
create sequence imagen_salon_sequence as integer increment 1;
ALTER TABLE "imagen_salon" ADD FOREIGN KEY ("salon_id") REFERENCES "salon" ("salon_id") ON DELETE restrict ON UPDATE restrict;


-- crear tabla TELEFONO SALON
CREATE TABLE "telefono_salon" (
  "id" integer PRIMARY KEY not null,
  "numero" integer not null UNIQUE,
  "salon_id" integer
);
create sequence telefono_salon_sequence as integer increment 1;
ALTER TABLE "telefono_salon" ADD FOREIGN KEY ("salon_id") REFERENCES "salon" ("salon_id") ON DELETE restrict ON UPDATE restrict;


-- crear tabla SOLICITUD RESERVA
CREATE TABLE "solicitud_reserva" (
  "id" integer PRIMARY KEY not null,
  "detalle" varchar(255) not null,
  "fecha_reserva" time not null,
  "fecha_emision" time not null,
  "motivo" varchar(100) not null,
  "salon_id" integer,
  "usuario_id" integer,
  "tipoSR_id" integer
);
create sequence solicitud_reserva_sequence as integer increment 1;
ALTER TABLE "solicitud_reserva" ADD FOREIGN KEY ("salon_id") REFERENCES "salon" ("salon_id") ON DELETE restrict ON UPDATE restrict;
ALTER TABLE "solicitud_reserva" ADD FOREIGN KEY ("tipoSR_id") REFERENCES "tipo_solicitud_reserva" ("id") ON DELETE restrict ON UPDATE restrict;
ALTER TABLE "solicitud_reserva" ADD FOREIGN KEY ("usuario_id") REFERENCES "usuario" ("id") ON DELETE restrict ON UPDATE restrict;

-- crear tabla SERVICIO
CREATE TABLE "servicio" (
  "servicio_id" integer PRIMARY KEY not null,
  "nombre" varchar(100) not null UNIQUE,
  "detalle" varchar(255) not null,
  "estado" integer not null,
  "created_at" timestamp
);
create sequence servicio_sequence as integer increment 1;


-- crear tabla SERVICIO SALON
CREATE TABLE "servicio_salon" (
  "id" SERIAL PRIMARY KEY not null,
  "salon_id" integer,
  "servicio_id" integer
);
ALTER TABLE "servicio_salon" ADD FOREIGN KEY ("salon_id") REFERENCES "salon" ("salon_id") ON DELETE restrict ON UPDATE restrict;
ALTER TABLE "servicio_salon" ADD FOREIGN KEY ("servicio_id") REFERENCES "servicio" ("servicio_id") ON DELETE restrict ON UPDATE restrict;









