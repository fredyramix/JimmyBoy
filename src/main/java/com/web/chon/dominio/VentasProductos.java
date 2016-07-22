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
public class VentasProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idVentasProductosPk;
    private BigDecimal idVentasFk;
    private String idProductoFk;
    private BigDecimal cantidad;
    private String observaciones;
    private BigDecimal precioVenta;
    private BigDecimal totalProducto;
    private String nombreProducto;
    private int  count;
    private BigDecimal estatus;
    private String idCategoria;

    public void reset() {
        idVentasProductosPk = null;
        idVentasFk = null;
        idProductoFk = null;
        cantidad = null;
        observaciones = null;
        precioVenta = null;
        totalProducto = null;
        nombreProducto=null;
        idCategoria=null;
    }

    @Override
    public String toString() {
        return "VentasProductos{" + "idVentasProductosPk=" + idVentasProductosPk + ", idVentasFk=" + idVentasFk + ", idProductoFk=" + idProductoFk + ", cantidad=" + cantidad + ", observaciones=" + observaciones + ", precioVenta=" + precioVenta + ", totalProducto=" + totalProducto + ", nombreProducto=" + nombreProducto + ", count=" + count + ", estatus=" + estatus + ", idCategoria=" + idCategoria + '}';
    }
    

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    
    public BigDecimal getEstatus() {
        return estatus;
    }

    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }
    

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    
    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

   
    public BigDecimal getIdVentasProductosPk() {
        return idVentasProductosPk;
    }

    public void setIdVentasProductosPk(BigDecimal idVentasProductosPk) {
        this.idVentasProductosPk = idVentasProductosPk;
    }

    public BigDecimal getIdVentasFk() {
        return idVentasFk;
    }

    public void setIdVentasFk(BigDecimal idVentasFk) {
        this.idVentasFk = idVentasFk;
    }

    public String getIdProductoFk() {
        return idProductoFk;
    }

    public void setIdProductoFk(String idProductoFk) {
        this.idProductoFk = idProductoFk;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getTotalProducto() {
        return totalProducto;
    }

    public void setTotalProducto(BigDecimal totalProducto) {
        this.totalProducto = totalProducto;
    }

}
