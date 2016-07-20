/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.dominio;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author freddy
 */
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idProductoPk;
    private String nombreProducto;
    private String idCategoriaFk;
    private BigDecimal estatus;
    private String nombreCategoria;
    private String descripcionProducto;
    private String nombreEstatus;
    private String urlImagen;
    private BigDecimal precio;

    @Override
    public String toString() {
        return "Productos{" + "idProductoPk=" + idProductoPk + ", nombreProducto=" + nombreProducto + ", idCategoriaFk=" + idCategoriaFk + ", estatus=" + estatus + ", nombreCategoria=" + nombreCategoria + ", descripcionProducto=" + descripcionProducto + ", nombreEstatus=" + nombreEstatus + ", urlImagen=" + urlImagen + ", precio=" + precio + '}';
    }
    


    public void reset() {
        idProductoPk = null;
        nombreProducto = null;
        idCategoriaFk = null;
        estatus = null;
        nombreCategoria = null;
        descripcionProducto = null;
        urlImagen =null;
        nombreEstatus = null;
        precio = null;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    
    public String getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(String nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }

    
    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    
    public String getIdProductoPk() {
        return idProductoPk;
    }

    public void setIdProductoPk(String idProductoPk) {
        this.idProductoPk = idProductoPk;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getIdCategoriaFk() {
        return idCategoriaFk;
    }

    public void setIdCategoriaFk(String idCategoriaFk) {
        this.idCategoriaFk = idCategoriaFk;
    }

   

    public BigDecimal getEstatus() {
        return estatus;
    }

    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

}
