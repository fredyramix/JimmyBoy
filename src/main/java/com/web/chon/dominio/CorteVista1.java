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
public class CorteVista1 implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idProducto;
    private String nombreProducto;
    private BigDecimal cantidad;
    private BigDecimal total;

    public void reset() {
        idProducto = null;
        nombreProducto = null;
        cantidad = null;
        total = null;
    }

    @Override
    public String toString() {
        return "CorteVista1{" + "idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", cantidad=" + cantidad + ", total=" + total + '}';
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
