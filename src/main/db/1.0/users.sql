CREATE TABLE users
(
    LOGIN    VARCHAR(70) NOT NULL PRIMARY KEY,
    NAME     VARCHAR(30) NOT NULL UNIQUE,
    PASSWORD VARCHAR(30) NOT NULL
);