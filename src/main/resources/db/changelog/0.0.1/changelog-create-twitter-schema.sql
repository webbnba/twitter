--liquibase formatted sql

--changeset ValeryBezborodov:create-twitter-schema
--comment create new schema
create schema twitter;
--rollback drop schema twitter;