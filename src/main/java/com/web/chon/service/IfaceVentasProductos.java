/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;


import com.web.chon.dominio.VentasProductos;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author freddy
 */
public interface IfaceVentasProductos {
    public ArrayList<VentasProductos> getVentasProductoByIdVenta(BigDecimal idVentaFk);
    public int insertarVentaProducto(VentasProductos ventaProducto);
    public int cancelarItem(VentasProductos ventaProducto);
    public int getNextVal();
}
