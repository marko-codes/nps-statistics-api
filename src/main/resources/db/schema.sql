

CREATE TABLE Survey (
    id integer not null auto_increment,
    country varchar(100) not null,
    start_date DATE not null,
    answer_date DATE not null,
    rating int not null,
    improvement varchar(255),
    primary key (id)
);
CREATE TABLE Quarterly (
    id integer not null auto_increment,
    quarter varchar(100) not null,
    detractors float not null,
    passives float not null,
    promoters float not null,
    score integer not null,
    responses integer not null,
    primary key (id)
);
CREATE TABLE Countryq (
    id integer not null auto_increment,
    country varchar(100) not null,
    quarter varchar(100) not null,
    detractors float not null,
    passives float not null,
    promoters float not null,
    score integer not null,
    responses integer not null,
    primary key (id)
);
CREATE TABLE Quarter (
    id integer not null auto_increment,
    quarter varchar(100) not null,
    primary key (id)
);
CREATE TABLE Country (
    id integer not null auto_increment,
    name varchar(100) not null,
    primary key (id)
);
CREATE TABLE Improvement (
    id integer not null auto_increment,
    question varchar(255) not null,
    quarter varchar(100) not null,
    percentage varchar(100) not null,
    primary key (id)
);
CREATE TABLE Question (
    id integer not null auto_increment,
    question varchar(255) not null,
    primary key (id)
);