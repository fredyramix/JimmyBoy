/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.Categorias;
import java.util.ArrayList;

/**
 *
 * @author freddy
 */
public interface IfaceCategorias {
    public ArrayList<Categorias> getCategorias();
    
    public Categorias getCategoriaById(String idProducto);
    
    public int deleteCategoria(String idProducto);
    
    public int updateCategoria(Categorias categoria);
    
    public int insertarCategoria(Categorias categoria );
    
    public int getLastIdCategoria();
     
}
