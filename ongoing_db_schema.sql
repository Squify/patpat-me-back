DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

create extension if not exists unaccent;

create or replace function remove_accents(field varchar)
	returns text
as
$BODY$
	select unaccent(field);
$BODY$
language sql
immutable;

CREATE TABLE user_gender
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE users
(
    id integer PRIMARY KEY NOT NULL,
    email character varying(256) NOT NULL,
    pseudo character varying(256) NOT NULL,
    password character varying(256) NOT NULL,
    name character varying(256) NOT NULL,
    surname character varying(256) NOT NULL,
    telephone character varying(10),
    birthday timestamp,
    sign_up timestamp NOT NULL,
    push_notification boolean,
    active_localisation boolean,
    display_real_name boolean,
    fk_id_gender integer REFERENCES user_gender(id)
);

CREATE TABLE follow_relation
(
    fk_id_follower integer REFERENCES users(id) NOT NULL,
    fk_id_user integer REFERENCES users(id) NOT NULL,
    PRIMARY KEY(fk_id_follower, fk_id_user)
);

CREATE TABLE animal_gender
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE animal_type
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE race
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL,
    fk_id_type integer REFERENCES animal_type(id)
);

CREATE TABLE animal
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL,
    birthday timestamp,
    fk_id_owner integer REFERENCES users(id),
    fk_id_gender integer REFERENCES animal_gender(id),
    fk_id_type integer REFERENCES animal_type(id),
    fk_id_race integer REFERENCES race(id)
);

CREATE TABLE event_type
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE event
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL,
    description character varying(256) NOT NULL,
    localisation character varying(256) NOT NULL,
    date timestamp,
    fk_id_type integer REFERENCES event_type (id)
);

CREATE TABLE event_member
(
    fk_id_member integer REFERENCES users(id) NOT NULL,
    fk_id_event integer REFERENCES event(id) NOT NULL,
    PRIMARY KEY(fk_id_member, fk_id_event)
);

CREATE TABLE tag
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE event_tags
(
    fk_id_tag integer REFERENCES tag(id) NOT NULL,
    fk_id_event integer REFERENCES event(id) NOT NULL,
    PRIMARY KEY(fk_id_tag, fk_id_event)
);