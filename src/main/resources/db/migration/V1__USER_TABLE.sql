CREATE SCHEMA if not exists "ccbackend";
DROP TABLE IF EXISTS "user_info";
CREATE TABLE "user_info" (
    "id" BIGSERIAL PRIMARY KEY,
    "email" VARCHAR(255),
    "name" VARCHAR(255),
    "title" VARCHAR(255),
    "phoneNumber" VARCHAR(255),
    "password" CHAR(64),
    "status" VARCHAR(255)
);
