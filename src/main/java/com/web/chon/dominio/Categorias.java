/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.dominio;

import java.io.Serializable;

/**
 *
 * @author freddy
 */
public class Categorias implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idCategorias;
    private String nombreCategoria;
    private String descripcionCategoria;

    @Override
    public String toString() {
        return "Categorias{" + "idCategorias=" + idCategorias + ", nombreCategoria=" + nombreCategoria + ", descripcionCategoria=" + descripcionCategoria + '}';
    }

    public String getIdCategorias() {
        return idCategorias;
    }

    public void setIdCategorias(String idCategorias) {
        this.idCategorias = idCategorias;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
    
    
    
}
