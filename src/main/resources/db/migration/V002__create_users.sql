create table users
(
    id   uuid default gen_random_uuid() primary key,
    name varchar(512)
);