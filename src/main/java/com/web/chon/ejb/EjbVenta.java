package com.web.chon.ejb;

import com.web.chon.dominio.Subproducto;
import com.web.chon.dominio.Venta;
import com.web.chon.negocio.NegocioSubProducto;
import com.web.chon.negocio.NegocioVenta;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * Ejb para el catalogo de Productos
 *
 * @author Juan de la Cruz
 */
@Stateless(mappedName = "ejbVenta")
public class EjbVenta implements NegocioVenta {

    @PersistenceContext(unitName = "persistenceJR")
    EntityManager em;

    @Override
    public int insertarVenta(Venta venta,int folioVenta) {
        Query query = em.createNativeQuery("INSERT INTO VENTA(ID_VENTA_PK,FECHA_VENTA,ID_CLIENTE_FK,ID_VENDEDOR_FK,STATUS_FK,ID_SUCURSAL_FK,FOLIO_SUCURSAL) VALUES(?,sysdate,?,?,1,?,?)");
        System.out.println("venta ejb :" + venta.toString());
        query.setParameter(1, venta.getIdVentaPk());
        query.setParameter(2, venta.getIdClienteFk());
        query.setParameter(3, venta.getIdVendedorFk());
        query.setParameter(4, venta.getIdSucursal());
        query.setParameter(5, folioVenta);
        return query.executeUpdate();
    }

    @Override
    public int getNextVal() {
        Query query = em.createNativeQuery("SELECT S_VENTA.nextVal FROM DUAL");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public List<Object[]> getVentasByInterval(String fechaInicio, String fechaFin, int idSucursal, int idStatusVenta) {
        Query query;
        int cont = 0;
        StringBuffer cadena = new StringBuffer("SELECT ven.ID_VENTA_PK,ven.ID_CLIENTE_FK,ven.ID_VENDEDOR_FK, ven.FECHA_VENTA, ven.FECHA_PROMESA_PAGO,ven.STATUS_FK, ven.FECHA_PAGO,USU.ID_SUCURSAL_FK,\n"
                + " (CLI.NOMBRE||' '||CLI.APELLIDO_PATERNO ||' '||CLI.APELLIDO_MATERNO ) AS CLIENTE, \n"
                + "(USU.NOMBRE_USUARIO||' '||USU.APATERNO_USUARIO ||' '||USU.AMATERNO_USUARIO ) AS VENDEDOR, (select NVL(sum(VTP.TOTAL_VENTA),0) \n"
                + "FROM VENTA_PRODUCTO VTP WHERE VTP.ID_VENTA_FK =ven.ID_VENTA_PK) AS TOTAL_VENTA,FOLIO_SUCURSAL FROM VENTA ven \n"
                + "INNER JOIN CLIENTE CLI ON CLI.ID_CLIENTE = ven.ID_CLIENTE_FK \n"
                + "INNER JOIN USUARIO USU ON USU.ID_USUARIO_PK = ven.ID_VENDEDOR_FK ");

        if (!fechaInicio.equals("")) {
            cont++;
            cadena.append("WHERE TO_DATE(TO_CHAR(ven.FECHA_VENTA,'dd/mm/yyyy'),'dd/mm/yyyy') BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "' ");
        }

        if (idSucursal != 0) {
            if (cont == 0) {
                cadena.append(" WHERE ");
            } else {
                cadena.append(" AND ");
            }

            cadena.append("ven.ID_SUCURSAL_FK = '" + idSucursal + "' ");
            cont++;

        }
        if (idStatusVenta != 0) {
            if (cont == 0) {
                cadena.append(" WHERE ");
            } else {
                cadena.append(" AND ");
            }

            cadena.append(" ven.STATUS_FK  = '" + idStatusVenta + "' ");
            cont++;

        }

        cadena.append(" ORDER BY ven.ID_VENTA_PK");
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
    public int getFolioByIdSucursal(int idSucursal) {
        try {
            Query query = em.createNativeQuery("select NVL(max(FOLIO_SUCURSAL),0) folioVenta from VENTA where ID_SUCURSAL_FK = ?");
            query.setParameter(1, idSucursal);

            return Integer.parseInt(query.getSingleResult().toString());
        } catch (Exception e) {
            System.out.println("Error >" + e.getMessage());
            return 1;
        }
    }

}
