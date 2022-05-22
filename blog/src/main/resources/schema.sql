drop table if exists genre;
create table genre
(
    id   IDENTITY primary key,
    name varchar(255) not null
);

drop table if exists author;
create table author
(
    id         IDENTITY primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null
);

drop table if exists post;
CREATE TABLE post
(
    id        IDENTITY primary key,
    title     varchar(255) not null,
    text      varchar(10000),
    image      varchar(255),
    author_id BIGINT,
    genre_id  BIGINT,
    foreign key (author_id) references author (id) on delete cascade,
    foreign key (genre_id) references genre (id) on delete cascade
);

drop table if exists reference_book;
CREATE TABLE reference_book
(
    id        IDENTITY primary key,
    title     varchar(255) not null,
    text      varchar(5000),
    author_id BIGINT,
    genre_id  BIGINT,
    foreign key (author_id) references author (id) on delete cascade,
    foreign key (genre_id) references genre (id) on delete cascade
);

drop table if exists user;
CREATE TABLE user
(
    id                      IDENTITY primary key,
    username                VARCHAR(50)  NOT NULL,
    password                VARCHAR(100) NOT NULL,
    full_name               VARCHAR(100),
    email                   VARCHAR(100),
    enabled                 boolean      NOT NULL DEFAULT true,
    account_non_expired     boolean      NOT NULL DEFAULT true,
    account_non_locked      boolean      NOT NULL DEFAULT true,
    credentials_non_expired boolean      NOT NULL DEFAULT true
);

drop table if exists authority;
CREATE TABLE authority
(
    id        IDENTITY primary key,
    user_id   bigint      NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE UNIQUE INDEX ix_auth_user
    on authority (user_id, authority);