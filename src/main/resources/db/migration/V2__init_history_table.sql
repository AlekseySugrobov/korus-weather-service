create table history_record
(
  id bigserial not null
    constraint history_record_pkey
    primary key,
  action_date timestamp,
  description varchar(255),
  user_action varchar(255),
  user_id varchar(255)
    constraint fk_history_users
    references users
)
;



