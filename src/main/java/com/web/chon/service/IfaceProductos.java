/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.Productos;
import java.util.ArrayList;

/**
 *
 * @author freddy
 */
public interface IfaceProductos {
    public ArrayList<Productos> getProductos();
    
    public Productos getProductoById(String idProducto);
    
    public int deleteProducto(String idProducto);
    
    public int updateProducto(Productos producto);
    
    public int insertarProducto(Productos producto );
    
    public int getLastIdCategoria();
}
