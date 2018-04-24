create table users
(
  id bigint not null
    constraint users_pkey
    primary key,
  email varchar(255) not null,
  first_name varchar(255),
  last_name varchar(255),
  password varchar(255) not null
);

INSERT INTO users (id, email, first_name, last_name, password) VALUES (1, 'admin@test', 'admin', 'admin', 'admin');

