createdb deliveries;

create table delivery_person(
    id serial not null primary key,
    name varchar(150) not null,
    email varchar(150) not null unique,
    password varchar(8) not null
);

alter table couriers rename to delivery_person;

create table customers(
	id serial not null primary key,
	name varchar(120),
	address varchar(150) not null
);

create table orders(
	id serial not null primary key,
	order_date timestamp not null,
	total double precision not null,
	status varchar(15) not null,
	customer integer not null,
	delivery_person integer
	
	constraint fk_customer foreign key(customer) references customers(id),
	constraint fk_delivery_person foreign key(delivery_person) references delivery_person(id)
);

create table geolocalization(
	id serial not null primary key,
	geolocalization_timestamp timestamp not null,
	latitude double precision not null,
	longitude double precision not null,
	geolocalization_order integer not null,
	delivery_person integer
	
	constraint fk_geolocalization_order foreign key(geolocalization_order) references orders(id)
);