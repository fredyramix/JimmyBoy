package com.web.chon.dominio;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Juan
 */
public class Credito extends ValueObject {

    private BigDecimal idCreditoPk;
    private BigDecimal idClienteFk;
    private BigDecimal idVentaMenudeo;
    private BigDecimal idVentaMayoreo;
    private BigDecimal idUsuarioCredito;
    private BigDecimal idTipoCreditoFk;
    private BigDecimal estatusCredito;
    private BigDecimal numeroPromesaPago;
    private Date fechaInicioCredito;
    private Date fechaFinCredito;
    private Date fechaPromesaPago;
    private BigDecimal tazaInteres;
    private BigDecimal plasos;

    @Override
    public String toString() {
        return "Credito{" + "idCreditoPk=" + idCreditoPk + ", idClienteFk=" + idClienteFk + ", idVentaMenudeo=" + idVentaMenudeo + ", idVentaMayoreo=" + idVentaMayoreo + ", idUsuarioCredito=" + idUsuarioCredito + ", idTipoCreditoFk=" + idTipoCreditoFk + ", estatusCredito=" + estatusCredito + ", numeroPromesaPago=" + numeroPromesaPago + ", fechaInicioCredito=" + fechaInicioCredito + ", fechaFinCredito=" + fechaFinCredito + ", fechaPromesaPago=" + fechaPromesaPago + ", tazaInteres=" + tazaInteres + '}';
    }

    public BigDecimal getIdCreditoPk() {
        return idCreditoPk;
    }

    public void setIdCreditoPk(BigDecimal idCreditoPk) {
        this.idCreditoPk = idCreditoPk;
    }

    public BigDecimal getIdClienteFk() {
        return idClienteFk;
    }

    public void setIdClienteFk(BigDecimal idClienteFk) {
        this.idClienteFk = idClienteFk;
    }

    public BigDecimal getIdVentaMenudeo() {
        return idVentaMenudeo;
    }

    public void setIdVentaMenudeo(BigDecimal idVentaMenudeo) {
        this.idVentaMenudeo = idVentaMenudeo;
    }

    public BigDecimal getIdVentaMayoreo() {
        return idVentaMayoreo;
    }

    public void setIdVentaMayoreo(BigDecimal idVentaMayoreo) {
        this.idVentaMayoreo = idVentaMayoreo;
    }

    public BigDecimal getIdUsuarioCredito() {
        return idUsuarioCredito;
    }

    public void setIdUsuarioCredito(BigDecimal idUsuarioCredito) {
        this.idUsuarioCredito = idUsuarioCredito;
    }

    public BigDecimal getIdTipoCreditoFk() {
        return idTipoCreditoFk;
    }

    public void setIdTipoCreditoFk(BigDecimal idTipoCreditoFk) {
        this.idTipoCreditoFk = idTipoCreditoFk;
    }

    public BigDecimal getNumeroPromesaPago() {
        return numeroPromesaPago;
    }

    public void setNumeroPromesaPago(BigDecimal numeroPromesaPago) {
        this.numeroPromesaPago = numeroPromesaPago;
    }

    public Date getFechaInicioCredito() {
        return fechaInicioCredito;
    }

    public void setFechaInicioCredito(Date fechaInicioCredito) {
        this.fechaInicioCredito = fechaInicioCredito;
    }

    public Date getFechaFinCredito() {
        return fechaFinCredito;
    }

    public void setFechaFinCredito(Date fechaFinCredito) {
        this.fechaFinCredito = fechaFinCredito;
    }

    public Date getFechaPromesaPago() {
        return fechaPromesaPago;
    }

    public void setFechaPromesaPago(Date fechaPromesaPago) {
        this.fechaPromesaPago = fechaPromesaPago;
    }

    public BigDecimal getTazaInteres() {
        return tazaInteres;
    }

    public void setTazaInteres(BigDecimal tazaInteres) {
        this.tazaInteres = tazaInteres;
    }

    public BigDecimal getEstatusCredito() {
        return estatusCredito;
    }

    public void setEstatusCredito(BigDecimal estatusCredito) {
        this.estatusCredito = estatusCredito;
    }

    public BigDecimal getPlasos() {
        return plasos;
    }

    public void setPlasos(BigDecimal plasos) {
        this.plasos = plasos;
    }
    

    @Override
    public void reset() {
        idCreditoPk = null;
        idClienteFk = null;
        idVentaMenudeo = null;
        idVentaMayoreo = null;
        idUsuarioCredito = null;
        idTipoCreditoFk = null;
        estatusCredito = null;
        numeroPromesaPago = null;
        fechaInicioCredito = null;
        fechaFinCredito = null;
        fechaPromesaPago = null;
        tazaInteres = null;
        plasos = null;
    }

}
