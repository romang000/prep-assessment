create table if not exists questions
(
    id               bigserial primary key,
    test_id          bigint       not null,
    topic            varchar(100) not null,
    subtopic         varchar(100) not null,
    difficulty varchar(10) not null check (difficulty in ('EASY', 'MEDIUM', 'HARD')),
    wording_question text         not null,
    serial_number    int          not null,
    created_at       timestamptz  not null default now(),
    updated_at       timestamptz,

    constraint fk_questions_tests_id foreign key (test_id) references tests (id)

);