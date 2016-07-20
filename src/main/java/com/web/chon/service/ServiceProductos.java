/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.Productos;
import com.web.chon.negocio.NegocioProductos;
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
public class ServiceProductos implements IfaceProductos {

    NegocioProductos ejb;

    public void getEjb() {
        if (ejb == null) {
            try {
                ejb = (NegocioProductos) Utilidades.getEJBRemote("ejbProductos", NegocioProductos.class.getName());
            } catch (Exception ex) {
                Logger.getLogger(ServiceProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Productos> getProductos() {
        try {
            getEjb();
            ArrayList<Productos> lstProducto = new ArrayList<Productos>();
            List<Object[]> lstObject = ejb.getProductos();
            for (Object[] obj : lstObject) {

                Productos producto = new Productos();
                
                producto.setIdProductoPk(obj[0] == null ? "" : obj[0].toString());
                producto.setNombreProducto(obj[1] == null ? "" : obj[1].toString());
                producto.setDescripcionProducto(obj[2] == null ? "" : obj[2].toString());
                producto.setIdCategoriaFk(obj[3] == null ? "" : obj[3].toString());
                producto.setEstatus(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
                producto.setUrlImagen(obj[5] == null ? "" : obj[5].toString());
                producto.setNombreCategoria(obj[6] == null ? "" : obj[6].toString());
                producto.setPrecio(obj[7] == null ? null : new BigDecimal(obj[7].toString()));
                producto.setNombreEstatus(obj[8] == null ? "" : obj[8].toString());
                
                lstProducto.add(producto);
            }

            return lstProducto;
        } catch (Exception ex) {
            Logger.getLogger(ServiceProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Productos getProductoById(String idProducto) {
        getEjb();
        List<Object[]> lstObject = new ArrayList<Object[]>();
        lstObject = ejb.getProductosById(idProducto);
        Productos producto = new Productos();
        for (Object[] obj : lstObject) {
            producto.setIdProductoPk(obj[0] == null ? "" : obj[0].toString());
            producto.setNombreProducto(obj[1] == null ? "" : obj[1].toString());
            producto.setDescripcionProducto(obj[2] == null ? "" : obj[2].toString());
            producto.setIdCategoriaFk(obj[3] == null ? "" : obj[3].toString());
            producto.setEstatus(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
            producto.setUrlImagen(obj[5] == null ? "" : obj[5].toString());
            producto.setNombreCategoria(obj[6] == null ? "" : obj[6].toString());
        }

        return producto;
    }

    @Override
    public int deleteProducto(String idProducto) {
        getEjb();
        return ejb.deleteProducto(idProducto);
    }

    @Override
    public int updateProducto(Productos producto) {
        getEjb();
        return ejb.updateProducto(producto);
    }

    @Override
    public int insertarProducto(Productos producto) {
        getEjb();
        return ejb.insertarProducto(producto);

    }

    @Override
    public int getLastIdCategoria() {
       
        try {
            getEjb();
            int idCategoria =0;
            idCategoria = ejb.getLastIdCategoria();
            return idCategoria;
        } catch (Exception ex) {
            Logger.getLogger(ServiceProductos.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        
    }

}
