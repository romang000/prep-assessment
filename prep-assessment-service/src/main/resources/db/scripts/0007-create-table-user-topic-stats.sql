create table if not exists user_topic_stats
(
    id               bigserial primary key,
    user_id          bigint        not null,
    topic            varchar(100)  not null,
    subtopic         varchar(100)  not null,
    total_answered   int           not null,
    correct_count    int           not null,
    incorrect_count  int           not null,
    accuracy         numeric(5, 2) not null,
    last_answered_at timestamptz,
    created_at       timestamptz   not null default now(),
    updated_at       timestamptz,

    constraint uq_user_topic unique (user_id, topic, subtopic)
);