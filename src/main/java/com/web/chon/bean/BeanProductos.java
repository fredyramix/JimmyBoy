/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.bean;

import com.web.chon.dominio.Categorias;
import com.web.chon.dominio.Productos;
import com.web.chon.service.IfaceCategorias;
import com.web.chon.service.IfaceProductos;
import com.web.chon.util.JsfUtil;
import com.web.chon.util.TiempoUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author freddy
 */
@Component
@Scope("view")
public class BeanProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired private IfaceProductos ifaceProductos;
    @Autowired private IfaceCategorias ifaceCategorias;
    
    private String title = "";
    public String viewEstate = "";
    public Productos data;
    public Productos dataEdit;
    private ArrayList<Productos> model;
    private ArrayList<Productos> selectedProducto;
    private ArrayList<Categorias> lstCategorias;
    
    
    
    @PostConstruct
    public void init() 
    {
        data = new Productos();
        model = new ArrayList<Productos>();
        selectedProducto = new ArrayList<Productos>();
        model = ifaceProductos.getProductos();
        lstCategorias = ifaceCategorias.getCategorias();
        setTitle("Catalogo de Productos");
        setViewEstate("init");
        
        
    }
    public void viewNew()
    {
        System.out.println("Enro a view new");
        data = new Productos();
        setTitle("Alta de Productos");
        setViewEstate("new");
    }
    public void update() {
        
    }
    public void backView() {
        setTitle("Catalogo de Productos");
        setViewEstate("init");
    }

    public void delete()
    {
        
    }
    public void insert()
    {
        data.setIdProductoPk(TiempoUtil.rellenaEspacios(ifaceProductos.getLastIdCategoria()));
        data.setEstatus(new BigDecimal(1));
        if(ifaceProductos.insertarProducto(data)==1)
        {
            JsfUtil.addSuccessMessageClean("Producto Agregado correctamente");
            model = ifaceProductos.getProductos();
            data.reset();
            setTitle("Catalogo de Productos");
        setViewEstate("init");
        }
        else
        {
            JsfUtil.addErrorMessageClean("Ocurrio un problema");
        }
        
    }
    public void edit()
    {
        
    }
    public void searchById()
    {
        data.setIdProductoPk(dataEdit.getIdProductoPk());
        data.setIdCategoriaFk(dataEdit.getIdCategoriaFk());
        data.setDescripcionProducto(dataEdit.getDescripcionProducto());
        data.setNombreCategoria(dataEdit.getNombreCategoria());
        data.setNombreProducto(dataEdit.getNombreProducto());
        data.setPrecio(dataEdit.getPrecio());
        viewEstate = "searchById";
        
    }
    public void updateProducto()
    {
         if(ifaceProductos.updateProducto(data)==1)
        {
            JsfUtil.addSuccessMessageClean("Producto Actualizado correctamente");
            model = ifaceProductos.getProductos();
            data.reset();
            dataEdit.reset();
         setTitle("Catalogo de Productos");
        setViewEstate("init");
        }
        else
        {
            JsfUtil.addErrorMessageClean("Ocurrio un problema");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewEstate() {
        return viewEstate;
    }

    public void setViewEstate(String viewEstate) {
        this.viewEstate = viewEstate;
    }

    public Productos getData() {
        return data;
    }

    public void setData(Productos data) {
        this.data = data;
    }

    public ArrayList<Productos> getModel() {
        return model;
    }

    public void setModel(ArrayList<Productos> model) {
        this.model = model;
    }

    public ArrayList<Productos> getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ArrayList<Productos> selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    public ArrayList<Categorias> getLstCategorias() {
        return lstCategorias;
    }

    public void setLstCategorias(ArrayList<Categorias> lstCategorias) {
        this.lstCategorias = lstCategorias;
    }

    public Productos getDataEdit() {
        return dataEdit;
    }

    public void setDataEdit(Productos dataEdit) {
        this.dataEdit = dataEdit;
    }
    
   
}
