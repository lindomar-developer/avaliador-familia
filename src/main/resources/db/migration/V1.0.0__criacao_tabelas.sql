CREATE TABLE FAMILIA (
    ID              int8        AUTO_INCREMENT        not null,
    RENDA_TOTAL     numeric(19,2)       not null,
    CONSTRAINT FAMILIA_PK PRIMARY KEY (ID)
);
CREATE SEQUENCE FAMILIA_SEQ;

CREATE TABLE DEPENDENTE (
    ID              int8            AUTO_INCREMENT        not null,
    NOME            varchar(50)             not null,
    IDADE           int4                    not null,
    ID_FAMILIA      int8                    not null,
    CONSTRAINT DEPENDENTE_PK PRIMARY KEY (ID),
    CONSTRAINT FK_DEPENDENTE_FAMILIA FOREIGN KEY (ID_FAMILIA) REFERENCES FAMILIA(ID)
);
CREATE SEQUENCE DEPENDENTE_SEQ;