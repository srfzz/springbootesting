create table employees(
    id BIGINT generated always as identity primary key ,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(100) not null  unique,
    created_at TIMESTAMPTZ default current_timestamp
)