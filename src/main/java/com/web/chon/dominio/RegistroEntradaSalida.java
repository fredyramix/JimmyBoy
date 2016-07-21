/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author marcogante
 */
public class RegistroEntradaSalida implements Serializable{
    private static final long serialVersionUID = 1L;
    private BigDecimal idRegEntSalPk;
    private Date fechaEntrada;
    private Date fechaSalida;
    private double latitudEntrada;
    private double latitudSalida;
    private double longitudEntrada;
    private double longitudSalida;
    private BigDecimal idUsuarioFk;
    private BigDecimal idSucursalFk;
    private Date fechaFiltroInicio;
    private Date fechaFiltroFin;
    private String nombre;
    private String apPaterno;
    private String apMaterno;

    @Override
    public String toString() {
        return "RegistroEntradaSalida{" + "idRegEntSalPk=" + idRegEntSalPk + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", latitudEntrada=" + latitudEntrada + ", latitudSalida=" + latitudSalida + ", longitudEntrada=" + longitudEntrada + ", longitudSalida=" + longitudSalida + ", idUsuarioFk=" + idUsuarioFk + ", idSucursalFk=" + idSucursalFk + ", fechaFiltroInicio=" + fechaFiltroInicio + ", fechaFiltroFin=" + fechaFiltroFin + ", nombre=" + nombre + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno + '}';
    }

    
    public BigDecimal getIdRegEntSalPk() {
        return idRegEntSalPk;
    }

    public void setIdRegEntSalPk(BigDecimal idRegEntSalPk) {
        this.idRegEntSalPk = idRegEntSalPk;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getLatitudEntrada() {
        return latitudEntrada;
    }

    public void setLatitudEntrada(double latitudEntrada) {
        this.latitudEntrada = latitudEntrada;
    }

    public double getLatitudSalida() {
        return latitudSalida;
    }

    public void setLatitudSalida(double latitudSalida) {
        this.latitudSalida = latitudSalida;
    }

    public double getLongitudEntrada() {
        return longitudEntrada;
    }

    public void setLongitudEntrada(double longitudEntrada) {
        this.longitudEntrada = longitudEntrada;
    }

    public double getLongitudSalida() {
        return longitudSalida;
    }

    public void setLongitudSalida(double longitudSalida) {
        this.longitudSalida = longitudSalida;
    }

    public BigDecimal getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(BigDecimal idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public BigDecimal getIdSucursalFk() {
        return idSucursalFk;
    }

    public void setIdSucursalFk(BigDecimal idSucursalFk) {
        this.idSucursalFk = idSucursalFk;
    }

    public Date getFechaFiltroInicio() {
        return fechaFiltroInicio;
    }

    public void setFechaFiltroInicio(Date fechaFiltroInicio) {
        this.fechaFiltroInicio = fechaFiltroInicio;
    }

    public Date getFechaFiltroFin() {
        return fechaFiltroFin;
    }

    public void setFechaFiltroFin(Date fechaFiltroFin) {
        this.fechaFiltroFin = fechaFiltroFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    
   
    
}
