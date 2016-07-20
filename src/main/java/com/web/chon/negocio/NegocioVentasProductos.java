/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.negocio;
import com.web.chon.dominio.VentasProductos;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author freddy
 */
@Remote
public interface NegocioVentasProductos {
    public int getNextVal();
    public List<Object[]> getVentaProductoByIdVenta(BigDecimal idVentaFK);
    public int insertarVentaProducto(VentasProductos ventaProducto);
    public int cancelarVenta(VentasProductos ventaProducto);
}
