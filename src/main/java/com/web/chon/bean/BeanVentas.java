/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.bean;

import com.web.chon.dominio.Categorias;
import com.web.chon.dominio.Productos;
import com.web.chon.dominio.Usuario;
import com.web.chon.dominio.Ventas;
import com.web.chon.dominio.VentasProductos;
import com.web.chon.service.IfaceCatUsuario;
import com.web.chon.service.IfaceCategorias;
import com.web.chon.service.IfaceProductos;
import com.web.chon.service.IfaceVentas;
import com.web.chon.service.IfaceVentasProductos;
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
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
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
public class BeanVentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private IfaceVentas ifaceVentas;
    @Autowired
    private IfaceCatUsuario ifaceCatUsuario;
    @Autowired
    private IfaceVentasProductos ifaceVentasProductos;
    @Autowired
    private IfaceProductos ifaceProductos;
    @Autowired
    private IfaceCategorias ifaceCategorias;
    
    private ArrayList<Categorias> listaCategorias;
    private ArrayList<Ventas> listaVentas;
    private ArrayList<Ventas> listaVentasHistorial;
    private ArrayList<Usuario> listaMeseros;
    private ArrayList<Productos> listaProductos;
    private Ventas venta;
    private Ventas ventaFinalizar;
    private Ventas ventaAddProducto;
    private Ventas ventaRemove;
    private Ventas ventaEdit;

    private String title = "";
    private String viewEstate = "";
    private Date hoy;
    private VentasProductos dataProductoEdit;
    private VentasProductos dataProductoRemove;
    private VentasProductos dataProductoNuevo;

    private Date fechaFin;
    private Date fechaInicio;
    private Date fechaFiltroFin;
    private Date fechaFiltroInicio;

    private int filtro;
    private int status;
    private boolean enableCalendar;
    private BigDecimal totalRelacion;
    private BigDecimal idMeseroFk;
    
    private Productos producto;

    //VARIABLES DE TICKET
    private StreamedContent media;
    private Map paramReport = new HashMap();
    private ByteArrayOutputStream outputStream;
    private String number;
    private String rutaPDF;
    private String nombreEmpaque = "";
    private String pathFileJasper = "C:/Users/Juan/Documents/NetBeansProjects/Chonajos-V2/ticket.jasper";

    @PostConstruct
    public void init() {
        listaVentas = new ArrayList<Ventas>();
        listaMeseros = new ArrayList<Usuario>();
        listaProductos = new ArrayList<Productos>();
        listaCategorias = new ArrayList<Categorias>();
        venta = new Ventas();
        status = 2;
        producto = new Productos();
        setTitle("Realizar Pedidos");
        setViewEstate("init");
        hoy = new Date();
        dataProductoNuevo = new VentasProductos();
        listaVentas = ifaceVentas.getVentasByInterval(hoy, hoy, new BigDecimal(1), new BigDecimal(1));
        listaMeseros = ifaceCatUsuario.getMeseros();
        //listaProductos = ifaceProductos.getProductos();
        listaCategorias = ifaceCategorias.getCategorias();
        listaVentasHistorial = new ArrayList<Ventas>();

    }
    public void searchProductosByIdCategorias()
    {
        listaProductos=ifaceProductos.getProductosByIdCategoria(dataProductoNuevo.getIdCategoria());
    }

    public void addVentaProducto() {
        if(producto.getIdProductoPk()!=null){
        BigDecimal suma = new BigDecimal(0);
        Ventas temporal = new Ventas();
        
        dataProductoNuevo.setIdVentasFk(ventaAddProducto.getIdVentaPk());
        dataProductoNuevo.setIdCategoria(dataProductoNuevo.getIdCategoria());
        dataProductoNuevo.setIdVentasProductosPk(new BigDecimal(ifaceVentasProductos.getNextVal()));
        dataProductoNuevo.setIdProductoFk(producto.getIdProductoPk());
        dataProductoNuevo.setNombreProducto(producto.getIdProductoPk());
        dataProductoNuevo.setEstatus(new BigDecimal(1));
        dataProductoNuevo.setObservaciones(dataProductoNuevo.getObservaciones());
        dataProductoNuevo.setPrecioVenta(getPrecio(dataProductoNuevo.getIdProductoFk()));
        if(dataProductoNuevo.getCantidad()==null || dataProductoNuevo.getCantidad().intValue()==0 )
        {
            dataProductoNuevo.setCantidad(new BigDecimal(1));
        }
        dataProductoNuevo.setCantidad(dataProductoNuevo.getCantidad());
        dataProductoNuevo.setTotalProducto(dataProductoNuevo.getCantidad().multiply(dataProductoNuevo.getPrecioVenta(), MathContext.UNLIMITED));
        System.out.println("Producto: " + dataProductoNuevo.toString());
        if (ifaceVentasProductos.insertarVentaProducto(dataProductoNuevo) == 1) {
            JsfUtil.addSuccessMessageClean("Produco Agregado Correcamene");

            for (Ventas v : listaVentas) {
                if (v.getIdVentaPk().intValue() == ventaAddProducto.getIdVentaPk().intValue()) {
                    for (VentasProductos vp : v.getListaProductos()) {
                        suma = suma.add(vp.getTotalProducto(), MathContext.UNLIMITED);
                    }
                }
            }
            temporal.setIdVentaPk(dataProductoNuevo.getIdVentasFk());
            temporal.setTotal(suma.add(dataProductoNuevo.getTotalProducto(), MathContext.UNLIMITED));
            ifaceVentas.updateVenta(temporal);

            listaVentas = ifaceVentas.getVentasByInterval(hoy, hoy, new BigDecimal(1), new BigDecimal(1));
            dataProductoNuevo.reset();
            producto  = new Productos();
        } else {
            JsfUtil.addErrorMessageClean("Ocurrio un problema conacar al adminisrador");
        
        }
        }
        else
        {
            JsfUtil.addErrorMessageClean("Agregar un producto al menos");
        }
    }
    public ArrayList<Productos> autoComplete(String nombreProducto) {
        listaProductos = ifaceProductos.getProductoByNombre(nombreProducto.toUpperCase());
        return listaProductos;

    }

    public BigDecimal getPrecio(String idProducto) {
        System.out.println("Entro a la funcion");
        for (Productos p : listaProductos) {
            if (p.getIdProductoPk().equals(idProducto)) {
                System.out.println("Encontro el precio.");
                return p.getPrecio();
            }
        }
        return new BigDecimal(0);

    }

    public void cancelarVenta() {
        ventaRemove.setEstatusVenta(new BigDecimal(3));
        System.out.println("VentaRemove: " + ventaRemove.toString());
        if (ifaceVentas.cancelarVenta(ventaRemove) == 1) {
            JsfUtil.addSuccessMessageClean("Venta Cancelada");
            listaVentas = ifaceVentas.getVentasByInterval(hoy, hoy, new BigDecimal(1), new BigDecimal(1));
        } else {
            JsfUtil.addErrorMessageClean("Ocurrio un problema, contactar a soporte");
        }
    }

    public void remove() {
        dataProductoRemove.setEstatus(new BigDecimal(3));
        System.out.println("DataProductoRemove: " + dataProductoRemove.toString());
        if (ifaceVentasProductos.cancelarItem(dataProductoRemove) == 1) {
            JsfUtil.addSuccessMessageClean("Producto Eliminado Correctamente");
            BigDecimal total = new BigDecimal(0);
            for (Ventas v : listaVentas) {
                if (v.getIdVentaPk().intValue() == dataProductoRemove.getIdVentasFk().intValue()) {
                    total = v.getTotal().subtract(dataProductoRemove.getTotalProducto(), MathContext.UNLIMITED);

                }
            }
            Ventas v = new Ventas();
            v.setIdVentaPk(dataProductoRemove.getIdVentasFk());
            v.setTotal(total);
            ifaceVentas.cancelarVentaProducto(v);
            listaVentas = ifaceVentas.getVentasByInterval(hoy, hoy, new BigDecimal(1), new BigDecimal(1));
        } else {
            JsfUtil.addErrorMessageClean("Ocurrio un problema, contactar a soporte");
        }

    }

    public void addVenta() {
        boolean bandera = false;
        if (venta.getNumeroMesa() == null || venta.getIdMeseroFk() == null) {
            JsfUtil.addErrorMessageClean("Favor de ingresar todos los datos para realizar la venta");
        } else {
            for (Ventas v : listaVentas) {
                if (v.getNumeroMesa().intValue() == venta.getNumeroMesa().intValue()) {
                    bandera = true;
                    System.out.println("Encontro mesa repetida");
                }
            }
            if (bandera) {
                JsfUtil.addErrorMessageClean("Esa mesa se encuentra activa, seleccionar otra o finalizar la venta de esa mesa");
            } else {
                if (venta.getCantidadPersonas() == null || venta.getCantidadPersonas().intValue() == 0) {
                    venta.setCantidadPersonas(new BigDecimal(1));
                }
                venta.setIdVentaPk(new BigDecimal(ifaceVentas.getNextVal()));
                venta.setIdSucursalFk(new BigDecimal(1));
                venta.setFolio(new BigDecimal(ifaceVentas.getNextFolioByIdSucursal(new BigDecimal(1))+1));
                venta.setEstatusVenta(new BigDecimal(1));
                System.out.println("Venta: " + venta.toString());
                if (ifaceVentas.insertarVenta(venta) != 0) {
                    JsfUtil.addSuccessMessageClean("Se ha iniciado la venta correctamente");
                    venta.reset();
                    listaVentas = ifaceVentas.getVentasByInterval(hoy, hoy, new BigDecimal(1), new BigDecimal(1));
                } else {
                    JsfUtil.addErrorMessageClean("Ocurrio un problema, contactar a soporte");
                }
            }
        }

    }

    public Date getHoy() {
        return hoy;
    }

    public void finalizarVenta() {

        Ventas ventaImprimir = new Ventas();
        ventaFinalizar.setEstatusVenta(new BigDecimal(2));
        ventaFinalizar.setFolio(new BigDecimal(ifaceVentas.getNextFolioByIdSucursal(new BigDecimal(1))));
        BigDecimal total = new BigDecimal(0);
        for (Ventas v : listaVentas) {
            if (v.getIdVentaPk().intValue() == ventaFinalizar.getIdVentaPk().intValue()) {
                ventaImprimir = v;
                for (VentasProductos vp : v.getListaProductos()) {
                    total = total.add(vp.getTotalProducto(), MathContext.UNLIMITED);
                }
            }
        }
        ventaFinalizar.setTotal(total);
        if (ventaFinalizar.getTotal().intValue() != 0) 
        {
            System.out.println("Venta a finalizar: " + ventaFinalizar.toString());
            if (ifaceVentas.finalizarVenta(ventaFinalizar) == 1) {
                JsfUtil.addSuccessMessageClean("Venta finalizada Correctamente");

                setParameterTicket(ventaImprimir);
                generateReport(ventaImprimir.getIdVentaPk().intValue(), ventaImprimir.getFolio().intValue());
                RequestContext.getCurrentInstance().execute("window.frames.miFrame.print();");

                listaVentas = ifaceVentas.getVentasByInterval(hoy, hoy, new BigDecimal(1), new BigDecimal(1));
            } else {
                JsfUtil.addErrorMessageClean("Ocurrio un problema, contactar a soporte");
            }
        }
        else
        {
            JsfUtil.addWarnMessageClean("Agregue al menos un producto para finalizar la venta");
        }
    }

    private void setParameterTicket(Ventas venta) {

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        DecimalFormat df = new DecimalFormat("###.##");
        Date date = new Date();

        //ArrayList<String> productos = new ArrayList<String>();
        NumeroALetra numeroLetra = new NumeroALetra();
//        for (VentasProductos vp : venta.getListaProductos()) {
//            int maximo = 20;
//            String cad = "";
//            StringBuilder nombreProducto = new StringBuilder(vp.getNombreProducto());
//            if (nombreProducto.length() > maximo) {
//                cad = nombreProducto.substring(0, maximo);
//            } else if (nombreProducto.length() < maximo) {
//                int dif = maximo - nombreProducto.length();
//
//                for (int i = 0; i <= dif + 6; i++) {
//                    nombreProducto.append(" ");
//                }
//                System.out.println("len: " + nombreProducto.length());
//                cad = nombreProducto.toString();
//            }
//            String cadena = vp.getCantidad().toString() + "   " +cad  + "   " + nf.format(vp.getPrecioVenta()).toString() + "   " + nf.format(vp.getTotalProducto()).toString();
//            productos.add(cadena);
//
//        }

        String totalVentaStr = numeroLetra.Convertir(df.format(venta.getTotal()), true);
        putValues(TiempoUtil.getFechaDDMMYYYYHHMM(date), totalVentaStr, venta,nf.format(venta.getTotal()));

    }
    private void putValues(String dateTime, String totalVentaStr, Ventas v,String totalisimo) {
        Date hoy = new Date();
        hoy.getHours();
        hoy.getMinutes();
        hoy.getSeconds();
        paramReport.put("fechaVenta", dateTime);
        paramReport.put("folio", v.getFolio().toString());
        paramReport.put("ID_VENTA_PK", v.getIdVentaPk().toString());
        paramReport.put("ventaTotal",totalisimo);
        paramReport.put("totalLetra", totalVentaStr);
        paramReport.put("mesa", v.getNumeroMesa().toString());
        paramReport.put("mesero", v.getNombreMesero());
        paramReport.put("per", v.getCantidadPersonas().toString());
        paramReport.put("abre",v.getFechaInicio().getHours()+":"+v.getFechaInicio().getMinutes()+":"+v.getFechaInicio().getSeconds()+"hrs");
        paramReport.put("cie", hoy.getHours()+":"+hoy.getMinutes()+":"+hoy.getSeconds()+"hrs");
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

            JasperPrint jp = JasperFillManager.fillReport(getPathFileJasper(), paramReport,con);
            outputStream = JasperReportUtil.getOutputStreamFromReport(paramReport, getPathFileJasper());
            exporter = new JRPdfExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//            exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");
            byte[] bytes = outputStream.toByteArray();

            rutaPDF = UtilUpload.saveFileTemp(bytes, "ticketPdf", idVenta, 1);

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


    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public ArrayList<Ventas> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(ArrayList<Ventas> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
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

    public ArrayList<Usuario> getListaMeseros() {
        return listaMeseros;
    }

    public void setListaMeseros(ArrayList<Usuario> listaMeseros) {
        this.listaMeseros = listaMeseros;
    }

    public VentasProductos getDataProductoEdit() {
        return dataProductoEdit;
    }

    public void setDataProductoEdit(VentasProductos dataProductoEdit) {
        this.dataProductoEdit = dataProductoEdit;
    }

    public VentasProductos getDataProductoRemove() {
        return dataProductoRemove;
    }

    public void setDataProductoRemove(VentasProductos dataProductoRemove) {
        this.dataProductoRemove = dataProductoRemove;
    }

    public Ventas getVentaRemove() {
        return ventaRemove;
    }

    public void setVentaRemove(Ventas ventaRemove) {
        this.ventaRemove = ventaRemove;
    }

    public Ventas getVentaEdit() {
        return ventaEdit;
    }

    public void setVentaEdit(Ventas ventaEdit) {
        this.ventaEdit = ventaEdit;
    }

    public Ventas getVentaAddProducto() {
        return ventaAddProducto;
    }

    public void setVentaAddProducto(Ventas ventaAddProducto) {
        this.ventaAddProducto = ventaAddProducto;
    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public VentasProductos getDataProductoNuevo() {
        return dataProductoNuevo;
    }

    public void setDataProductoNuevo(VentasProductos dataProductoNuevo) {
        this.dataProductoNuevo = dataProductoNuevo;
    }

    public Ventas getVentaFinalizar() {
        return ventaFinalizar;
    }

    public void setVentaFinalizar(Ventas ventaFinalizar) {
        this.ventaFinalizar = ventaFinalizar;
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

    public int getFiltro() {
        return filtro;
    }

    public void setFiltro(int filtro) {
        this.filtro = filtro;
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

    public boolean isEnableCalendar() {
        return enableCalendar;
    }

    public void setEnableCalendar(boolean enableCalendar) {
        this.enableCalendar = enableCalendar;
    }

    public ArrayList<Ventas> getListaVentasHistorial() {
        return listaVentasHistorial;
    }

    public void setListaVentasHistorial(ArrayList<Ventas> listaVentasHistorial) {
        this.listaVentasHistorial = listaVentasHistorial;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getTotalRelacion() {
        return totalRelacion;
    }

    public void setTotalRelacion(BigDecimal totalRelacion) {
        this.totalRelacion = totalRelacion;
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

    public ArrayList<Categorias> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Categorias> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public BigDecimal getIdMeseroFk() {
        return idMeseroFk;
    }

    public void setIdMeseroFk(BigDecimal idMeseroFk) {
        this.idMeseroFk = idMeseroFk;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}
