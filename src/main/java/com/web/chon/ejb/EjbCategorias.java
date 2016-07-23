/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

import com.web.chon.dominio.Categorias;
import com.web.chon.negocio.NegocioCategorias;
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
@Stateless(mappedName = "ejbCategorias")
public class EjbCategorias implements NegocioCategorias{
    @PersistenceContext(unitName = "persistenceJR")
    EntityManager em;

    @Override
    public List<Object[]> getCategorias() {
        try {
            Query query = em.createNativeQuery("SELECT * FROM CATEGORIAS");
            List<Object[]> resultList = null;
            resultList = query.getResultList();

            return resultList;

        } catch (Exception ex) {
            Logger.getLogger(EjbCategorias.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Object[]> getCategoriaById(String idCategorias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteCategoria(String idCategorias) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateCategoria(Categorias categoria) {
        try {
            System.out.println("update : " + categoria.toString());
            Query query = em.createNativeQuery("UPDATE CATEGORIAS SET NOMBRE_CATEGORIA=? ,DESCRIPCION_CATEGORIA=? WHERE ID_CATEGORIA_PK=?");
            
            query.setParameter(1, categoria.getNombreCategoria());
            query.setParameter(2, categoria.getDescripcionCategoria());
            query.setParameter(3, categoria.getIdCategorias());
            return query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(EjbCategorias.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int insertarCategoria(Categorias categoria) {
        try {
            System.out.println("insert : " + categoria.toString());
            Query query = em.createNativeQuery("insert into CATEGORIAS (ID_CATEGORIA_PK,NOMBRE_CATEGORIA,DESCRIPCION_CATEGORIA) values(?,?,?)");
            query.setParameter(1, categoria.getIdCategorias());
            query.setParameter(2, categoria.getNombreCategoria());
            query.setParameter(3, categoria.getDescripcionCategoria());
            return query.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(EjbCategorias.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int getLastIdCategoria() {
        Query query = em.createNativeQuery("SELECT MAX(ID_CATEGORIA_PK)+1 ID_CATEGORIA_PK FROM CATEGORIAS");
        String lastId = query.getSingleResult().toString();
        return Integer.parseInt(lastId);
    }
    
}
