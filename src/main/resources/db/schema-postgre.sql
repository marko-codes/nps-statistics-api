DROP TABLE IF EXISTS Survey;
DROP TABLE IF EXISTS Quarterly;
DROP TABLE IF EXISTS Countryq;
DROP TABLE IF EXISTS Country;
DROP TABLE IF EXISTS Quarter;
DROP TABLE IF EXISTS Improvement;
DROP TABLE IF EXISTS Question;

CREATE TABLE Survey (
  id SERIAL PRIMARY KEY,
  country varchar(100) not null,
  start_date DATE not null,
  answer_date DATE not null,
  rating int not null,
  improvement varchar(255)
);

CREATE TABLE Quarterly (
    id SERIAL PRIMARY KEY,
    quarter varchar(100) not null,
    detractors float not null,
    passives float not null,
    promoters float not null,
    score integer not null,
    responses integer not null
);
CREATE TABLE Countryq (
id SERIAL PRIMARY KEY,
    country varchar(100) not null,
    quarter varchar(100) not null,
    detractors float not null,
    passives float not null,
    promoters float not null,
    score integer not null,
    responses integer not null

);
CREATE TABLE Quarter (
id SERIAL PRIMARY KEY,
    quarter varchar(100) not null

);
CREATE TABLE Country (
id SERIAL PRIMARY KEY,
    name varchar(100) not null

);
CREATE TABLE Improvement (
id SERIAL PRIMARY KEY,
    question varchar(255) not null,
    quarter varchar(100) not null,
    percentage varchar(100) not null
);
CREATE TABLE Question (
id SERIAL PRIMARY KEY,
    question varchar(255) not null
);