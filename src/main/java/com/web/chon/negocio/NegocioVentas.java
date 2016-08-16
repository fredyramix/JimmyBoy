/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.negocio;

import com.web.chon.dominio.CorteVista1;
import com.web.chon.dominio.Venta;
import com.web.chon.dominio.Ventas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author freddy
 */
@Remote
public interface NegocioVentas {
    public int getNextVal();
    public int insertarVenta(Ventas venta);
    public int updateVenta(Ventas venta);
    public int cancelarVenta(Ventas venta);
    public int getNextFolioByIdSucursal(BigDecimal idSucursal);
    public int finalizarVenta(Ventas venta);
    public int cancelarVentaProducto(Ventas venta);
    public List<Object[]> getVentas(String fechaInicio, String fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta,BigDecimal idMesero);
    public List<Object[]> getVentasByInterval(String fechaInicio, String fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta); 
    public List<Object[]> getVentasCorte1(String fechaInicio, String fechaFin); 
    public List<Object[]> getVentasCategorias(String fechaInicio, String fechaFin);
    public List<Object[]> getVentasMeseros(String fechaInicio, String fechaFin);
    public int getCantPersonasByFecha(String fechaIni, String fechaFin);

}
