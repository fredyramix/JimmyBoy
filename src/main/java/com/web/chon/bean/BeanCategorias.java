/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.chon.bean;

import com.web.chon.dominio.Categorias;
import com.web.chon.service.IfaceCategorias;
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
public class BeanCategorias implements Serializable{
    private static final long serialVersionUID = 1L;
    @Autowired private IfaceCategorias ifaceCategorias;

    private String title = "";
    public String viewEstate = "";
    public Categorias data;
    public Categorias dataEdit;
    private ArrayList<Categorias> model;
    private ArrayList<Categorias> selectedCategoria;
    private ArrayList<Categorias> lstCategorias;
     @PostConstruct
    public void init() 
    {
        data = new Categorias();
        model = new ArrayList<Categorias>();
        selectedCategoria = new ArrayList<Categorias>();
        model = ifaceCategorias.getCategorias();
        lstCategorias = ifaceCategorias.getCategorias();
        setTitle("Catalogo de Categorias");
        setViewEstate("init");

    }
    
    public void viewNew() {
        data = new Categorias();
        setTitle("Alta de Categorias");
        setViewEstate("new");
    }

    public void backView() {
        setTitle("Catalogo de Categorias");
        setViewEstate("init");
    }

    public void delete() {

    }

    public void insert() 
    {
        data.setIdCategorias(TiempoUtil.rellenaEspacios(ifaceCategorias.getLastIdCategoria()));
        if (ifaceCategorias.insertarCategoria(data) == 1) {
            JsfUtil.addSuccessMessageClean("Categoria agregada correctamente");
            model = ifaceCategorias.getCategorias();
            data.reset();
//            setTitle("Catalogo de Productos");
//            setViewEstate("init");
        } else {
            JsfUtil.addErrorMessageClean("Ocurrio un problema");
        }

    }

    public void edit() {

    }

    public void searchById() {
        data.setIdCategorias(dataEdit.getIdCategorias());
        data.setDescripcionCategoria(dataEdit.getDescripcionCategoria());
        data.setNombreCategoria(dataEdit.getNombreCategoria());
        viewEstate = "searchById";

    }

    public void updateProducto() {
        if (ifaceCategorias.updateCategoria(data) == 1) {
            JsfUtil.addSuccessMessageClean("Categoria Actualizada correctamente");
            model = ifaceCategorias.getCategorias();
            data.reset();
            dataEdit.reset();
            setTitle("Catalogo de Categorias");
            setViewEstate("init");
        } else {
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

    public Categorias getData() {
        return data;
    }

    public void setData(Categorias data) {
        this.data = data;
    }

    public Categorias getDataEdit() {
        return dataEdit;
    }

    public void setDataEdit(Categorias dataEdit) {
        this.dataEdit = dataEdit;
    }

    public ArrayList<Categorias> getModel() {
        return model;
    }

    public void setModel(ArrayList<Categorias> model) {
        this.model = model;
    }

    public ArrayList<Categorias> getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(ArrayList<Categorias> selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    public ArrayList<Categorias> getLstCategorias() {
        return lstCategorias;
    }

    public void setLstCategorias(ArrayList<Categorias> lstCategorias) {
        this.lstCategorias = lstCategorias;
    }
    
    
    
    
}
