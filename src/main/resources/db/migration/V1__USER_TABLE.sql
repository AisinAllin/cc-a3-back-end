CREATE SCHEMA if not exists "ccbackend";
DROP TABLE IF EXISTS "user_info";
DROP TABLE IF EXISTS "musical_instrument";
DROP TABLE IF EXISTS "cart";

CREATE TABLE "user_info" (
    "id" BIGSERIAL PRIMARY KEY,
    "uuid" VARCHAR(255),
    "name" VARCHAR(255),
    "email" VARCHAR(255),
    "phone_number" VARCHAR(255),
    "address" VARCHAR(255)
);

CREATE TABLE "musical_instrument" (
     "id" BIGSERIAL PRIMARY KEY,
     "user_id" BIGSERIAL NOT NULL REFERENCES "user_info" (id),
     "type" VARCHAR(255),
     "name" VARCHAR(255),
     "num_left" BIGINT,
     "price" NUMERIC,
     "count" BIGINT,
     "description" VARCHAR(255)
);

CREATE TABLE "cart" (
      "id" BIGSERIAL PRIMARY KEY,
      "user_id" BIGSERIAL NOT NULL REFERENCES "user_info" (id),
      "music_id" BIGSERIAL NOT NULL REFERENCES "musical_instrument" (id),
      "num_require" BIGINT
);

