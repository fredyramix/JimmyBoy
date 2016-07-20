/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.ejb;

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
    
}
