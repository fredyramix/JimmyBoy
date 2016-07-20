/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.VentaProducto;
import com.web.chon.dominio.VentasProductos;
import com.web.chon.negocio.NegocioVentasProductos;
import com.web.chon.util.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author freddy
 */
@Service
public class ServiceVentasProductos implements IfaceVentasProductos{

    NegocioVentasProductos ejb;

    private void getEjb() {
        try {
            ejb = (NegocioVentasProductos) Utilidades.getEJBRemote("ejbVentasProductos", NegocioVentasProductos.class.getName());
        } catch (Exception ex) {
            Logger.getLogger(ServiceVentasProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public ArrayList<VentasProductos> getVentasProductoByIdVenta(BigDecimal idVentaFk) {
        getEjb();
        ArrayList<VentasProductos> lstVentaProductos = new ArrayList<VentasProductos>();
        List<Object[]> lstObject = ejb.getVentaProductoByIdVenta(idVentaFk);
        int count = 1;
        for(Object[] object : lstObject){
           
            VentasProductos venta = new VentasProductos();
            venta.setIdVentasProductosPk(object[0] == null ? null : new BigDecimal(object[0].toString()));
            venta.setIdVentasFk(object[1] == null ? null : new BigDecimal(object[1].toString()));
            venta.setIdProductoFk(object[2] == null ? "" : object[2].toString());
            venta.setCantidad(object[3] == null ? null : new BigDecimal(object[3].toString()));
            venta.setObservaciones(object[4] == null ? "" : object[4].toString());
            venta.setPrecioVenta(object[5] == null ? null : new BigDecimal(object[5].toString()));
            venta.setTotalProducto(object[6] == null ? null : new BigDecimal(object[6].toString()));
            venta.setNombreProducto(object[8] == null ? "" : object[8].toString());
            venta.setCount(count);
            count = count +1;
            lstVentaProductos.add(venta);
        }
        
        return lstVentaProductos;
    }

    @Override
    public int insertarVentaProducto(VentasProductos ventaProducto) {
        getEjb();
        return ejb.insertarVentaProducto(ventaProducto);
    }

    @Override
    public int cancelarItem(VentasProductos ventaProducto) {
        getEjb();
        return ejb.cancelarVenta(ventaProducto);
    }

    @Override
    public int getNextVal() {
        getEjb();
        return ejb.getNextVal();
    }
    
}
