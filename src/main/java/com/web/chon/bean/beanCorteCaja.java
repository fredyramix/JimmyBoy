/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.bean;

import com.web.chon.dominio.CorteVista1;
import com.web.chon.service.IfaceVentas;
import com.web.chon.util.JsfUtil;
import com.web.chon.util.TiempoUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author freddy
 */
@Component
@Scope("view")

public class beanCorteCaja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IfaceVentas ifaceVentas;

    private String title = "";
    private String viewEstate = "";

    private Date fechaFin;
    private Date fechaInicio;
    private Date fechaFiltroFin;
    private Date fechaFiltroInicio;
    private int filtro;
    private int status;
    private Date hoy;
    private boolean enableCalendar;
    private BigDecimal totalVista1;
    private BigDecimal totalCategorias;
    private BigDecimal totalMeseros;
    private ArrayList<CorteVista1> vista1;
    private ArrayList<CorteVista1> vistaCategorias;
    private ArrayList<CorteVista1> vistaMeseros;

    @PostConstruct
    public void init() {
        status = 2;
        setTitle("Corte de Caja");
        setViewEstate("init");
        hoy = new Date();
        vista1 = new ArrayList<CorteVista1>();
        vistaCategorias = new ArrayList<CorteVista1>();
        vistaMeseros = new ArrayList<CorteVista1>();
    }
    public void imprimir()
    {
        
    }

    public void buscar() {

        totalVista1 = new BigDecimal(0);
        totalCategorias = new BigDecimal(0);
        totalMeseros = new BigDecimal(0);
        if (getFechaFiltroInicio() == null || getFechaFiltroFin() == null) {
            JsfUtil.addErrorMessageClean("Favor de ingresar un rango de fechas");
        } else {
            System.out.println("Fecha Inicio: " + fechaFiltroInicio);
            System.out.println("Fecha Inicio: " + fechaFiltroFin);
            vista1 = ifaceVentas.getVentasCorte1(fechaFiltroInicio, fechaFiltroFin);
            vistaCategorias = ifaceVentas.getVentasCategorias(fechaFiltroInicio, fechaFiltroFin);
            vistaMeseros = ifaceVentas.getVentasMeseros(fechaFiltroInicio, fechaFiltroFin);
            getTotalVistaProductos();
            getTotalVistaCategorias();
            getTotalVistaMeseros();

        }

    }

    public void getTotalVistaProductos() {
        for (CorteVista1 v : vista1) {
            totalVista1 = totalVista1.add(v.getTotal(), MathContext.UNLIMITED);
        }
    }

    public void getTotalVistaCategorias() {
        for (CorteVista1 v : vistaCategorias) {
            totalCategorias = totalCategorias.add(v.getTotal(), MathContext.UNLIMITED);
        }
    }

    public void getTotalVistaMeseros() {
        for (CorteVista1 v : vistaMeseros) {
            totalMeseros = totalMeseros.add(v.getTotal(), MathContext.UNLIMITED);
        }
    }

    public void verificarCombo() {
        if (filtro == -1) {
            //se habilitan los calendarios.
            setFechaFiltroInicio(null);
            setFechaFiltroFin(null);
            enableCalendar = false;
        } else {
            switch (filtro) {
                case 1:
                    setFechaFiltroInicio(new Date());
                    setFechaFiltroFin(new Date());
                    break;

                case 2:
                    setFechaFiltroInicio(TiempoUtil.getDayOneOfMonth(new Date()));
                    setFechaFiltroFin(TiempoUtil.getDayEndOfMonth(new Date()));

                    break;
                case 3:
                    setFechaFiltroInicio(TiempoUtil.getDayOneYear(new Date()));
                    setFechaFiltroFin(TiempoUtil.getDayEndYear(new Date()));
                    break;
                default:
                    setFechaFiltroInicio(null);
                    setFechaFiltroFin(null);
                    break;
            }
            enableCalendar = true;
        }
    }

    public ArrayList<CorteVista1> getVistaCategorias() {
        return vistaCategorias;
    }

    public void setVistaCategorias(ArrayList<CorteVista1> vistaCategorias) {
        this.vistaCategorias = vistaCategorias;
    }

    public BigDecimal getTotalVista1() {
        return totalVista1;
    }

    public void setTotalVista1(BigDecimal totalVista1) {
        this.totalVista1 = totalVista1;
    }

    public ArrayList<CorteVista1> getVista1() {
        return vista1;
    }

    public void setVista1(ArrayList<CorteVista1> vista1) {
        this.vista1 = vista1;
    }

    public boolean isEnableCalendar() {
        return enableCalendar;
    }

    public void setEnableCalendar(boolean enableCalendar) {
        this.enableCalendar = enableCalendar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewEstate() {
        return viewEstate;
    }

    public void setViewEstate(String viewEstate) {
        this.viewEstate = viewEstate;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFiltroFin() {
        return fechaFiltroFin;
    }

    public void setFechaFiltroFin(Date fechaFiltroFin) {
        this.fechaFiltroFin = fechaFiltroFin;
    }

    public Date getFechaFiltroInicio() {
        return fechaFiltroInicio;
    }

    public void setFechaFiltroInicio(Date fechaFiltroInicio) {
        this.fechaFiltroInicio = fechaFiltroInicio;
    }

    public int getFiltro() {
        return filtro;
    }

    public void setFiltro(int filtro) {
        this.filtro = filtro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getHoy() {
        return hoy;
    }

    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public BigDecimal getTotalCategorias() {
        return totalCategorias;
    }

    public void setTotalCategorias(BigDecimal totalCategorias) {
        this.totalCategorias = totalCategorias;
    }

    public BigDecimal getTotalMeseros() {
        return totalMeseros;
    }

    public void setTotalMeseros(BigDecimal totalMeseros) {
        this.totalMeseros = totalMeseros;
    }

    public ArrayList<CorteVista1> getVistaMeseros() {
        return vistaMeseros;
    }

    public void setVistaMeseros(ArrayList<CorteVista1> vistaMeseros) {
        this.vistaMeseros = vistaMeseros;
    }

}
