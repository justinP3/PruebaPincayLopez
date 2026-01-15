-- database: ..\LPDatabases\LPAntFood.sqlite
DROP TABLE IF EXISTS LPHormiga;

DROP TABLE IF EXISTS LPSexo;

DROP TABLE IF EXISTS LPEstado;

DROP TABLE IF EXISTS LPHormigaTipo;

DROP TABLE IF EXISTS LPAlimento;

DROP TABLE IF EXISTS LPAlimentoTipo;

CREATE TABLE
    LPAlimentoTipo (
        IdAlimentoTipo INTEGER PRIMARY KEY AUTOINCREMENT,
        Nombre VARCHAR(15) NOT NULL UNIQUE,
        FechaCreacion DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime')),
        FechaModifica DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime'))
    );

CREATE TABLE
    LPAlimento (
        IdAlimento INTEGER PRIMARY KEY AUTOINCREMENT,
        IdAlimentoTipo INTEGER NOT NULL REFERENCES LPAlimentoTipo (IdAlimentoTipo),
        Nombre VARCHAR(15) NOT NULL UNIQUE,
        FechaCreacion DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime')),
        FechaModifica DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime'))
    );

CREATE TABLE
    LPHormigaTipo (
        IdHormigaTipo INTEGER PRIMARY KEY AUTOINCREMENT,
        Nombre VARCHAR(15) NOT NULL UNIQUE,
        Estado VARCHAR(1) NOT NULL DEFAULT 'A',
        FechaCreacion DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime')),
        FechaModifica DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime'))
    );

CREATE TABLE
    LPEstado (
        IdEstado INTEGER PRIMARY KEY AUTOINCREMENT,
        Nombre VARCHAR(15) NOT NULL UNIQUE,
        Descripcion VARCHAR(100) NULL,
        FechaCreacion DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime')),
        FechaModifica DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime'))
    );

CREATE TABLE
    LPSexo (
        IdSexo INTEGER PRIMARY KEY AUTOINCREMENT,
        Nombre VARCHAR(15) NOT NULL UNIQUE,
        FechaCreacion DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime')),
        FechaModifica DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime'))
    );

CREATE TABLE
    LPHormiga (
        IdHormiga INTEGER PRIMARY KEY AUTOINCREMENT,
        IdHormigaTipo INTEGER NOT NULL REFERENCES LPHormigaTipo (IdHormigaTipo),
        IdSexo INTEGER NOT NULL REFERENCES LPSexo (IdSexo),
        IdEstado INTEGER NOT NULL REFERENCES LPEstado (IdEstado),
        Nombre VARCHAR(20) NOT NULL UNIQUE,
        Estado VARCHAR(1) NOT NULL DEFAULT 'A',
        FechaCreacion DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime')),
        FechaModifica DATETIME NOT NULL DEFAULT (datetime ('now', 'localtime'))
    );

INSERT INTO
    LPSexo (Nombre)
VALUES
    ('Macho'),
    ('Hembra'),
    ('Asexual'),
    ('Nulo');

INSERT INTO
    LPAlimentoTipo (Nombre)
VALUES
    ('Nectarivoro'),
    ('Carnivoro'),
    ('Omnívoro'),
    ('Insectivoro'),
    ('Herbivoro');

INSERT INTO
    LPHormigaTipo (Nombre)
VALUES
    ('HLarva'),
    ('HSoldado'),
    ('HZángano'),
    ('HRastreeadora'),
    ('HReina'),
    ('HObrera');

INSERT INTO
    LPEstado (Nombre)
VALUES
    ('Vive'),
    ('Muere');

DROP VIEW IF EXISTS vwHormiga;

CREATE VIEW
    vwHormiga AS
SELECT
    H.IdHormiga,
    HT.Nombre AS Tipo,
    S.Nombre AS Sexo,
    E.Nombre AS EstadoHormiga,
    H.Nombre AS Nombre,
    H.Estado,
    H.FechaCreacion,
    H.FechaModifica
FROM
    Hormiga H
    JOIN HormigaTipo HT ON H.IdHormigaTipo = HT.IdHormigaTipo
    JOIN Sexo S ON H.IdSexo = S.IdSexo
    JOIN Estado E ON H.IdEstado = E.IdEstado
WHERE
    H.Estado = 'A';

SELECT
    *
FROM
    vwHormiga;

SELECT
    IdHormiga,
    Tipo,
    Sexo,
    EstadoHormiga,
    Nombre,
    Estado,
    FechaCreacion,
    FechaModifica
FROM
    vwHormiga;

SELECT
    *
FROM
    Hormiga;

UPDATE Estado
SET
    Nombre = 'VIVA'
WHERE
    IdEstado = 1;

UPDATE Estado
SET
    Nombre = 'MUERTA'
WHERE
    IdEstado = 2;

UPDATE Estado
SET
    Estado = 'X'
WHERE
    IdEstado = 3;

select
    *
from
    Estado;