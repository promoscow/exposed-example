create table contacts
(
    id      uuid default uuid_generate_v4() primary key,
    type    varchar(128) not null,
    value   varchar(256),
    user_id uuid
        constraint contacts_user_id_fkey references users
);