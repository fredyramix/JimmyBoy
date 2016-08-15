package com.web.chon.bean;

import com.web.chon.dominio.Usuario;
import com.web.chon.dominio.Venta;
import com.web.chon.dominio.Ventas;
import com.web.chon.dominio.VentasProductos;
import com.web.chon.service.IfaceCatUsuario;
import com.web.chon.service.IfaceVentas;
import com.web.chon.util.Constantes;
import com.web.chon.util.JasperReportUtil;
import com.web.chon.util.JsfUtil;
import com.web.chon.util.NumeroALetra;
import com.web.chon.util.TiempoUtil;
import com.web.chon.util.UtilUpload;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class BeanRelacionOperaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private IfaceVentas ifaceVentas;
    @Autowired
    private IfaceCatUsuario ifaceCatUsuario;
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
    private StreamedContent media;

    //variables para ticket
    private Map paramReport = new HashMap();
    private ByteArrayOutputStream outputStream;
    private String number;
    private String rutaPDF;
    private String nombreEmpaque = "";
    private String pathFileJasper = "";
    private Ventas ventaImprimir;

    @PostConstruct
    public void init() {
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

    public void imprimir() {
        setParameterTicket(ventaImprimir);
        generateReport(ventaImprimir.getIdVentaPk().intValue(), ventaImprimir.getFolio().intValue());
        RequestContext.getCurrentInstance().execute("window.frames.miFrame.print();");
    }

    private void setParameterTicket(Ventas venta) {

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        DecimalFormat df = new DecimalFormat("###.##");
        Date date = new Date();
        NumeroALetra numeroLetra = new NumeroALetra();
        String totalVentaStr = numeroLetra.Convertir(df.format(venta.getTotal()), true);
        putValues(TiempoUtil.getFechaDDMMYYYYHHMM(date), totalVentaStr, venta, nf.format(venta.getTotal()));

    }
    private void putValues(String dateTime, String totalVentaStr, Ventas v, String totalisimo) {
        paramReport.put("fechaVenta", TiempoUtil.getFechaDDMMYYYYHHMM(v.getFechaFin()));
        paramReport.put("folio", v.getFolio().toString());
        paramReport.put("ID_VENTA_PK", v.getIdVentaPk().toString());
        paramReport.put("ventaTotal", totalisimo);
        paramReport.put("totalLetra", totalVentaStr);
        paramReport.put("mesa", v.getNumeroMesa().toString());
        paramReport.put("mesero", v.getNombreMesero());
        paramReport.put("per", v.getCantidadPersonas().toString());
        paramReport.put("abre", v.getFechaInicio().getHours() + ":" + v.getFechaInicio().getMinutes() + ":" + v.getFechaInicio().getSeconds() + "hrs");
        paramReport.put("cie", v.getFechaFin().getHours() + ":" + v.getFechaFin().getMinutes() + ":" +v.getFechaFin().getSeconds() + "hrs");
    }

    public void generateReport(int idVenta, int folioVenta) {
        JRExporter exporter = null;

        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String temporal = "";
            if (servletContext.getRealPath("") == null) {
                temporal = Constantes.PATHSERVER;
            } else {
                temporal = servletContext.getRealPath("");
            }
            Context initContext;
            Connection con = null;
            try {

                javax.sql.DataSource datasource = null;

                Context initialContext = new InitialContext();

                // "jdbc/MyDBname" >> is a JNDI Name of DataSource on weblogic
                datasource = (DataSource) initialContext.lookup("DataChon");

                try {
                    con = datasource.getConnection();
                    System.out.println("datsource" + con.toString());
                } catch (SQLException ex) {
                    Logger.getLogger(beanCorteCaja.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NamingException ex) {
                Logger.getLogger(beanCorteCaja.class.getName()).log(Level.SEVERE, null, ex);
            }

            pathFileJasper = temporal + File.separatorChar + "resources" + File.separatorChar + "report" + File.separatorChar + "ticketVenta" + File.separatorChar + "ticketVentaV2.jasper";

            JasperPrint jp = JasperFillManager.fillReport(getPathFileJasper(), paramReport, con);
            outputStream = JasperReportUtil.getOutputStreamFromReport(paramReport, getPathFileJasper());
            exporter = new JRPdfExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//            exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");
            byte[] bytes = outputStream.toByteArray();

            rutaPDF = UtilUpload.saveFileTemp(bytes, "ticketPdf", idVenta, 1);
            con.close();

        } catch (Exception exception) {
            System.out.println("Error >" + exception.getMessage());

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

    public Ventas getVentaImprimir() {
        return ventaImprimir;
    }

    public void setVentaImprimir(Ventas ventaImprimir) {
        this.ventaImprimir = ventaImprimir;
    }

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }

    public Map getParamReport() {
        return paramReport;
    }

    public void setParamReport(Map paramReport) {
        this.paramReport = paramReport;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

    public String getNombreEmpaque() {
        return nombreEmpaque;
    }

    public void setNombreEmpaque(String nombreEmpaque) {
        this.nombreEmpaque = nombreEmpaque;
    }

    public String getPathFileJasper() {
        return pathFileJasper;
    }

    public void setPathFileJasper(String pathFileJasper) {
        this.pathFileJasper = pathFileJasper;
    }

}
