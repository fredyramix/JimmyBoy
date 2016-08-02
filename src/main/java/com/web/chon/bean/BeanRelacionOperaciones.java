package com.web.chon.bean;
import com.web.chon.dominio.Usuario;
import com.web.chon.dominio.Ventas;
import com.web.chon.dominio.VentasProductos;
import com.web.chon.service.IfaceCatUsuario;
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

@Component
@Scope("view")
public class BeanRelacionOperaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired private IfaceVentas ifaceVentas;
    @Autowired private IfaceCatUsuario ifaceCatUsuario;
    private ArrayList<Ventas> listaVentasHistorial;
    private ArrayList<Usuario> listaMeseros;
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
    private BigDecimal totalRelacion;
    private BigDecimal idMeseroFk;

    @PostConstruct
    public void init() 
    {
        listaMeseros = new ArrayList<Usuario>();
        status = 2;
        setTitle("Relación de Operaciones");
        setViewEstate("init");
        hoy = new Date();
        listaMeseros = ifaceCatUsuario.getMeseros();
        listaVentasHistorial = new ArrayList<Ventas>();
        fechaFiltroInicio = new Date();
        fechaFiltroFin = new Date();

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

    public void buscar() {
        totalRelacion = new BigDecimal(0);
        if (getFechaFiltroInicio() == null || getFechaFiltroFin() == null) {
            JsfUtil.addErrorMessageClean("Favor de ingresar un rango de fechas");
        } else {
            listaVentasHistorial = ifaceVentas.getVentas(fechaFiltroInicio, fechaFiltroFin, new BigDecimal(1), new BigDecimal(status), idMeseroFk);
            getTotalRelacionOperaciones();

        }

    }

    public void getTotalRelacionOperaciones() {
        for (Ventas v : listaVentasHistorial) {
            for (VentasProductos vp : v.getListaProductos()) {
                totalRelacion = totalRelacion.add(vp.getTotalProducto(), MathContext.UNLIMITED);
            }

        }
    }

    public boolean isEnableCalendar() {
        return enableCalendar;
    }

    public void setEnableCalendar(boolean enableCalendar) {
        this.enableCalendar = enableCalendar;
    }

    public BigDecimal getTotalRelacion() {
        return totalRelacion;
    }

    public void setTotalRelacion(BigDecimal totalRelacion) {
        this.totalRelacion = totalRelacion;
    }

    public BigDecimal getIdMeseroFk() {
        return idMeseroFk;
    }

    public void setIdMeseroFk(BigDecimal idMeseroFk) {
        this.idMeseroFk = idMeseroFk;
    }

    public ArrayList<Ventas> getListaVentasHistorial() {
        return listaVentasHistorial;
    }

    public void setListaVentasHistorial(ArrayList<Ventas> listaVentasHistorial) {
        this.listaVentasHistorial = listaVentasHistorial;
    }

    public ArrayList<Usuario> getListaMeseros() {
        return listaMeseros;
    }

    public void setListaMeseros(ArrayList<Usuario> listaMeseros) {
        this.listaMeseros = listaMeseros;
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

}
