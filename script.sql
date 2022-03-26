createdb deliveries;

create table couriers(
    id serial not null primary key,
    name varchar(150) not null,
    email varchar(150) not null unique,
    password varchar(8) not null
);