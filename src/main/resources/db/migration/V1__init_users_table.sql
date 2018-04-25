create table IF NOT EXISTS users
(
  user_id varchar(255) not null
    constraint users_pkey
    primary key,
  email varchar(255),
  first_name varchar(255),
  is_active boolean not null,
  last_name varchar(255),
  password varchar(255),
  role varchar(255)
);
INSERT INTO users (user_id, email, first_name, is_active, last_name, password, role)
VALUES
('admin', 'admin@admin', 'Ad', TRUE, 'Min', 'admin', 'ADMIN' ),
  ('user', 'user@user', 'Us', TRUE, 'Er', 'user', 'USER');

