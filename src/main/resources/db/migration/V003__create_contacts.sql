create table contacts
(
    id      uuid default gen_random_uuid() primary key,
    type    varchar(128) not null,
    value   varchar(256),
    user_id uuid
        constraint contacts_user_id_fkey references users
);