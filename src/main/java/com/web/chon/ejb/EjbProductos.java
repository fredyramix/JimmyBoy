/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.Productos;
import com.web.chon.negocio.NegocioProductos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author freddy
 */
@Stateless(mappedName = "ejbProductos")
public class EjbProductos implements NegocioProductos{
    @PersistenceContext(unitName = "persistenceJR")
    EntityManager em;

    @Override
    public List<Object[]> getProductos() {
        try {

            Query query = em.createNativeQuery("SELECT p.ID_PRODUCTO_PK,p.NOMBRE_PRODUCTO,p.DESCRIPCION_PRODUCTO,\n" +
"p.ID_CATEGORIA_FK,p.ESTATUS,p.URL_IMAGEN , c.NOMBRE_CATEGORIA,p.PRECIO,s.DESCRIPCION_STATUS\n" +
"from productos p\n" +
"inner join CATEGORIAS c\n" +
"on c.ID_CATEGORIA_PK = p.ID_CATEGORIA_FK\n" +
"inner join status s\n" +
"on s.ID_PK = p.ESTATUS order by p.id_categoria_fk");
            List<Object[]> resultList = null;
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbProductos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }}

    @Override
    public int deleteProducto(String idProducto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertarProducto(Productos producto) {
        try {
            System.out.println("insert : " + producto.getNombreProducto() + " " + producto.getDescripcionProducto());
            Query query = em.createNativeQuery("insert into PRODUCTOS (ID_PRODUCTO_PK,NOMBRE_PRODUCTO,DESCRIPCION_PRODUCTO,ID_CATEGORIA_FK,ESTATUS,URL_IMAGEN,PRECIO) values(?,?,?,?,?,?,?)");
            query.setParameter(1, producto.getIdProductoPk());
            query.setParameter(2, producto.getNombreProducto());
            query.setParameter(3, producto.getDescripcionProducto());
            query.setParameter(4, producto.getIdCategoriaFk());
            query.setParameter(5, producto.getEstatus());
            query.setParameter(6, producto.getUrlImagen());
            query.setParameter(7, producto.getPrecio());

            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbProductos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateProducto(Productos producto) {
       try {
            System.out.println("update : " + producto.toString());
            Query query = em.createNativeQuery("UPDATE PRODUCTOS SET NOMBRE_PRODUCTO=? ,DESCRIPCION_PRODUCTO=?,ID_CATEGORIA_FK=?,URL_IMAGEN=?,PRECIO=? WHERE ID_PRODUCTO_PK=? ");
            
            query.setParameter(1, producto.getNombreProducto());
            query.setParameter(2, producto.getDescripcionProducto());
            query.setParameter(3, producto.getIdCategoriaFk());
            
            query.setParameter(5, producto.getUrlImagen());
            query.setParameter(6, producto.getPrecio());
            query.setParameter(7, producto.getIdProductoPk());
            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbProductos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    
    }

    @Override
    public List<Object[]> getProductosById(String idProducto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLastIdCategoria() {
        Query query = em.createNativeQuery("SELECT MAX(ID_PRODUCTO_PK)+1 ID_PRODUCTO_PK FROM PRODUCTOS");
        String lastId = query.getSingleResult().toString();
        return Integer.parseInt(lastId);
    }
    
}
