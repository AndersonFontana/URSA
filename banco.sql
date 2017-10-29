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
    INSERT INTO Oportunidade VALUES (codigo, codcargo, descricao, acesso, ingresso, fechada);
    INSERT INTO Cargo VALUES (codcargo, descricao, tipo);
    INSERT INTO Cargo VALUES (01, 'Teste 01', 7);
    INSERT INTO Cargo VALUES (02, 'Teste 02', 7);
    INSERT INTO Cargo VALUES (03, 'Teste 03', 6);
    INSERT INTO Cargo VALUES (04, 'Teste 04', 6);
    INSERT INTO Cargo VALUES (05, 'Teste 05', 5);
    INSERT INTO Cargo VALUES (06, 'Teste 06', 5);
    INSERT INTO Cargo VALUES (07, 'Teste 07', 4);
    INSERT INTO Cargo VALUES (08, 'Teste 08', 4);
    INSERT INTO Cargo VALUES (09, 'Teste 09', 3);
    INSERT INTO Cargo VALUES (10, 'Teste 10', 3);
    INSERT INTO Cargo VALUES (11, 'Teste 11', 2);
    INSERT INTO Cargo VALUES (12, 'Teste 12', 2);
    INSERT INTO Cargo VALUES (13, 'Teste 13', 1);
    INSERT INTO Cargo VALUES (14, 'Teste 14', 1);
*/
