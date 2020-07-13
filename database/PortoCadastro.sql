create database portocadastro;

use portocadastro;

create table user(
iduser int PRIMARY KEY,
usuario VARCHAR(20) not null unique,
senha VARCHAR(10) not null unique
);

describe Associados;

create table Associados(
idassoc int primary key auto_increment,
nome VARCHAR(50) not null,
datanasc date not null,
estciv VARCHAR(20),
prof VARCHAR(20),
nacionalidade VARCHAR(20),
naturalidade VARCHAR(20),
sbleresc VARCHAR(3),
filiacao VARCHAR(30),
dataadm VARCHAR(10) not null,
resid VARCHAR(30),
cartprof VARCHAR(50),
marit VARCHAR(30),
inps VARCHAR(30),
img longblob NOT NULL
);

INSERT into user(iduser,usuario,senha) 
values(1,'geraldolima','melissa1');

SELECT * from user where usuario='geraldolima' and senha='melissa1';

SELECT * from Associados;

drop table Associados;