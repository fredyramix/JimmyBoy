/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.service;

import com.web.chon.dominio.TipoVenta;
import com.web.chon.dominio.topVentas;
import com.web.chon.negocio.NegocioTopVentas;
import com.web.chon.util.TiempoUtil;
import com.web.chon.util.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author freddy
 */
@Service
public class ServiceTopVentas implements IfaceTopVentas{

    NegocioTopVentas ejb;

    public void getEjb() {
        if (ejb == null) {
            try {
                ejb = (NegocioTopVentas) Utilidades.getEJBRemote("ejbTopVentas", NegocioTopVentas.class.getName());
            } catch (Exception ex) {
                Logger.getLogger(ServiceTopVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public ArrayList<topVentas> getMayoreo(Date fechaInicio, Date fechaFin,String orden,BigDecimal rows) {
       getEjb();
        try {
            ArrayList<topVentas> lstTop = new ArrayList<topVentas>();
            List<Object[]> lstObject = ejb.getMayoreo(TiempoUtil.getFechaDDMMYYYY(fechaInicio),TiempoUtil.getFechaDDMMYYYY(fechaFin),orden, rows);

            for (Object[] obj : lstObject) 
            {
                topVentas tv = new topVentas();
                tv.setNombreSucursal(obj[0] == null ? "" : obj[0].toString());
                tv.setNombreVendedor(obj[1] == null ? "" : obj[1].toString());
                tv.setApPaternoVendedor(obj[2] == null ? "" : obj[2].toString());
                tv.setApMaternoVendedor(obj[3] == null ? "" : obj[3].toString());
                tv.setIdVendedor(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
                tv.setEmpaques(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
                tv.setKilos(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
                tv.setDinero(obj[7] == null ? null : new BigDecimal(obj[7].toString()));        
                tv.setNumVentas(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
                System.out.println("tv: "+tv.toString());
                lstTop.add(tv);
            }
            return lstTop;
        } catch (Exception ex) {
            Logger.getLogger(ServiceTopVentas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }

    @Override
    public ArrayList<topVentas> getMenudeo(Date fechaInicio, Date fechaFin,String orden,BigDecimal rows) {
        
        getEjb();
        try {
            ArrayList<topVentas> lstTop = new ArrayList<topVentas>();
            List<Object[]> lstObject = ejb.getMenudeo(TiempoUtil.getFechaDDMMYYYY(fechaInicio),TiempoUtil.getFechaDDMMYYYY(fechaFin),orden,rows);
            for (Object[] obj : lstObject) 
            {
                topVentas tv = new topVentas();
                tv.setNombreSucursal(obj[0] == null ? "" : obj[0].toString());
                tv.setNombreVendedor(obj[1] == null ? "" : obj[1].toString());
                tv.setApPaternoVendedor(obj[2] == null ? "" : obj[2].toString());
                tv.setApMaternoVendedor(obj[3] == null ? "" : obj[3].toString());
                tv.setIdVendedor(obj[4] == null ? null : new BigDecimal(obj[4].toString()));
                tv.setEmpaques(obj[5] == null ? null : new BigDecimal(obj[5].toString()));
                tv.setKilos(obj[6] == null ? null : new BigDecimal(obj[6].toString()));
                tv.setDinero(obj[7] == null ? null : new BigDecimal(obj[7].toString()));        
                tv.setNumVentas(obj[8] == null ? null : new BigDecimal(obj[8].toString()));
                System.out.println("tv: "+tv.toString());
                lstTop.add(tv);
            }
            return lstTop;
        } catch (Exception ex) {
            Logger.getLogger(ServiceTopVentas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
}
