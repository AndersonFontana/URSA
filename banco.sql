/*
    Host:       ec2-184-73-189-190.compute-1.amazonaws.com
    Database:   df4dv32d0ha20c
    User:       jgnctpthhqngqk
    Port:       5432
    Password:   6a4f0f2a99e6d6109658af60edced243a6fa8c2ff9eac54d474be2cc584303bd
*/

CREATE TABLE Cargo (
    codcargo    integer        NOT NULL,
    descricao   varchar(100)   NOT NULL,
    tipo        integer        NOT NULL,
    primary key (codcargo)
);

CREATE TABLE Oportunidade (
    codigo      integer        NOT NULL,
    codcargo    integer        NOT NULL,
    descricao   varchar(4000)  NOT NULL,
    acesso      integer        NOT NULL,
    ingresso    timestamp      NOT NULL,
    fechada     timestamp      NOT NULL,
    primary key (codigo),
    foreign key (codcargo) references Cargo
);

/*
    INSERT INTO Cargo VALUES (codcargo, descricao, tipo);
    INSERT INTO Oportunidade VALUES (codigo, codcargo, descricao, acesso, ingresso, fechada);
*/
