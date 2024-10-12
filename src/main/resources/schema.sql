create extension if not exists "uuid-ossp";

create table if not exists "restaurant" (
    id uuid not null constraint pk_restaurant primary key,
    town_hall text not null,
    province text not null,
    type text not null,
    name text not null,
    street text not null,
    city text not null,
    postal_code text not null,
    mail text,
    issuer text,
    phone text,
    fax text,
    latitude text not null,
    longitude text not null
);