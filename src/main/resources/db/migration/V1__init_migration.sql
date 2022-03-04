create table role
(
    id         bigserial
        primary key,
    created_at timestamp,
    updated_at timestamp,
    uuid       uuid,
    role_name  varchar(255)
);

create table subscription
(
    id                bigserial
        primary key,
    created_at        timestamp,
    updated_at        timestamp,
    uuid              uuid,
    subscription_type varchar(255)
);

create table wallet_user
(
    id              bigserial
        primary key,
    created_at      timestamp,
    updated_at      timestamp,
    uuid            uuid,
    email           varchar(255),
    name            varchar(255),
    password        varchar(255),
    role_id         bigint not null
        constraint FK_ROLE_ID
            references role,
    subscription_id bigint not null
        constraint FK_SUBSCRIPTION_ID
            references role
);


create table wallet
(
    id           bigserial
        primary key,
    created_at   timestamp,
    updated_at   timestamp,
    uuid         uuid,
    address      varchar(255) not null,
    balance      numeric(20, 8),
    coin_type    varchar(255),
    file_name    varchar(255),
    password     varchar(255),
    user_id      bigint       not null
        constraint FK_USER_ID
            references wallet_user
);



INSERT INTO role (id, created_at, uuid, role_name)
VALUES (1, CURRENT_TIMESTAMP, uuid_generate_v4(), 'ROLE_USER'),
       (2, CURRENT_TIMESTAMP, uuid_generate_v4(), 'ROLE_ADMIN'),
       (3, CURRENT_TIMESTAMP, uuid_generate_v4(), 'ROLE_MANAGER');

INSERT INTO subscription (id, created_at, uuid, subscription_type)
VALUES (1, CURRENT_TIMESTAMP, uuid_generate_v4(), 'TRIAL'),
       (2, CURRENT_TIMESTAMP, uuid_generate_v4(), 'SILVER'),
       (3, CURRENT_TIMESTAMP, uuid_generate_v4(), 'DIAMOND');

