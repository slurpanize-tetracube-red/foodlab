CREATE EXTENSION if not exists "uuid-ossp";

CREATE SCHEMA IF NOT EXISTS foodhouse;

create table if not exists foodhouse.foodhouses
(
    id          uuid primary key not null,
    name        varchar(100)     not null,
    description text
);

create table if not exists foodhouse.look_meta_features
(
    id       uuid primary key not null default uuid_generate_v4(),
    name     varchar(100)     not null unique,
    required bool             not null default false
);

create table if not exists foodhouse.look_meta
(
    id            uuid primary key not null default uuid_generate_v4(),
    foodhouse_id  uuid             not null references foodhouse.foodhouses (id),
    feature_id    uuid             not null references foodhouse.look_meta_features (id),
    feature_value varchar(255)     not null
);

create table if not exists foodhouse.areas
(
    id   uuid primary key not null default uuid_generate_v4(),
    name varchar(255)     not null unique
);

create table if not exists foodhouse.coworkers
(
    id           uuid primary key not null default uuid_generate_v4(),
    name_ref     varchar(255)     not null unique,
    full_name    varchar(255)     not null,
    area_id      uuid             not null references foodhouse.areas (id),
    foodhouse_id uuid             not null references foodhouse.foodhouses (id)
);

insert into foodhouse.look_meta_features(id, name, required)
VALUES (uuid_generate_v4(), 'logo_ref', false),
       (uuid_generate_v4(), 'brand_color', false),
       (uuid_generate_v4(), 'secondary_color', false)
on conflict (name) do nothing;

insert into foodhouse.areas(id, name)
values (uuid_generate_v4(), 'CASH_DESK'),
       (uuid_generate_v4(), 'KITCHEN'),
       (uuid_generate_v4(), 'MANAGER'),
       (uuid_generate_v4(), 'FOUNDER')
on conflict (name) do nothing;

delete from foodhouse.areas where name = 'FOUNDER';