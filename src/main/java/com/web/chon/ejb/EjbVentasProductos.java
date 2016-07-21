/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.VentasProductos;
import com.web.chon.negocio.NegocioVentasProductos;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author freddy
 */
@Stateless(mappedName = "ejbVentasProductos")
public class EjbVentasProductos implements NegocioVentasProductos{
    @PersistenceContext(unitName = "persistenceJR")
    EntityManager em;

    @Override
    public List<Object[]> getVentaProductoByIdVenta(BigDecimal idVentaFK) {
       try {

            Query query = em.createNativeQuery("select vp.*, p.NOMBRE_PRODUCTO from VENTAS_PRODUCTOS vp INNER JOIN PRODUCTOS P ON P.ID_PRODUCTO_PK = VP.ID_PRODUCTO_FK where ID_VENTAS_FK =? and VP.ESTATUS = 1");
            query.setParameter(1, idVentaFK);
            List<Object[]> resultList = null;
            
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) 
        {
            Logger.getLogger(EjbVentasProductos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int insertarVentaProducto(VentasProductos ventaProducto) {
       Query query = em.createNativeQuery("INSERT INTO VENTAS_PRODUCTOS(ID_VENTAS_PRODUCTOS_PK,ID_VENTAS_FK,ID_PRODUCTO_FK,CANTIDAD,OBSERVACIONES,PRECIO_VENTA,TOTAL,ESTATUS) VALUES(?,?,?,?,?,?,?,?) ");
        System.out.println("ventas ejb :" + ventaProducto.toString());
        query.setParameter(1, ventaProducto.getIdVentasProductosPk());
        query.setParameter(2, ventaProducto.getIdVentasFk());
        query.setParameter(3, ventaProducto.getIdProductoFk());
        query.setParameter(4, ventaProducto.getCantidad());
        query.setParameter(5, ventaProducto.getObservaciones());
        query.setParameter(6, ventaProducto.getPrecioVenta());
        query.setParameter(7, ventaProducto.getTotalProducto());
        query.setParameter(8, ventaProducto.getEstatus());
        return query.executeUpdate();
    }

    @Override
    public int cancelarVenta(VentasProductos ventaProducto) {
        Query query = em.createNativeQuery("UPDATE VENTAS_PRODUCTOS SET ESTATUS = ? WHERE ID_VENTAS_PRODUCTOS_PK = ?");
        System.out.println("ventas ejb :" + ventaProducto.toString());
        query.setParameter(1, ventaProducto.getEstatus());
        query.setParameter(2, ventaProducto.getIdVentasProductosPk());
        return query.executeUpdate();
    }

    @Override
    public int getNextVal() {
        Query query = em.createNativeQuery("SELECT S_VENTAS_PRODUCTOS.nextVal FROM DUAL");
        return Integer.parseInt(query.getSingleResult().toString());
    }
}
