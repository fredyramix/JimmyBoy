/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.Ventas;
import com.web.chon.dominio.VentasProductos;
import com.web.chon.negocio.NegocioVentas;
import com.web.chon.util.TiempoUtil;
import com.web.chon.util.Utilidades;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author freddy
 */
@Service
public class ServiceVentas implements IfaceVentas {

    NegocioVentas ejb;
    @Autowired IfaceVentasProductos ifaceVentasProductos ;

    private void getEjb() {
        try {
            if (ejb == null) {
                ejb = (NegocioVentas) Utilidades.getEJBRemote("ejbVentas", NegocioVentas.class.getName());
            }

        } catch (Exception ex) {
            Logger.getLogger(ServiceVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public int getNextVal() {
        getEjb();
        return ejb.getNextVal();
    }

    @Override
    public int insertarVenta(Ventas venta) {
        getEjb();
        return ejb.insertarVenta(venta);
    }

    @Override
    public int getNextFolioByIdSucursal(BigDecimal idSucursal) {
       getEjb();
        return (ejb.getNextFolioByIdSucursal(idSucursal));
    }

    @Override
    public ArrayList<Ventas> getVentasByInterval(Date fechaInicio, Date fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta) {
        getEjb();
        ArrayList<Ventas> lstVenta = new ArrayList<Ventas>();
        List<Object[]> lstObject = ejb.getVentasByInterval(TiempoUtil.getFechaDDMMYYYY(fechaInicio), TiempoUtil.getFechaDDMMYYYY(fechaFin), idSucursal, idStatusVenta);
        for (Object[] object : lstObject) {

            Ventas venta = new Ventas();
            venta.setIdVentaPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            venta.setNumeroMesa(object[1] == null ? null : new BigDecimal(object[1].toString()));
            venta.setEstatusVenta(object[2] == null ? null : new BigDecimal(object[2].toString()));
            venta.setCantidadPersonas(object[3] == null ? null : new BigDecimal(object[3].toString()));
            venta.setFechaInicio(object[4] == null ? null : (Date) object[4]);
            venta.setFechaFin(object[5] == null ? null : (Date) object[5]);
            venta.setIdMeseroFk(object[6] == null ? null : new BigDecimal(object[6].toString()));
            venta.setTotal(object[7] == null ? null : new BigDecimal(object[7].toString()));
            venta.setFolio(object[8] == null ? null : new BigDecimal(object[8].toString()));
            venta.setIdSucursalFk(object[9] == null ? null : new BigDecimal(object[9].toString()));
            venta.setNombreMesero(object[10] == null ? "" :(object[10].toString()));
            venta.setApPaMesero(object[11] == null ? "" :(object[11].toString()));
            venta.setApMaMesero(object[12] == null ? "" :(object[12].toString()));
            if(venta.getEstatusVenta().intValue()==1)
            {
                venta.setNombreEstatus("ACTIVO");
            }
            
            ArrayList<VentasProductos> lstDetalle = new ArrayList<VentasProductos>();
            lstDetalle = ifaceVentasProductos.getVentasProductoByIdVenta(venta.getIdVentaPk());
            venta.setListaProductos(lstDetalle);
//            dominio.setListaDetalleProducto(lstDetalle);
//            BigDecimal sumaKilos = new BigDecimal(0);
//            BigDecimal sumaCostos = new BigDecimal(0);
//            for(EntradaMenudeoProducto item:lstDetalle)
//            {
//                sumaKilos = sumaKilos.add(item.getKilosTotales(), MathContext.UNLIMITED);
//                sumaCostos = sumaCostos.add(item.getPrecio(), MathContext.UNLIMITED);
//                dominio.setSumaCostos(sumaCostos);
//                dominio.setSumaKilos(sumaKilos);
//            }
            
            lstVenta.add(venta);
        }
        return lstVenta;
    
    }

    @Override
    public int cancelarVenta(Ventas venta) {
     getEjb();
        return ejb.cancelarVenta(venta);
    }

    @Override
    public int finalizarVenta(Ventas venta) {
        getEjb();
        return ejb.finalizarVenta(venta);
    }

    @Override
    public ArrayList<Ventas> getVentas(Date fechaInicio, Date fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta,BigDecimal idMesero) {
        getEjb();
        ArrayList<Ventas> lstVenta = new ArrayList<Ventas>();
        List<Object[]> lstObject = ejb.getVentas(TiempoUtil.getFechaDDMMYYYY(fechaInicio), TiempoUtil.getFechaDDMMYYYY(fechaFin), idSucursal, idStatusVenta,idMesero);
        for (Object[] object : lstObject) {

            Ventas venta = new Ventas();
            venta.setIdVentaPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            venta.setNumeroMesa(object[1] == null ? null : new BigDecimal(object[1].toString()));
            venta.setEstatusVenta(object[2] == null ? null : new BigDecimal(object[2].toString()));
            venta.setCantidadPersonas(object[3] == null ? null : new BigDecimal(object[3].toString()));
            venta.setFechaInicio(object[4] == null ? null : (Date) object[4]);
            venta.setFechaFin(object[5] == null ? null : (Date) object[5]);
            venta.setIdMeseroFk(object[6] == null ? null : new BigDecimal(object[6].toString()));
            venta.setTotal(object[7] == null ? null : new BigDecimal(object[7].toString()));
            venta.setFolio(object[8] == null ? null : new BigDecimal(object[8].toString()));
            venta.setIdSucursalFk(object[9] == null ? null : new BigDecimal(object[9].toString()));
            venta.setNombreMesero(object[10] == null ? "" :(object[10].toString()));
            venta.setApPaMesero(object[11] == null ? "" :(object[11].toString()));
            venta.setApMaMesero(object[12] == null ? "" :(object[12].toString()));
//            if(venta.getEstatusVenta().intValue()==1)
//            {
//                venta.setNombreEstatus("ACTIVO");
//            }
//            
            ArrayList<VentasProductos> lstDetalle = new ArrayList<VentasProductos>();
            lstDetalle = ifaceVentasProductos.getVentasProductoByIdVenta(venta.getIdVentaPk());
            venta.setListaProductos(lstDetalle);
//            dominio.setListaDetalleProducto(lstDetalle);
//            BigDecimal sumaKilos = new BigDecimal(0);
//            BigDecimal sumaCostos = new BigDecimal(0);
//            for(EntradaMenudeoProducto item:lstDetalle)
//            {
//                sumaKilos = sumaKilos.add(item.getKilosTotales(), MathContext.UNLIMITED);
//                sumaCostos = sumaCostos.add(item.getPrecio(), MathContext.UNLIMITED);
//                dominio.setSumaCostos(sumaCostos);
//                dominio.setSumaKilos(sumaKilos);
//            }
            
            lstVenta.add(venta);
        }
        return lstVenta;
    
    }

    @Override
    public int updateVenta(Ventas venta) {
        getEjb();
        return ejb.updateVenta(venta);
    }

    @Override
    public int cancelarVentaProducto(Ventas venta) {
        getEjb();
        return ejb.cancelarVentaProducto(venta);
    
    }
    
}
