/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.util;

import com.web.chon.dominio.Productos;
import com.web.chon.service.ServiceProductos;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author freddy
 */

@Component
public class ProductosConverter implements Converter{
    @Autowired ServiceProductos serviceProductos;
    
    @Override
     public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && !value.equals("null") && value.trim().length() > 0) 
        {

            try {

                Object object = serviceProductos.getProductoById(value);

                return object;

            } catch (Exception e) 
            {
                System.out.println("Entro a esta excepcion");
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error >" + e.getStackTrace(), " - " + e.getStackTrace()));
            }
        } else {

            return null;

        }

    }
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {

            if (value instanceof Productos) {

                return String.valueOf(((Productos) value).getIdProductoPk());

            } else {

                return String.valueOf(value);

            }
        } else {

            return null;

        }
    }
}
