/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.Ventas;
import com.web.chon.negocio.NegocioVentas;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author freddy
 */
@Stateless(mappedName = "ejbVentas")
public class EjbVentas implements NegocioVentas{

    @PersistenceContext(unitName = "persistenceJR")
    EntityManager em;
    
    @Override
    public int getNextVal() {
        Query query = em.createNativeQuery("SELECT S_VENTAS.nextVal FROM DUAL");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public int insertarVenta(Ventas venta) {
        Query query = em.createNativeQuery("INSERT INTO VENTAS(ID_VENTAS_PK,NUMERO_MESA,ESTATUS_VENTAS,CANTIDAD_PERSONAS,FECHA_INICIO,ID_MESERO_FK,TOTAL,FOLIO,ID_SUCURSAL_FK) VALUES(?,?,?,?,sysdate,?,?,?,?)");
        System.out.println("ventas ejb :" + venta.toString());
        query.setParameter(1, venta.getIdVentaPk());
        query.setParameter(2, venta.getNumeroMesa());
        query.setParameter(3, venta.getEstatusVenta());
        query.setParameter(4, venta.getCantidadPersonas());
        //Fecha_Inicio
        //Fecha_Fin
        query.setParameter(5, venta.getIdMeseroFk());
        query.setParameter(6, venta.getTotal());
        query.setParameter(7, venta.getFolio());
        query.setParameter(8, venta.getIdSucursalFk());
        return query.executeUpdate();
    }

    @Override
    public int getNextFolioByIdSucursal(BigDecimal idSucursal) 
    {
        try {
            Query query = em.createNativeQuery("select NVL(max(FOLIO),0) as FOLIO  from VENTAS where ID_SUCURSAL_FK = ?");
            query.setParameter(1, idSucursal);
            return Integer.parseInt(query.getSingleResult().toString());
        } catch (Exception e) {
            System.out.println("Error >" + e.getMessage());
            return 1;
        }
        
    }

    @Override
    public List<Object[]> getVentasByInterval(String fechaInicio, String fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta) 
    {
        
        Query query;
        StringBuffer cadena = new StringBuffer("select v.ID_VENTAS_PK, v.NUMERO_MESA, v.ESTATUS_VENTAS,v.CANTIDAD_PERSONAS, \n" +
"v.FECHA_INICIO, v.FECHA_FIN, v.ID_MESERO_FK,v.TOTAL , v.FOLIO, v.ID_SUCURSAL_FK,u.NOMBRE_USUARIO,u.APATERNO_USUARIO,\n" +
"u.AMATERNO_USUARIO\n" +
"from ventas v\n" +
"inner join usuario u \n" +
"on u.id_usuario_pk = v.id_mesero_fk\n" +
"where v.ESTATUS_VENTAS = '"+idStatusVenta+"' and v.ID_SUCURSAL_FK='"+idSucursal+"' and TO_DATE(TO_CHAR(v.FECHA_INICIO,'dd/mm/yyyy'),'dd/mm/yyyy') = '"+ fechaInicio +"' order by v.NUMERO_MESA");
        query = em.createNativeQuery(cadena.toString());

        try {
            List<Object[]> lstObject = query.getResultList();
            return lstObject;
        } catch (Exception e) {
            System.out.println("Error >" + e.getMessage());
            return null;
        }
    }

    @Override
    public int cancelarVenta(Ventas venta) {
       Query query = em.createNativeQuery("UPDATE  VENTAS SET ESTATUS_VENTAS = ? WHERE ID_VENTAS_PK =?");
        System.out.println("ventas ejb :" + venta.toString());
        query.setParameter(1, venta.getEstatusVenta());
        query.setParameter(2, venta.getIdVentaPk());
        
        return query.executeUpdate();
        
    }
    @Override
    public int finalizarVenta(Ventas venta) {
       Query query = em.createNativeQuery("UPDATE  VENTAS SET ESTATUS_VENTAS = ?,FECHA_FIN=sysdate,FOLIO = ?, TOTAL=?  WHERE ID_VENTAS_PK =?");
        System.out.println("ventas ejb :" + venta.toString());
        query.setParameter(1, venta.getEstatusVenta());
        query.setParameter(2, venta.getFolio());
        query.setParameter(3, venta.getTotal());
        query.setParameter(4, venta.getIdVentaPk());
        
        return query.executeUpdate();
        
    }

    @Override
    public List<Object[]> getVentas(String fechaInicio, String fechaFin, BigDecimal idSucursal, BigDecimal idStatusVenta) {
         Query query;
         System.out.println("EJB=GETVENTAS");
        StringBuffer cadena = new StringBuffer("select v.ID_VENTAS_PK, v.NUMERO_MESA, v.ESTATUS_VENTAS,v.CANTIDAD_PERSONAS, \n" +
"v.FECHA_INICIO, v.FECHA_FIN, v.ID_MESERO_FK,v.TOTAL , v.FOLIO, v.ID_SUCURSAL_FK,u.NOMBRE_USUARIO,u.APATERNO_USUARIO,\n" +
"u.AMATERNO_USUARIO\n" +
"from ventas v\n" +
"inner join usuario u \n" +
"on u.id_usuario_pk = v.id_mesero_fk\n" +
"where v.ESTATUS_VENTAS = '"+idStatusVenta+"' and v.ID_SUCURSAL_FK='"+idSucursal+"' and TO_DATE(TO_CHAR(v.FECHA_INICIO,'dd/mm/yyyy'),'dd/mm/yyyy') BETWEEN '"+ fechaInicio +"' AND '"+ fechaFin +"' order by v.FECHA_INICIO");
        query = em.createNativeQuery(cadena.toString());

        try {
            List<Object[]> lstObject = query.getResultList();
            return lstObject;
        } catch (Exception e) {
            System.out.println("Error >" + e.getMessage());
            return null;
        }
    
    }

    @Override
    public int updateVenta(Ventas venta) {
         Query query = em.createNativeQuery("UPDATE  VENTAS SET TOTAL = ? WHERE ID_VENTAS_PK =?");
        System.out.println("ventas ejb :" + venta.toString());
        query.setParameter(1, venta.getTotal());
        query.setParameter(2, venta.getIdVentaPk());
        
        return query.executeUpdate();
    
    }

    @Override
    public int cancelarVentaProducto(Ventas venta) {
       Query query = em.createNativeQuery("UPDATE  VENTAS SET TOTAL = ? WHERE ID_VENTAS_PK =?");
        System.out.println("ventas ejb :" + venta.toString());
        query.setParameter(1, venta.getTotal());
        query.setParameter(2, venta.getIdVentaPk());
        
        return query.executeUpdate();
        
    }
    
}
