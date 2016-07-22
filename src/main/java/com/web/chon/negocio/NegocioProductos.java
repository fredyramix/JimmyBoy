/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.negocio;

import com.web.chon.dominio.Productos;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author freddy
 */
@Remote
public interface NegocioProductos {
    public List<Object[]> getProductos();
    public List<Object[]> getProductosById(String idProducto);
    public int deleteProducto(String idProducto);
    public int insertarProducto(Productos producto);
    public int updateProducto(Productos producto);
    public int getLastIdCategoria();
    public List<Object[]> getProductosByIdCategoria(String idCategoria);
    
}
