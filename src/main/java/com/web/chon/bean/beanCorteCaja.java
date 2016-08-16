/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.bean;
import com.web.chon.dominio.CorteVista1;
import com.web.chon.dominio.Ventas;
import com.web.chon.dominio.VentasProductos;
import com.web.chon.service.IfaceVentas;
import com.web.chon.util.Constantes;
import com.web.chon.util.JasperReportUtil;
import com.web.chon.util.JsfUtil;
import com.web.chon.util.NumeroALetra;
import com.web.chon.util.TiempoUtil;
import com.web.chon.util.UtilUpload;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
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

    //VARIABLES DE REPORTE
    private StreamedContent media;
    private Map paramReport = new HashMap();
    private ByteArrayOutputStream outputStream;
    private String number;
    private String rutaPDF;
    private String nombreEmpaque = "";
    private String pathFileJasper = "C:/Users/Juan/Documents/NetBeansProjects/Chonajos-V2/ticket.jasper";
private BigDecimal cantidadPersonas;
private BigDecimal totalVentas;
private BigDecimal promedio;
    @PostConstruct
    public void init() {
        status = 2;
        setTitle("Corte de Caja");
        setViewEstate("init");
        hoy = new Date();
        vista1 = new ArrayList<CorteVista1>();
        vistaCategorias = new ArrayList<CorteVista1>();
        vistaMeseros = new ArrayList<CorteVista1>();
        cantidadPersonas=new BigDecimal(0);
 totalVentas=new BigDecimal(0);
promedio=new BigDecimal(0);
        
    }

    public void descargar() 
    {
        if (vista1.isEmpty() || vistaCategorias.isEmpty() || vistaMeseros.isEmpty()) {
            JsfUtil.addErrorMessageClean("Genera una consulta primero");
        } else 
        {
            setParameterTicket(vistaCategorias, vista1, vistaMeseros);
            generateReport(1, "Coffee_2");
            downloadFile();

        }

    }

    public void imprimir() {
        if (vista1.isEmpty() || vistaCategorias.isEmpty() || vistaMeseros.isEmpty()) {
            JsfUtil.addErrorMessageClean("Genera una consulta primero");
        } else {
            setParameterTicket(vistaCategorias, vista1, vistaMeseros);
            generateReport(1, "Coffee_3");
            RequestContext.getCurrentInstance().execute("window.frames.miFrame.print();");

        }
    }

    public void buscar() {

        totalVista1 = new BigDecimal(0);
        totalCategorias = new BigDecimal(0);
        totalMeseros = new BigDecimal(0);
        if (getFechaFiltroInicio() == null || getFechaFiltroFin() == null) {
            JsfUtil.addErrorMessageClean("Favor de ingresar un rango de fechas");
        } else 
        {
            System.out.println("Fecha Inicio: " + fechaFiltroInicio);
            System.out.println("Fecha Inicio: " + fechaFiltroFin);
            vista1 = ifaceVentas.getVentasCorte1(fechaFiltroInicio, fechaFiltroFin);
            vistaCategorias = ifaceVentas.getVentasCategorias(fechaFiltroInicio, fechaFiltroFin);
            vistaMeseros = ifaceVentas.getVentasMeseros(fechaFiltroInicio, fechaFiltroFin);
            getTotalVistaProductos();
            getTotalVistaCategorias();
            getTotalVistaMeseros();
            cantidadPersonas = new BigDecimal(ifaceVentas.getCantPersonasByFecha(TiempoUtil.getFechaDDMMYYYY(fechaFiltroInicio), TiempoUtil.getFechaDDMMYYYY(fechaFiltroFin)));
            totalVentas  = totalVista1;
            promedio = totalVentas.divide(cantidadPersonas, 2, RoundingMode.HALF_UP);
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

        switch (filtro) {
            case 1:
                //se habilitan los calendarios.
                setFechaFiltroInicio(null);
                setFechaFiltroFin(null);
                enableCalendar = false;
                break;
            case 2:
                setFechaFiltroInicio(new Date());
                setFechaFiltroFin(new Date());
                enableCalendar = true;
                break;
            case 3:
                setFechaFiltroInicio(TiempoUtil.getDayOneOfMonth(new Date()));
                setFechaFiltroFin(TiempoUtil.getDayEndOfMonth(new Date()));
                enableCalendar = true;
                break;

            default:
                setFechaFiltroInicio(null);
                setFechaFiltroFin(null);
                enableCalendar = true;
                break;
        }

    }

    private void setParameterTicket(ArrayList<CorteVista1> listaCategorias, ArrayList<CorteVista1> listaProductos, ArrayList<CorteVista1> listaMeseros) {

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());

        //------For para recorrer las Categorias----//
        BigDecimal totalcantCategorias = new BigDecimal(0);
        BigDecimal totalDineCategorias = new BigDecimal(0);
        BigDecimal totalcantProductos = new BigDecimal(0);
        BigDecimal totalDineProductos = new BigDecimal(0);
        BigDecimal totalcantMeseros = new BigDecimal(0);
        BigDecimal totalDineMeseros = new BigDecimal(0);
        for (CorteVista1 linea : listaCategorias) {
            int maximo = 30;
            String cad = "";
            StringBuilder nombreProducto = new StringBuilder(linea.getNombreProducto());
            if (nombreProducto.length() > maximo) {
                cad = nombreProducto.substring(0, maximo);
            } else if (nombreProducto.length() < maximo) {
                int dif = maximo - nombreProducto.length();

                for (int i = 0; i <= dif + 6; i++) {
                    nombreProducto.append(" ");
                }
                //System.out.println("len: " + nombreProducto.length());
                cad = nombreProducto.toString();
            }
            totalcantCategorias = totalcantCategorias.add(linea.getCantidad(), MathContext.UNLIMITED);
            totalDineCategorias = totalDineCategorias.add(linea.getTotal(), MathContext.UNLIMITED);
            String cadena = linea.getIdProducto().toString() + "              " + cad + "             " + linea.getCantidad().toString() + "            " + nf.format(linea.getTotal()).toString();

        }
        //------For para recorrer los Productos----//
        for (CorteVista1 linea : listaProductos) {
            int maximo = 30;
            String cad = "";
            StringBuilder nombreProducto = new StringBuilder(linea.getNombreProducto());
            if (nombreProducto.length() > maximo) {
                cad = nombreProducto.substring(0, maximo);
            } else if (nombreProducto.length() < maximo) {
                int dif = maximo - nombreProducto.length();

                for (int i = 0; i <= dif + 6; i++) {
                    nombreProducto.append(" ");
                }
                //System.out.println("len: " + nombreProducto.length());
                cad = nombreProducto.toString();
            }
            totalcantProductos = totalcantProductos.add(linea.getCantidad(), MathContext.UNLIMITED);
            totalDineProductos = totalDineProductos.add(linea.getTotal(), MathContext.UNLIMITED);
            String cadena = linea.getIdProducto().toString() + "              " + cad + "             " + linea.getCantidad().toString() + "            " + nf.format(linea.getTotal()).toString();

        }
        for (CorteVista1 linea : listaMeseros) {
            int maximo = 30;
            String cad = "";
            StringBuilder nombreProducto = new StringBuilder(linea.getNombreProducto());
            if (nombreProducto.length() > maximo) {
                cad = nombreProducto.substring(0, maximo);
            } else if (nombreProducto.length() < maximo) {
                int dif = maximo - nombreProducto.length();

                for (int i = 0; i <= dif + 6; i++) {
                    nombreProducto.append(" ");
                }
                //System.out.println("len: " + nombreProducto.length());
                cad = nombreProducto.toString();
            }
            totalcantMeseros = totalcantMeseros.add(linea.getCantidad(), MathContext.UNLIMITED);
            totalDineMeseros = totalDineMeseros.add(linea.getTotal(), MathContext.UNLIMITED);
            String cadena = linea.getIdProducto().toString() + "              " + cad + "             " + linea.getCantidad().toString() + "            " + nf.format(linea.getTotal()).toString();

        }

        paramReport.put("fechaIni", TiempoUtil.getFechaDDMMYYYY(fechaFiltroInicio));
        paramReport.put("fechaFin", TiempoUtil.getFechaDDMMYYYY(fechaFiltroFin));
        System.out.println("Cantidad Personas: "+cantidadPersonas);
        System.out.println("Total Ventas: "+totalVentas);
        System.out.println("Promedio: "+promedio);
        
        paramReport.put("cantPers", cantidadPersonas.toString());
        paramReport.put("toVen", nf.format(totalVentas).toString());
        paramReport.put("promedio", nf.format(promedio).toString());

    }

    public void generateReport(int idVenta, String nombreArchivo) {
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

            pathFileJasper = temporal + File.separatorChar + "resources" + File.separatorChar + "report" + File.separatorChar + "Corte" + File.separatorChar + nombreArchivo + ".jasper";

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

    public void downloadFile() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            Date hoy = new Date();

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=" + "ReporteVentas" + TiempoUtil.getFechaDDMMYYYY(hoy) + ".pdf");

            OutputStream output = response.getOutputStream();
            output.write(outputStream.toByteArray());
            output.close();

            facesContext.responseComplete();
        } catch (Exception e) {
            System.out.println("Error >" + e.getMessage());
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

    public BigDecimal getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(BigDecimal cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }
    

}
