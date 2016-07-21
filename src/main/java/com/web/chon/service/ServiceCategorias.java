/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.Categorias;
import com.web.chon.negocio.NegocioCategorias;
import com.web.chon.util.Utilidades;
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
public class ServiceCategorias implements IfaceCategorias {

    NegocioCategorias ejb;
    
    public void getEjb() {
        if (ejb == null) {
            try {
                ejb = (NegocioCategorias) Utilidades.getEJBRemote("ejbCategorias", NegocioCategorias.class.getName());
            } catch (Exception ex) {
                Logger.getLogger(ServiceCategorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Categorias> getCategorias() {
        getEjb();

        List<Object[]> lstObject = new ArrayList<Object[]>();
        ArrayList<Categorias> lstCategorias = new ArrayList<Categorias>();
        lstObject = ejb.getCategorias();
        for (Object[] obj : lstObject) {
            Categorias dominio = new Categorias();
            dominio.setIdCategorias(obj[0] == null ? null : obj[0].toString());;
            dominio.setNombreCategoria(obj[1] == null ? "" : obj[1].toString());
            dominio.setDescripcionCategoria(obj[2] == null ? "" : obj[2].toString());
            lstCategorias.add(dominio);
        }
        return lstCategorias;
    }
    
    
    
}
