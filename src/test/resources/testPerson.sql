DROP TABLE IF EXISTS Person;
CREATE table Person (
    id integer AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) null,
    surname varchar(50) null,
    gender varchar(10) null
);
