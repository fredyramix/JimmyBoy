/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author freddy
 */
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idVentaPk;
    private BigDecimal numeroMesa;
    private BigDecimal estatusVenta;
    private BigDecimal cantidadPersonas;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal idMeseroFk;
    private BigDecimal folio;
    private BigDecimal idSucursalFk;
    private BigDecimal total;
    private String nombreMesero;
    private String apPaMesero;
    private String apMaMesero;
    private String nombreEstatus;
    private ArrayList<VentasProductos> listaProductos;

    @Override
    public String toString() {
        return "Ventas{" + "idVentaPk=" + idVentaPk + ", numeroMesa=" + numeroMesa + ", estatusVenta=" + estatusVenta + ", cantidadPersonas=" + cantidadPersonas + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", idMeseroFk=" + idMeseroFk + ", folio=" + folio + ", idSucursalFk=" + idSucursalFk + ", total=" + total + ", nombreMesero=" + nombreMesero + ", apPaMesero=" + apPaMesero + ", apMaMesero=" + apMaMesero + ", nombreEstatus=" + nombreEstatus + '}';
    }

    
    
    public void reset() {
        idVentaPk = null;
        numeroMesa = null;
        estatusVenta = null;
        cantidadPersonas = null;
        fechaInicio = null;
        fechaFin = null;
        idMeseroFk = null;
        folio = null;
        idSucursalFk = null;
        total = null;
        nombreMesero = null;
        apPaMesero = null;
        nombreEstatus=null;
        apMaMesero = null;
    }

    public String getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(String nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }

   

    public String getNombreMesero() {
        return nombreMesero;
    }

    public void setNombreMesero(String nombreMesero) {
        this.nombreMesero = nombreMesero;
    }

    public String getApPaMesero() {
        return apPaMesero;
    }

    public void setApPaMesero(String apPaMesero) {
        this.apPaMesero = apPaMesero;
    }

    public String getApMaMesero() {
        return apMaMesero;
    }

    public void setApMaMesero(String apMaMesero) {
        this.apMaMesero = apMaMesero;
    }

    
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getIdSucursalFk() {
        return idSucursalFk;
    }

    public void setIdSucursalFk(BigDecimal idSucursalFk) {
        this.idSucursalFk = idSucursalFk;
    }

    public BigDecimal getIdVentaPk() {
        return idVentaPk;
    }

    public void setIdVentaPk(BigDecimal idVentaPk) {
        this.idVentaPk = idVentaPk;
    }

    public BigDecimal getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(BigDecimal numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public BigDecimal getEstatusVenta() {
        return estatusVenta;
    }

    public void setEstatusVenta(BigDecimal estatusVenta) {
        this.estatusVenta = estatusVenta;
    }

    public BigDecimal getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(BigDecimal cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getIdMeseroFk() {
        return idMeseroFk;
    }

    public void setIdMeseroFk(BigDecimal idMeseroFk) {
        this.idMeseroFk = idMeseroFk;
    }

    public BigDecimal getFolio() {
        return folio;
    }

    public void setFolio(BigDecimal folio) {
        this.folio = folio;
    }

    public ArrayList<VentasProductos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<VentasProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
}
