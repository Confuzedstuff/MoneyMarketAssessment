
create table account_types
(
    id   integer not null
        constraint account_types_pk
            primary key,
    type varchar not null
        constraint account_types_pk_2
            unique
);

alter table account_types
    owner to postgres;

INSERT INTO public.account_types (id, type) VALUES (0, 'ZAR');
INSERT INTO public.account_types (id, type) VALUES (1, 'USD');
INSERT INTO public.account_types (id, type) VALUES (2, 'EUR');

create table transaction_types
(
    id   integer not null
        constraint transaction_types_pk
            primary key,
    type varchar not null
        constraint transaction_types_pk_2
            unique
);

alter table transaction_types
    owner to postgres;

INSERT INTO public.transaction_types (id, type) VALUES (1, 'Debit');
INSERT INTO public.transaction_types (id, type) VALUES (2, 'Credit');


create table users
(
    id        integer generated always as identity
        constraint users_pk_2
            primary key,
    user_name varchar not null
        constraint users_pk
            unique
);

alter table users
    owner to postgres;

INSERT INTO public.users (user_name) VALUES ('testuser');


create table accounts
(
    id              integer not null
        constraint accounts_pk
            primary key,
    account_type_id integer
        constraint accounts_account_types_id_fk
            references account_types,
    user_id         integer
        constraint accounts_users_id_fk
            references users
);

alter table accounts
    owner to postgres;

INSERT INTO public.accounts (id, account_type_id, user_id) VALUES (0, 0, 1);
INSERT INTO public.accounts (id, account_type_id, user_id) VALUES (1, 1, 1);
INSERT INTO public.accounts (id, account_type_id, user_id) VALUES (2, 2, 1);

create table transactions
(
    id                  integer generated always as identity
        constraint transactions_pk
            primary key,
    account_id          integer        not null
        constraint transactions_accounts_id_fk
            references accounts,
    amount              numeric(12, 4) not null,
    transaction_type_id integer        not null
        constraint transactions_transaction_types_id_fk
            references transaction_types
);

alter table transactions
    owner to postgres;

INSERT INTO public.transactions (account_id, amount, transaction_type_id) VALUES (0, 432.1230, 1);
INSERT INTO public.transactions (account_id, amount, transaction_type_id) VALUES (0, 123.1230, 2);
INSERT INTO public.transactions (account_id, amount, transaction_type_id) VALUES (0, 123.1230, 2);
INSERT INTO public.transactions (account_id, amount, transaction_type_id) VALUES (0, 1232.1230, 2);
INSERT INTO public.transactions (account_id, amount, transaction_type_id) VALUES (0, 123.1230, 2);
INSERT INTO public.transactions (account_id, amount, transaction_type_id) VALUES (0, 23.1100, 1);
INSERT INTO public.transactions (account_id, amount, transaction_type_id) VALUES (1, 23.1100, 2);



