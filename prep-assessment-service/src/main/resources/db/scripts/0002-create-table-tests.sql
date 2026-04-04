create table if not exists tests
(
    id          bigserial primary key,
    title       varchar(100) not null,
    description text,
    created_at  timestamptz  not null default now(),
    updated_at  timestamptz,

    constraint uq_tests_title unique (title)
);