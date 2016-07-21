/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  freddy
 * Created: 15/07/2016
 */
CREATE TABLE CATEGORIAS(
    ID_CATEGORIA_PK VARCHAR(4) NOT NULL,
    NOMBRE_CATEGORIA VARCHAR(30) NOT NULL,
    DESCRIPCION_CATEGORIA VARCHAR(100),
    CONSTRAINT C_C_ID_CATEGORIA_PK PRIMARY KEY(ID_CATEGORIA_PK)
);

CREATE SEQUENCE s_CATEGORIAS
INCREMENT BY 1
START WITH 1
MINVALUE 0;

CREATE TABLE PRODUCTOS(
    ID_PRODUCTO_PK VARCHAR(8) NOT NULL,
    NOMBRE_PRODUCTO VARCHAR(100) NOT NULL,
    DESCRIPCION_PRODUCTO VARCHAR(300),
    ID_CATEGORIA_FK VARCHAR(4),
    ESTATUS NUMBER,
    URL_IMAGEN VARCHAR(255),
    CONSTRAINT C_P_ID_PRODUCTO_PK PRIMARY KEY(ID_PRODUCTO_PK),
    CONSTRAINT C_P_ID_CATEGORIA_FK FOREIGN KEY(ID_CATEGORIA_FK) REFERENCES CATEGORIAS(ID_CATEGORIA_PK)
);

CREATE SEQUENCE s_PRODUCTOS
INCREMENT BY 1
START WITH 1
MINVALUE 0;