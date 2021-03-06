/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.CorteVista1;
import com.web.chon.dominio.Ventas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author freddy
 */
public interface IfaceVentas {

    public int getNextVal();

    public int insertarVenta(Ventas venta);
    public int updateVenta(Ventas venta);

    public int cancelarVenta(Ventas venta);
    
    public int cancelarVentaProducto(Ventas venta);

    public int finalizarVenta(Ventas venta);

    public int getNextFolioByIdSucursal(BigDecimal idSucursal);
    

    public ArrayList<Ventas> getVentas(Date fechaInicio, Date fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta,BigDecimal idMesero);

    public ArrayList<Ventas> getVentasByInterval(Date fechaInicio, Date fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta);

    public ArrayList<CorteVista1> getVentasCorte1(Date fechaInicio, Date fechaFin);
    
    public ArrayList<CorteVista1> getVentasCategorias(Date fechaInicio, Date fechaFin);
    
    public ArrayList<CorteVista1> getVentasMeseros(Date fechaInicio, Date fechaFin);
    
    public int getCantPersonasByFecha(String fechaIni, String fechaFin);

    
    
}
