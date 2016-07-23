/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.negocio;

import com.web.chon.dominio.Categorias;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author freddy
 */
@Remote
public interface NegocioCategorias {
    
    public List<Object[]> getCategorias();
    
    
    public List<Object[]> getCategoriaById(String idCategorias);
    
    public int deleteCategoria(String idCategorias);
    
    public int updateCategoria(Categorias categoria);
    
    public int insertarCategoria(Categorias categoria );
    
    public int getLastIdCategoria();
}
